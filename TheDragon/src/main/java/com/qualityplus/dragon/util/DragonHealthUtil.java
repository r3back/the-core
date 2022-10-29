package com.qualityplus.dragon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class DragonHealthUtil {
    private static final double MAX_AMOUNT = 200D;
    private static final double MULTIPLIER = 1;

    public double getHealthAfterDamage(double max, double current, double damageCaused) {
        double basisHealthAfter = current - damageCaused;

        if (max < MAX_AMOUNT)
            return Math.max(basisHealthAfter, 0);

        double heartRate = max / MAX_AMOUNT;

        double lifeWithoutDamage = heartRate * current;

        double finalHealth = Math.max(lifeWithoutDamage - damageCaused, 0);


        return Math.min(Math.max(((int)(finalHealth / heartRate)), 0), MAX_AMOUNT);
    }

    public double getHealth(double max, double current) {
        double heartRate = max / MAX_AMOUNT;

        return (heartRate * MULTIPLIER) * current;
    }
}
