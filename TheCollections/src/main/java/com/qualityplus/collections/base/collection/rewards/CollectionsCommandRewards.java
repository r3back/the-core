package com.qualityplus.collections.base.collection.rewards;

import com.qualityplus.assistant.api.common.rewards.LevellableRewards;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public final class CollectionsCommandRewards extends OkaeriConfig implements LevellableRewards<CommandReward> {
    private Map<Integer, List<CommandReward>> rewards = new HashMap<>();
}