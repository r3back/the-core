package com.qualityplus.anvil.base.gui.anvilmain.handler;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.base.session.AnvilSessionImpl;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.StringUtils;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@AllArgsConstructor
public final class ShiftClickHandler implements ClickHandler{
    private final Box box;
    private final AnvilMainGUI gui;
    private final AnvilSession session;

    @Override
    public void handle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getSlot() == gui.getConfig().getCombinedFilledItem().slot) {
            if (BukkitItemUtil.isNull(session.getResult())) {
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);

            gui.setGiveItem(false);

            player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, null, null)).getInventory());

            player.getInventory().addItem(session.getResult());

        }else if(event.getSlot() == gui.getConfig().getCombineFilledItem().slot){
            event.setCancelled(true);
        }else{

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

    @Override
    public void handleOutSide(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        final ItemStack current = Optional.ofNullable(event.getCurrentItem())
                .map(ItemStack::clone)
                .orElse(null);

        ShiftTarget target = getTarget();

        if(!BukkitItemUtil.isNull(session.getResult())) {
            player.sendMessage(StringUtils.color(box.files().messages().anvilMessages.thereIsAnItemToPickup));
            event.setCancelled(true);
            return;
        }

        if(BukkitItemUtil.isNull(session.getItemToSacrifice()) && !BukkitItemUtil.isNull(current) && target.equals(ShiftTarget.SACRIFICE)){
            if(!box.files().config().allowedItems.contains(XMaterial.matchXMaterial(current))) {
                event.setCancelled(true);
                return;
            }

            gui.setGiveItem(false);

            event.setCurrentItem(null);

            player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, session.getItemToUpgrade(), current)).getInventory());
        }

        if(BukkitItemUtil.isNull(session.getItemToUpgrade()) && !BukkitItemUtil.isNull(current) && target.equals(ShiftTarget.UPGRADE)){
            if(!box.files().config().allowedItems.contains(XMaterial.matchXMaterial(current))) {
                event.setCancelled(true);
                return;
            }

            gui.setGiveItem(false);

            event.setCurrentItem(null);

            player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, current, session.getItemToSacrifice())).getInventory());
        }
    }

    private ShiftTarget getTarget(){
        boolean toUpgradeIsNull = BukkitItemUtil.isNull(session.getItemToUpgrade());

        return toUpgradeIsNull ? ShiftTarget.UPGRADE : ShiftTarget.SACRIFICE;

    }

    enum ShiftTarget{
        UPGRADE,
        SACRIFICE
    }
}
