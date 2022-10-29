package com.qualityplus.runes.api.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface RemoveSession {
    ItemStack getItemToRemove();

    RemoveSessionResult getSessionResult();

    boolean isSuccess();

    @AllArgsConstructor
    public enum RemoveSessionResult {
        ITEM_SET(false),
        ITEM_IS_NOT_RUNED(true),;

        private final @Getter boolean error;
    }
}
