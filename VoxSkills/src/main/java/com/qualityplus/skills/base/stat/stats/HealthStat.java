package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.api.util.NumberUtil;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Chance to make critic damage
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class HealthStat extends Stat {
    private static final String HEALTH_KEY_MODIFIER = "theskills.health";
    private Map<UUID, UUID> objects = new HashMap<>();
    private double amountPerLevel;

    @Builder
    public HealthStat(final String id,
                      final boolean enabled,
                      final String displayName,
                      final List<String> description,
                      final GUIOptions skillGUIOptions,
                      final double baseAmount,
                      final double amountPerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.amountPerLevel = amountPerLevel;
    }

    //@EventHandler(priority = EventPriority.LOW)
    public void onEntityDamageByEntity(final PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        final double amount = getStat(player, id);

        final AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (attribute == null) {
            return;
        }

        final UUID attributeID = this.objects.getOrDefault(player.getUniqueId(), null);

        // Player not has attribute defined
        if (attributeID == null) {
            final UUID random = UUID.randomUUID();
            this.objects.put(player.getUniqueId(), random);

            final AttributeModifier newModifier = new AttributeModifier(
                    random,
                    HEALTH_KEY_MODIFIER,
                    amount,
                    AttributeModifier.Operation.ADD_NUMBER);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(newModifier);
        } else {
            final Optional<AttributeModifier> modifier = attribute.getModifiers()
                    .stream()
                    .filter(m -> m.getUniqueId() == attributeID)
                    .findFirst();

            if (modifier.isPresent() && modifier.get().getAmount() == amount) {
                Bukkit.getConsoleSender().sendMessage("Already has");
                return;
            }


            modifier.ifPresent(attributeModifier ->
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(attributeModifier)
            );

            final UUID random = UUID.randomUUID();
            this.objects.put(player.getUniqueId(), random);

            final AttributeModifier newModifier = new AttributeModifier(
                    random,
                    HEALTH_KEY_MODIFIER,
                    amount,
                    AttributeModifier.Operation.ADD_NUMBER);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(newModifier);
        }

    }

    @Override
    public List<String> getFormattedDescription(double level) {
        List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                        new Placeholder("level_roman", NumberUtil.toRoman((int) level)),
                        new Placeholder("amount", this.amountPerLevel * level)
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
