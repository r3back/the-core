package com.qualityplus.anvil.base.gui.anvilmain.handler;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.base.session.AnvilSessionImpl;
import com.qualityplus.anvil.util.AnvilFinderUtil;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.anvil.api.box.Box;
import lombok.AllArgsConstructor;
import com.qualityplus.anvil.api.session.AnvilSession.SessionResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@AllArgsConstructor
public final class NormalClickHandler implements ClickHandler{
    private final Box box;
    private final AnvilMainGUI gui;
    private final AnvilSession session;

    @Override
    public void handle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getSlot() == gui.getConfig().getCombineFilledItem().slot){
            event.setCancelled(true);

            SessionResult sessionResult = session.getSessionResult();

            if(!sessionResult.equals(SessionResult.BOTH_ITEMS_SET)) return;

            if(AnvilFinderUtil.dontHavePermission(session, player)){
                player.sendMessage(box.files().messages().anvilMessages.youDontHaveEnoughPermissions);
                return;
            }

            if(AnvilFinderUtil.getMoneyCost(session) > TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(player)){
                player.sendMessage(box.files().messages().anvilMessages.youDontHaveEnoughMoney);
                return;
            }

            if(AnvilFinderUtil.getLevelsCost(session) > player.getLevel()){
                player.sendMessage(box.files().messages().anvilMessages.youDontHaveEnoughLevels);
                return;
            }

            gui.setGiveItem(false);

            player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(AnvilFinderUtil.getFinalItem(session), null, null)).getInventory());

        } else if(event.getSlot() == gui.getConfig().getCombinedFilledItem().slot){

            if(ItemStackUtils.isNull(session.getResult())) {
                event.setCancelled(true);
                return;
            }

            gui.setGiveItem(false);

            player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(null, null, null)).getInventory());

            player.setItemOnCursor(session.getResult());

        }else{
            event.setCancelled(true);

            if(!ItemStackUtils.isNull(session.getResult())) {
                player.sendMessage(StringUtils.color(box.files().messages().anvilMessages.thereIsAnItemToPickup));
                return;
            }

            final ItemStack copy = Optional.ofNullable(event.getCursor()).map(ItemStack::clone).orElse(null);
            final ItemStack current = Optional.ofNullable(event.getCurrentItem()).map(ItemStack::clone).orElse(null);

            if(ItemStackUtils.isNull(copy)){
                AnvilSession newSession = event.getSlot() == gui.getConfig().getToSacrificeSlot() ?
                        new AnvilSessionImpl(null, session.getItemToUpgrade(), null) :
                        new AnvilSessionImpl(null, null, session.getItemToSacrifice());

                //PickUp
                gui.setGiveItem(false);
                event.setCurrentItem(null);
                player.openInventory(new AnvilMainGUI(box, newSession).getInventory());
                player.setItemOnCursor(current);
            }else{

                if(!box.files().config().allowedItems.contains(XMaterial.matchXMaterial(copy))) return;

                AnvilSession newSession = event.getSlot() == gui.getConfig().getToSacrificeSlot() ?
                        new AnvilSessionImpl(null, session.getItemToUpgrade(), copy) :
                        new AnvilSessionImpl(null, copy, session.getItemToSacrifice());
                //Put
                gui.setGiveItem(false);

                player.setItemOnCursor(null);

                player.openInventory(new AnvilMainGUI(box, newSession).getInventory());

                if(ItemStackUtils.isNull(current)) return;

                player.setItemOnCursor(current);

            }
        }
    }

    @Override
    public void handleOutSide(InventoryClickEvent event) {

    }
}
