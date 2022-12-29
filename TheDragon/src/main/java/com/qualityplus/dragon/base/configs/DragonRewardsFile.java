package com.qualityplus.dragon.base.configs;

import com.qualityplus.dragon.base.game.reward.DragonRewardImpl;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Configuration(path = "rewards.yml")
@Header("================================")
@Header("       Dragon Rewards      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class DragonRewardsFile extends OkaeriConfig {
    public Set<DragonRewardImpl> dragonRewards = new HashSet<>(Arrays.asList(DragonRewardImpl.builder()
                    .commands(Arrays.asList("eco give %player% 100", "xp give %player% %thedragon_player_reward_xp%"))
                    .damageDone(75)
            .build(),
            DragonRewardImpl.builder()
                    .commands(Arrays.asList("eco give %player% 75", "xp give %player% %thedragon_player_reward_xp%"))
                    .damageDone(50)
                    .build(),
            DragonRewardImpl.builder()
                    .commands(Arrays.asList("eco give %player% 50", "xp give %player% %thedragon_player_reward_xp%"))
                    .damageDone(25)
                    .build()
            ));
}
