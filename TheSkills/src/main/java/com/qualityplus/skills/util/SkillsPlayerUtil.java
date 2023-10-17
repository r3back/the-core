package com.qualityplus.skills.util;

import lombok.experimental.UtilityClass;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Utility class for a Skill player util
 */
@UtilityClass
public final class SkillsPlayerUtil {
    /**
     *
     *
     * @param player {@link Player}
     * @return Is In Survival
     */
    public static boolean isInSurvival(final Player player) {
        return player.getGameMode().equals(GameMode.SURVIVAL);
    }

    /**
     * Makes an xp multiplier
     *
     * @param player {@link Player}
     * @return a XP Multiplier
     */
    public static double getXpMultiplier(final Player player) {
        double multiplier = 1;

        for (double i = 1.5D; i < 10D; i += 0.5D) {
            if (player.hasPermission("theskills.xp.multiplier." + i)) {
                multiplier = Math.max(multiplier, i);
            }
        }
        return multiplier;
    }
}
