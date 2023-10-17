package com.qualityplus.anvil.base.gui.anvilmain.factory;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.ClickRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.normal.NormalClickToCombineRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.normal.NormalPickUpRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.normal.NormalUpgradeAndSacrificeRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.shift.ShiftClickToCombineRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.shift.ShiftPickUpRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.shift.ShiftUpgradeAndSacrificeRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.player.normal.NormalPlayerClickRequestStrategy;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.player.shift.ShiftPlayerClickRequestStrategy;

import java.util.Optional;
import java.util.Set;

public class ClickRequestStrategyFactoryImpl implements ClickRequestStrategyFactory {
    private static final ClickRequestStrategy NORMAL_UPGRADE_AND_SACRIFICE = new NormalUpgradeAndSacrificeRequestStrategy();
    private static final ClickRequestStrategy SHIFT_UPGRADE_AND_SACRIFICE = new ShiftUpgradeAndSacrificeRequestStrategy();
    private static final ClickRequestStrategy NORMAL_CLICK_TO_COMBINE = new NormalClickToCombineRequestStrategy();
    private static final ClickRequestStrategy SHIFT_CLICK_TO_COMBINE = new ShiftClickToCombineRequestStrategy();
    private static final ClickRequestStrategy PLAYER_NORMAL_CLICK = new NormalPlayerClickRequestStrategy();
    private static final ClickRequestStrategy PLAYER_SHIFT_CLICK = new ShiftPlayerClickRequestStrategy();
    private static final ClickRequestStrategy NORMAL_PICK_UP = new NormalPickUpRequestStrategy();
    private static final ClickRequestStrategy SHIFT_PICK_UP = new ShiftPickUpRequestStrategy();

    public static final Set<ClickRequestStrategy> STRATEGIES_LIST = ImmutableSet.of(
            NORMAL_UPGRADE_AND_SACRIFICE,
            SHIFT_UPGRADE_AND_SACRIFICE,
            NORMAL_CLICK_TO_COMBINE,
            SHIFT_CLICK_TO_COMBINE,
            PLAYER_NORMAL_CLICK,
            PLAYER_SHIFT_CLICK,
            NORMAL_PICK_UP,
            SHIFT_PICK_UP
    );

    @Override
    public Optional<ClickRequestStrategy> getStrategy(final ClickRequest request) {
        return STRATEGIES_LIST.stream()
                .filter(strategy -> strategy.applies(request))
                .findFirst();
    }
}
