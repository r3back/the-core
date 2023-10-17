package com.qualityplus.anvil.base.gui.anvilmain.strategy;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;

import java.util.Optional;

public interface ClickRequestStrategy {
    public boolean applies(final ClickRequest request);


    public void execute(final ClickRequest request);

    default void cancelEvent(final ClickRequest request) {
        Optional.ofNullable(request.getEvent()).ifPresent(e -> e.setCancelled(true));
    }
}
