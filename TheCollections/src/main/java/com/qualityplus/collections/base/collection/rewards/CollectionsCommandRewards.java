package com.qualityplus.collections.base.collection.rewards;

import com.qualityplus.assistant.api.common.rewards.Rewards;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public final class CollectionsCommandRewards extends OkaeriConfig implements Rewards<CommandReward> {
    private Map<Integer, List<CommandReward>> rewards = new HashMap<>();
}