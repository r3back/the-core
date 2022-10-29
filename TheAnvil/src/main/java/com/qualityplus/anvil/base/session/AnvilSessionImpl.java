package com.qualityplus.anvil.base.session;

import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.util.AnvilFinderUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public final class AnvilSessionImpl implements AnvilSession {
    private final ItemStack result;
    private final ItemStack itemToUpgrade;
    private final ItemStack itemToSacrifice;

    @Override
    public SessionResult getSessionResult() {
        return AnvilFinderUtil.getAnswer(this);
    }
}
