package com.qualityplus.pets.base.rewards;

import com.qualityplus.assistant.api.common.rewards.LevellableRewards;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class StatRewards extends OkaeriConfig implements LevellableRewards<StatReward> {
    private Map<Integer, List<StatReward>> rewards = new HashMap<>();
}
