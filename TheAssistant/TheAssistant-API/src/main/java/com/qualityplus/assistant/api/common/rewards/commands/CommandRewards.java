package com.qualityplus.assistant.api.common.rewards.commands;

import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.assistant.api.common.rewards.Rewards;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter @Setter
public final class CommandRewards extends OkaeriConfig implements Rewards<Reward> {
    private Map<Integer, List<Reward>> rewards = new HashMap<>();
}
