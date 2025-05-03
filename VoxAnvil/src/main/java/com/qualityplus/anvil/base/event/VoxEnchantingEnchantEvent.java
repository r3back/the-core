package com.qualityplus.anvil.base.event;

import com.qualityplus.anvil.api.event.EnchantingEvent;
import com.qualityplus.enchanting.api.session.EnchantmentSession;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class VoxEnchantingEnchantEvent extends EnchantingEvent {
    private final EnchantmentSession session;

    public VoxEnchantingEnchantEvent(@NotNull Player who, EnchantmentSession session) {
        super(who);

        this.session = session;
    }
}
