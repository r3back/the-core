package com.qualityplus.dragon.base.service;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.dragon.VoxDragon;
import com.qualityplus.dragon.api.event.SwitchableEvents;
import com.qualityplus.dragon.api.game.part.GameCountdown;
import com.qualityplus.dragon.api.game.part.GameEventSwitch;
import com.qualityplus.dragon.api.game.part.GameExplosion;
import com.qualityplus.dragon.api.service.GameService;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

import java.util.concurrent.CompletableFuture;

@Component
public final class GameServiceImpl implements GameService {
    private @Inject GameEventSwitch gameEventSwitch;
    private @Inject GameExplosion gameExplosion;
    private @Inject GameCountdown gameCountdown;

    @Override
    public CompletableFuture<Void> startCountdown() {
        return gameCountdown.start(VoxDragon.getApi().getDragonService().getActiveDragon());
    }

    @Override
    public CompletableFuture<Void> makeBlockExplosion(PasterSession session) {
        return gameExplosion.makeBlockExplosion(session);
    }


    @Override
    public void switchEvents() {
        gameEventSwitch.switchEvents();
    }

    @Override
    public void stopSwitching() {
        gameEventSwitch.stopSwitchEvents();
    }

    @Override
    public SwitchableEvents getSwitchableEvent() {
        return gameEventSwitch.getSwitchableEvent();
    }
}
