package com.qualityplus.assistant.api.common.rewards;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Rewards<T> {
    Map<Integer, List<T>> getRewards();

    default List<T> getRewardsForLevel(Integer level){
        return getRewards().getOrDefault(level, Collections.emptyList());
    }
}
