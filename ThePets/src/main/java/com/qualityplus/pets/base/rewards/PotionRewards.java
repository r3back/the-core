package com.qualityplus.pets.base.rewards;

import com.qualityplus.assistant.api.common.rewards.Rewards;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class PotionRewards extends OkaeriConfig implements Rewards<PotionReward> {
    private Map<Integer, List<PotionReward>> rewards = new HashMap<>();
}
