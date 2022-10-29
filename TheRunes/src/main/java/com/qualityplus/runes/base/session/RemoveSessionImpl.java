package com.qualityplus.runes.base.session;

import com.qualityplus.runes.api.session.RemoveSession;
import com.qualityplus.runes.api.session.RuneInstance;
import com.qualityplus.runes.util.RuneFinderUtil;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
public final class RemoveSessionImpl implements RemoveSession {
    private final ItemStack itemToRemove;
    private final boolean success;

    public RemoveSessionImpl(ItemStack itemToRemove, boolean success) {
        this.itemToRemove = itemToRemove;
        this.success = success;
    }

    @Override
    public RemoveSessionResult getSessionResult() {
        return RuneFinderUtil.getAnswer(this);
    }
}
