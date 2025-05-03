package com.qualityplus.minions.base.minions.entity.getter;

import com.qualityplus.minions.VoxMinions;
import com.qualityplus.minions.persistance.data.MinionData;

import java.util.Optional;

public interface LevelGetter extends MinionUUIDGetter {

    default int getLevel() {
        final Optional<MinionData> petData = VoxMinions.getApi().getMinionsService().getData(getMinionUniqueId());

        return petData.map(MinionData::getLevel).orElse(1);
    }
}
