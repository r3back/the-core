package com.qualityplus.dragon.api.factory;

import com.qualityplus.dragon.api.game.guardian.Guardian;
import com.qualityplus.dragon.base.configs.DragonEventsFile.*;
import java.util.List;

public interface GuardianFactory {
    Guardian getRandom(List<GuardianChanceConfig> guardians);
}
