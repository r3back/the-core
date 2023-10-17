package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.shift;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUIConfig;
import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.base.session.AnvilSessionImpl;
import com.qualityplus.anvil.util.ClickLocation;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public final class ShiftUpgradeAndSacrificeRequestStrategy extends CancellableClickRequestStrategy {

    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() &&
                (request.isUpgradeSlot() || request.isSacrificeSlot()) &&
                request.isShiftClick();
    }

    @Override
    public void execute(final ClickRequest request) {
        final Player player = request.getPlayer().orElse(null);
        final InventoryClickEvent event = request.getEvent();
        final AnvilMainGUI gui = request.getGui();
        final Box box = request.getBox();

        if (player == null) {
            return;
        }

        final AnvilSession session = request.getSession();

        if(!BukkitItemUtil.isNull(session.getResult())) {
            player.sendMessage(StringUtils.color(box.files().messages().anvilMessages.thereIsAnItemToPickup));
            event.setCancelled(true);
            return;
        }

        final ItemStack copy = Optional.ofNullable(event.getCursor()).map(ItemStack::clone).orElse(null);
        final ItemStack current = Optional.ofNullable(event.getCurrentItem()).map(ItemStack::clone).orElse(null);

        if(event.getSlot() == gui.getConfig().getToSacrificeSlot()){
            if(BukkitItemUtil.isNull(copy) && !BukkitItemUtil.isNull(current)){
                gui.setGiveItem(false);
                player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, session.getItemToUpgrade(), null)).getInventory());
                player.getInventory().addItem(current);
            }
        }else{
            if(BukkitItemUtil.isNull(copy) && !BukkitItemUtil.isNull(current)){
                gui.setGiveItem(false);
                player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, null, session.getItemToSacrifice())).getInventory());
                player.getInventory().addItem(current);
            }
        }
    }
}
