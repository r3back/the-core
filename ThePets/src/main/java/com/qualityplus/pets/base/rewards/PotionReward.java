package com.qualityplus.pets.base.rewards;

import com.qualityplus.assistant.api.common.rewards.Reward;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import org.bukkit.entity.Player;

@Data
public final class PotionReward extends OkaeriConfig implements Reward {
    private String potion;
    private int level;

    @Override
    public void execute(Player player) {

    }
}
