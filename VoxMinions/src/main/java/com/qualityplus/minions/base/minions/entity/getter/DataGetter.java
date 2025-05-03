package com.qualityplus.minions.base.minions.entity.getter;

import com.qualityplus.minions.VoxMinions;
import com.qualityplus.minions.persistance.data.MinionData;

import java.util.Optional;

public interface DataGetter extends MinionUUIDGetter {
    public default Optional<MinionData> getData() {
        return VoxMinions.getApi().getMinionsService().getData(getMinionUniqueId());
    }
}
