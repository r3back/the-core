package com.qualityplus.minions.base.minions.entity.getter;

import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.layout.LayoutType;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionUpgrade;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.persistance.data.upgrade.UpgradeEntity;

import java.util.Optional;

public interface LayoutGetter extends DataGetter {
    default LayoutType getMinionLayout(Minion minion) {
        if (minion.is(LayoutType.THREE_X_THREE)) return LayoutType.THREE_X_THREE;

        Optional<MinionData> minionData = getData();

        boolean firstUpgrade = minionData.map(data -> hasMinionExpander(data.getFirstUpgrade())).orElse(false);
        boolean secondUpgrade = minionData.map(data -> hasMinionExpander(data.getSecondUpgrade())).orElse(false);

        if (firstUpgrade || secondUpgrade) return LayoutType.THREE_X_THREE;

        return LayoutType.TWO_X_TWO;
    }

    default boolean hasMinionExpander(UpgradeEntity entity) {
        if (entity == null) return false;

        MinionUpgrade upgrade = TheMinions.getApi().getConfigFiles().upgrades().normalUpgrades.getOrDefault(entity.getId(), null);

        if (upgrade == null) return false;

        return upgrade.isExpandsOneBlock();
    }
}
