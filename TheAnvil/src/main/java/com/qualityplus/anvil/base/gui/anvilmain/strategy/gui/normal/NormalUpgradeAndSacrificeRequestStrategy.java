package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.normal;

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

public final class NormalUpgradeAndSacrificeRequestStrategy extends CancellableClickRequestStrategy {

    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() &&
                (request.isUpgradeSlot() || request.isSacrificeSlot()) &&
                !request.isShiftClick();
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);

        final InventoryClickEvent event = request.getEvent();
        final AnvilMainGUIConfig config = request.getConfig();
        final Player player = request.getPlayer().orElse(null);
        final AnvilMainGUI gui = request.getGui();
        final Box box = request.getBox();

        if (player == null) {
            return;
        }

        final AnvilSession session = request.getSession();

        if (!BukkitItemUtil.isNull(session.getResult())) {
            player.sendMessage(StringUtils.color(box.files().messages().anvilMessages.thereIsAnItemToPickup));
            return;
        }

        final ItemStack copy = Optional.ofNullable(event.getCursor()).map(ItemStack::clone).orElse(null);
        final ItemStack current = Optional.ofNullable(event.getCurrentItem()).map(ItemStack::clone).orElse(null);

        if (BukkitItemUtil.isNull(copy)) {
            AnvilSession newSession = event.getSlot() == config.getToSacrificeSlot() ?
                    new AnvilSessionImpl(null, session.getItemToUpgrade(), null) :
                    new AnvilSessionImpl(null, null, session.getItemToSacrifice());

            //PickUp
            gui.setGiveItem(false);
            event.setCurrentItem(null);
            player.openInventory(new AnvilMainGUI(box, newSession).getInventory());
            player.setItemOnCursor(current);
        } else {

            if (!box.files().config().allowedItems.contains(XMaterial.matchXMaterial(copy))) return;

            AnvilSession newSession = event.getSlot() == gui.getConfig().getToSacrificeSlot() ?
                    new AnvilSessionImpl(null, session.getItemToUpgrade(), copy) :
                    new AnvilSessionImpl(null, copy, session.getItemToSacrifice());
            //Put
            gui.setGiveItem(false);

            player.setItemOnCursor(null);

            player.openInventory(new AnvilMainGUI(box, newSession).getInventory());

            if (BukkitItemUtil.isNull(current)) return;

            player.setItemOnCursor(current);

        }
    }
}
