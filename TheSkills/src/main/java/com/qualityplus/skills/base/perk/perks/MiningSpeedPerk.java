package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * Utility class for mining speed perk
 */
@Getter @Setter
@NoArgsConstructor
public final class MiningSpeedPerk extends Perk {
    private int secondsDurationPerLevel;
    private int baseSecondsDuration;

    /**
     *
     * @param id                      Id
     * @param enabled                 Enabled
     * @param displayName             Display Name
     * @param description             Description
     * @param skillGUIOptions         {@link GUIOptions}
     * @param initialAmount           Initial Amount
     * @param secondsDurationPerLevel Seconds Duration Per Level
     * @param baseSecondsDuration     Base Seconds Duration
     * @param chancePerLevel          Chance Per Level
     */
    @Builder
    public MiningSpeedPerk(final String id,
                           final boolean enabled,
                           final String displayName,
                           final List<String> description,
                           final GUIOptions skillGUIOptions,
                           final double initialAmount,
                           final int secondsDurationPerLevel,
                           final int baseSecondsDuration,
                           final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);

        this.secondsDurationPerLevel = secondsDurationPerLevel;
        this.baseSecondsDuration = baseSecondsDuration;
    }

    /**
     * Adds an on break
     *
     * @param event {@link BlockBreakEvent}
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();

        final int level = getStat(player);

        if (RandomUtil.randomBetween(0.0, 100.0) >= chancePerLevel * level) {
            return;
        }
        final int duration = getDuration(level) * 20;

        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, duration, 1));
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level), PlaceholderBuilder.create(new Placeholder("duration", getDuration(level))).get());
    }

    private int getDuration(final int level) {
        return ((this.secondsDurationPerLevel * level) + baseSecondsDuration);
    }
}
