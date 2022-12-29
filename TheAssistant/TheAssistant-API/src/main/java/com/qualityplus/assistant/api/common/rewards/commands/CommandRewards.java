package com.qualityplus.assistant.api.common.rewards.commands;

import com.qualityplus.assistant.api.common.rewards.Rewards;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter @Setter
public final class CommandRewards extends OkaeriConfig implements Rewards<CommandReward> {
    private Map<Integer, List<CommandReward>> rewards = new HashMap<>();
}
