package com.qualityplus.skills.base.reward;

import com.qualityplus.assistant.api.common.rewards.LevellableRewards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for stat rewards
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class StatRewards implements LevellableRewards<StatReward> {
    private Map<Integer, List<StatReward>> rewards = new HashMap<>();
}
