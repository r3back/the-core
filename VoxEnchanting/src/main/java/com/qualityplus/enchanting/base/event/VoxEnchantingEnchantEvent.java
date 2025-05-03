package com.qualityplus.enchanting.base.event;

import com.qualityplus.enchanting.api.event.EnchantingEvent;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class VoxEnchantingEnchantEvent extends EnchantingEvent {
    private final EnchantmentSession session;

    public VoxEnchantingEnchantEvent(final @NotNull Player who, final EnchantmentSession session) {
        super(who);

        this.session = session;
    }
}
