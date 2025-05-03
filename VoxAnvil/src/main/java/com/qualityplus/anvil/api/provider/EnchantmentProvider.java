package com.qualityplus.anvil.api.provider;

import com.qualityplus.anvil.api.session.AnvilSession.SessionResult;
import com.qualityplus.anvil.api.session.AnvilSession;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface EnchantmentProvider {
    public SessionResult getAnswer(AnvilSession session);

    public ItemStack getFinalItem(AnvilSession anvilSession);

    public double getMoneyCost(AnvilSession session);

    public double getLevelsCost(AnvilSession session);

    public boolean dontHavePermission(AnvilSession session, Player player);
}
