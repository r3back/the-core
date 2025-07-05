package com.qualityplus.minions.base.gui.main.handler.click;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.minions.VoxMinions;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.gui.main.handler.ClickHandler;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionAutoShipping;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.persistance.data.upgrade.AutomatedShippingEntity;
import com.qualityplus.minions.util.MinionUpgradeUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class AutoShipClickHandler implements ClickHandler {
    @Override
    public void handle(InventoryClickEvent event, MinionEntity minionEntity, Box box) {
        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        if (event.isShiftClick()) return;

        Optional<MinionData> data = VoxMinions.getApi().getMinionsService().getData(minionEntity.getMinionUniqueId());
        AutomatedShippingEntity entity = data.map(MinionData::getAutoSell).orElse(null);

        ItemStack cursor = BukkitItemUtil.cloneOrNull(event.getCursor());
        ItemStack oldToGive = getTaken(box, entity, minionEntity);
        boolean isPlacingAutoShip = MinionUpgradeUtil.isAutoShip(cursor);

        if (isPlacingAutoShip) {
            final AutomatedShippingEntity newAuto = getEntityFromItem(box, entity, data, cursor);

            data.ifPresent(d -> d.setAutoSell(newAuto));

            player.setItemOnCursor(oldToGive);

        } else {
            if (entity == null) {
                return;
            }

            if (entity.getHeldCoins() > 0) {
                TheAssistantPlugin.getAPI().getAddons().getEconomy().depositMoney(player, entity.getHeldCoins());
                final String msg = VoxMinions.getApi().getConfigFiles().messages().minionMessages.youReceivedCoins;
                final String finalMsg = StringUtils.processMulti(
                        Optional.ofNullable(msg).orElse("&aYou received &e%coins% &acoins from minion!"),
                        new Placeholder("coins", entity.getHeldCoins()).alone()
                );
                entity.setHeldCoins(0);
                entity.setSoldItems(0);
                player.sendMessage(finalMsg);
                return;
            }

            if (BukkitItemUtil.isNull(cursor)) {
                Optional.ofNullable(oldToGive).ifPresent(player::setItemOnCursor);
            } else
                Optional.ofNullable(oldToGive).ifPresent(item -> BukkitItemUtil.dropItem(player, item));

            data.ifPresent(MinionData::removeAutoShip);
        }
    }

    @SuppressWarnings("all")
    private AutomatedShippingEntity getEntityFromItem(Box box, AutomatedShippingEntity entity, Optional<MinionData> minionData, @Nullable ItemStack itemStack) {
        AutomatedShippingEntity modified = MinionUpgradeUtil.getAutoShipFromItem(itemStack.clone());

        minionData.ifPresent(data -> data.setAutoSell(modified));

        return modified;
    }

    @SuppressWarnings("all")
    private ItemStack getTaken(Box box, AutomatedShippingEntity shippingEntity, MinionEntity entity) {
        if (shippingEntity == null) return null;

        MinionAutoShipping automatedShipping = box.files().getAutoSell().automatedShippingUpgrades.getOrDefault(shippingEntity.getId(), null);

        if (automatedShipping == null) return null;

        return automatedShipping.getItemStack(shippingEntity.getSoldItems(), shippingEntity.getHeldCoins());
    }
}
