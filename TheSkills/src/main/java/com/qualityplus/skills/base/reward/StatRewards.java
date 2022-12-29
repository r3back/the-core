package com.qualityplus.skills.base.reward;

import com.qualityplus.assistant.api.common.rewards.Rewards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class StatRewards implements Rewards<StatReward> {
    private Map<Integer, List<StatReward>> rewards = new HashMap<>();
}
