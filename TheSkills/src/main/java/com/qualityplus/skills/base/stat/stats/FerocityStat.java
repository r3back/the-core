package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.event.FerocityDamageEvent;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

/**
 * Chance to make damage twice
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class FerocityStat extends Stat {
    private double chancePerLevel;

    /**
     * Makes a ferocity stats
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param baseAmount      Base Amount
     * @param chancePerLevel  Chance Per Level
     */
    @Builder
    public FerocityStat(final String id,
                        final boolean enabled,
                        final String displayName,
                        final List<String> description,
                        final GUIOptions skillGUIOptions,
                        final double baseAmount,
                        final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.chancePerLevel = chancePerLevel;
    }

    /**
     * Makes an on entity damage by entity
     *
     * @param event {@link EntityDamageByEntityEvent}
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        final Player player = (Player) event.getDamager();

        final LivingEntity victim = (LivingEntity) event.getEntity();

        if (victim.hasMetadata("ferocity")) {
            return;
        }
        final int level = getStat(player);

        if (RandomUtil.randomBetween(0.0, 100.0) >= this.chancePerLevel * level) {
            return;
        }

        Bukkit.getScheduler().runTask(TheSkills.getApi().getPlugin(), () -> {
            final FerocityDamageEvent ferocityEvent = new FerocityDamageEvent(player, this, event.getEntity(), event.getDamage());

            Bukkit.getPluginManager().callEvent(ferocityEvent);

            if (ferocityEvent.isCancelled()) {
                return;
            }

            victim.setMetadata("ferocity", new FixedMetadataValue(TheSkills.getApi().getPlugin(), this));
            victim.setNoDamageTicks(0);
            victim.damage(ferocityEvent.getDamage(), player);
        });
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level)),
                      new Placeholder("chance", this.chancePerLevel * level)
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
