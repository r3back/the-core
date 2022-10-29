package com.qualityplus.skills.base.config.skills.common;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SkillsConfig {
    public static Map<Integer, Double> getRequirements(){
        return ImmutableMap.<Integer, Double>builder()
                .put(0, 10D)
                .put(1, 15D)
                .put(2, 20D)
                .put(3, 50D)
                .put(4, 100D)
                .put(5, 200D)
                .put(6, 300D)
                .put(7, 400D)
                .put(8, 600D)
                .put(9, 700D)
                .put(10, 800D)
                .put(11, 900D)
                .put(12, 1000D)
                .put(13, 1100D)
                .put(14, 1200D)
                .put(15, 1300D)
                .put(16, 1400D)
                .put(17, 1500D)
                .put(18, 1600D)
                .put(19, 1700D)
                .put(20, 1800D)
                .put(21, 1900D)
                .put(22, 2000D)
                .put(23, 2100D)
                .put(24, 2200D)
                .put(25, 2300D)
                .put(26, 2400D)
                .put(27, 2500D)
                .put(28, 2600D)
                .put(29, 2700D)
                .put(30, 2800D)
                .put(31, 2900D)
                .put(32, 3000D)
                .put(33, 3100D)
                .put(34, 3200D)
                .put(35, 3300D)
                .put(36, 3400D)
                .put(37, 3500D)
                .put(38, 3600D)
                .put(39, 3700D)
                .put(40, 3800D)
                .put(41, 3900D)
                .put(42, 4000D)
                .put(43, 4100D)
                .put(44, 4200D)
                .put(45, 4300D)
                .put(46, 4400D)
                .put(47, 4500D)
                .put(48, 4600D)
                .put(49, 4700D)
                .put(50, 4800D)
                .build();
    }
}
