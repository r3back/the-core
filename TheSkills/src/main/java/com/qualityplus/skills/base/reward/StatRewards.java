package com.qualityplus.skills.base.reward;

import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.assistant.api.common.rewards.Rewards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class StatRewards implements Rewards<Reward> {
    private Map<Integer, List<Reward>> rewards = new HashMap<>();
}
