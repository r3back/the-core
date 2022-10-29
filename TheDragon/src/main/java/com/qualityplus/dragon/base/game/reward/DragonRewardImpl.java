package com.qualityplus.dragon.base.game.reward;

import com.qualityplus.dragon.api.game.reward.DragonReward;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@Builder
public final class DragonRewardImpl extends OkaeriConfig implements DragonReward {
    private final int damageDone;
    private final List<String> commands;

    @Override
    public int getDamageDone() {
        return damageDone;
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
