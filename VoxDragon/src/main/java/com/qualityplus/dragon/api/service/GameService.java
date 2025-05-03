package com.qualityplus.dragon.api.service;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.dragon.api.event.SwitchableEvents;

import java.util.concurrent.CompletableFuture;

public interface GameService {
    CompletableFuture<Void> startCountdown();
    CompletableFuture<Void> makeBlockExplosion(PasterSession session);
    void switchEvents();
    void stopSwitching();
    SwitchableEvents getSwitchableEvent();
}
