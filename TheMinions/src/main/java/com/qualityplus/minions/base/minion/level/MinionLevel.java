package com.qualityplus.minions.base.minion.level;

import com.qualityplus.assistant.util.time.TimeUtils;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class MinionLevel extends OkaeriConfig {
    private Timer executeActionsTime;
}
