package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;
import java.util.Optional;

/**
 * Chance to make critic damage
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class CritChanceStat extends Stat {
    private double chancePerLevel;

    /**
     * Makes a critic chance stats
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
    public CritChanceStat(final String id,
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
     * Adds an entity damage
     *
     * @param e {@link EntityDamageByEntityEvent}
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        final Player player = (Player) e.getDamager();

        final double randomNumber = RandomUtil.randomBetween(0.0, 100.0);
        final double playerChance = this.chancePerLevel * getStat(player);

        if (randomNumber >= playerChance) {
            return;
        }
        final Optional<CritDamageStat> damageStat = Stats.values(stat -> stat instanceof CritDamageStat).stream()
                .map(stat -> (CritDamageStat) stat)
                .findFirst();


        double multiplier = damageStat
                .map(CritDamageStat::getDamagePercentagePerLevel)
                .orElse(0D)
                *
                getStat(player, damageStat.map(CritDamageStat::getId).orElse(""));

        multiplier += initialAmount;
        multiplier /= 100;
        multiplier += 1;

        /*CritDamageEvent event = new CritDamageEvent(player, this);
        Bukkit.getPluginManager().callEvent(e);

        if (event.isCancelled()) return;*/

        e.setDamage(e.getDamage() * multiplier);
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
