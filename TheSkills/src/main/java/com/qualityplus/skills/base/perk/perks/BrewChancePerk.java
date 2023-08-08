package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XPotion;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.random.EasyRandom;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for Chance to Give Random Potion Efect
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class BrewChancePerk extends Perk {
    private Map<XPotion, Double> potionAndChances;
    private int secondsDurationPerLevel;
    private int baseSecondsDuration;

    /**
     * Makes a rew chance perk
     *
     * @param id                      Id
     * @param enabled                 Enabled
     * @param displayName             Display Name
     * @param description             Description
     * @param skillGUIOptions         {@link GUIOptions}
     * @param baseAmount              Base Amount
     * @param chancePerLevel          Chance Per Level
     * @param potionAndChances        Potion And Chances
     * @param baseSecondsDuration     Base Seconds Duration
     * @param secondsDurationPerLevel Seconds Duration Per Level
     */
    @Builder
    public BrewChancePerk(final String id,
                          final boolean enabled,
                          final String displayName,
                          final List<String> description,
                          final GUIOptions skillGUIOptions,
                          final double baseAmount,
                          final double chancePerLevel,
                          final Map<XPotion, Double> potionAndChances,
                          final int baseSecondsDuration,
                          final int secondsDurationPerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount, chancePerLevel);

        this.secondsDurationPerLevel = secondsDurationPerLevel;
        this.baseSecondsDuration = baseSecondsDuration;
        this.potionAndChances = potionAndChances;
    }

    /**
     * Adds a player consume potion
     *
     * @param e {@link PlayerItemConsumeEvent}
     */
    @EventHandler(ignoreCancelled = true)
    public void playerConsumePotion(final PlayerItemConsumeEvent e) {
        if (!e.getItem().getType().equals(Material.POTION)) {
            return;
        }

        final Player p = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= chancePerLevel * getStat(p)) {
            return;
        }
        getRandom(p).ifPresent(p::addPotionEffect);
    }

    private Optional<PotionEffect> getRandom(final Player player) {
        final List<EasyRandom<XPotion>> items = this.potionAndChances.keySet().stream()
                .map(item -> new EasyRandom<>(item, this.potionAndChances.get(item)))
                .collect(Collectors.toList());

        final int level = getStat(player);

        final int duration = getDurationTicks(level);

        return Optional.ofNullable(new RandomSelector<>(items).getRandom()).map(item -> item.getItem().buildPotionEffect(duration, level));
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), Collections
                .singletonList(new Placeholder("duration", getDurationSeconds(level))));
    }

    private int getDurationTicks(final int level) {
        return getDurationSeconds(level) * 20;
    }

    private int getDurationSeconds(final int level) {
        return this.baseSecondsDuration + (this.secondsDurationPerLevel * level);
    }
}
