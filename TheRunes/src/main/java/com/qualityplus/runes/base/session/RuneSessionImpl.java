package com.qualityplus.runes.base.session;

import com.qualityplus.runes.api.session.RuneSession;
import com.qualityplus.runes.api.session.RuneInstance;
import com.qualityplus.runes.util.RuneFinderUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
public final class RuneSessionImpl implements RuneSession {
    private final UUID uuid;
    private final ItemStack result;
    private final ItemStack itemToUpgrade;
    private final ItemStack itemToSacrifice;
    private final RuneInstance runeInstance;

    private @Setter boolean isFusing = false;

    public RuneSessionImpl(UUID uuid, ItemStack result, ItemStack itemToUpgrade, ItemStack itemToSacrifice, RuneInstance runeInstance) {
        this.uuid = uuid;
        this.result = result;
        this.itemToUpgrade = itemToUpgrade;
        this.itemToSacrifice = itemToSacrifice;
        this.runeInstance = runeInstance;
    }

    @Override
    public SessionResult getSessionResult() {
        return RuneFinderUtil.getAnswer(this);
    }
}
