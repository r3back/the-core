package com.qualityplus.runes.base.rune.effects;

import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.runes.base.rune.EffectBridge;
import com.qualityplus.runes.base.rune.Rune;
import com.qualityplus.runes.base.rune.RuneApply;
import com.qualityplus.runes.base.rune.effects.apply.BootEffect;
import com.qualityplus.runes.base.rune.effects.apply.BowEffect;
import com.qualityplus.runes.base.rune.effects.apply.ChainEffect;
import com.qualityplus.runes.base.rune.effects.apply.SwordEffect;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

@Component
public final class EffectBridgeImpl implements EffectBridge {
    private final Map<RuneApply, ChainEffect> effectMap = FasterMap.builder(RuneApply.class, ChainEffect.class)
            .put(RuneApply.BOW, new BowEffect())
            .put(RuneApply.BOOTS, new BootEffect())
            .put(RuneApply.SWORD, new SwordEffect())
            .build();

    @Override
    public void applyExact(Player player, Entity entity, Rune rune) {
        effectMap.get(rune.getEffect().getToApplyIn()).execute(player, entity, rune);
    }
}
