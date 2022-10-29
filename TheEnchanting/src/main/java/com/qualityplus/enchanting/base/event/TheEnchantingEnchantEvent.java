package com.qualityplus.enchanting.base.event;

import com.qualityplus.enchanting.api.event.EnchantingEvent;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class TheEnchantingEnchantEvent extends EnchantingEvent {
    private final EnchantmentSession session;

    public TheEnchantingEnchantEvent(@NotNull Player who, EnchantmentSession session) {
        super(who);

        this.session = session;
    }
}
