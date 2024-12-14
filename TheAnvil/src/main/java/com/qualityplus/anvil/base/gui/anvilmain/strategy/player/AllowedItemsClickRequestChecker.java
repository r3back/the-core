package com.qualityplus.anvil.base.gui.anvilmain.strategy.player;

import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public abstract class AllowedItemsClickRequestChecker {
    public void check(final ClickRequest request){
        final ItemStack current = request.getEvent().getCurrentItem();

        if (BukkitItemUtil.isNull(current)) {
            return;
        }

        final XMaterial material = XMaterial.matchXMaterial(current.getType());

        if (request.getBox().files().config().allowedItems.contains(material)) {
            return;
        }

        request.getEvent().setCancelled(true);
    }
}
