package com.qualityplus.runes.base.provider;

import com.qualityplus.runes.api.provider.EffectProvider;
import com.qualityplus.runes.base.rune.Rune;
import com.qualityplus.runes.base.rune.EffectBridge;
import com.qualityplus.runes.base.rune.effects.EffectBridgeImpl;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@Component
public final class EffectProviderImpl implements EffectProvider {
    private @Inject EffectBridge particleBridge;

    @Override
    public void execute(Player player, Entity entity, Rune rune) {
        particleBridge.applyExact(player, entity, rune);
    }
}

