package com.qualityplus.minions.base.gui.main.handler.click;

import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.gui.main.handler.ClickHandler;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionAutoShipping;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.persistance.data.upgrade.AutomatedShippingEntity;
import com.qualityplus.minions.util.MinionEggUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;
import java.util.Optional;

public final class PickUpMinionClickHandler implements ClickHandler {
    @Override
    public void handle(InventoryClickEvent event, MinionEntity minionEntity, Box box) {
        Player player = (Player) event.getWhoClicked();

        player.closeInventory();

        dropMinionItems(player, minionEntity, box);

        minionEntity.deSpawn(MinionEntity.DeSpawnReason.PLAYER_DE_SPAWN_PET);

        MinionEggUtil
                .createFromExistent(box.files().config().minionEggItem, minionEntity.getMinionUniqueId())
                .ifPresent(player.getInventory()::addItem);

    }

    private void dropMinionItems(Player player, MinionEntity minionEntity, Box box){
        minionEntity.pickUpAllItems()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> player.getWorld().dropItem(player.getLocation(), item));

        Optional<MinionData> minionData = TheMinions.getApi().getMinionsService().getData(minionEntity.getMinionUniqueId());

        AutomatedShippingEntity autoSellEntity = minionData
                .map(MinionData::getAutoSell)
                .orElse(null);

        if(autoSellEntity != null){
            MinionAutoShipping automatedShipping = box.files().getAutoSell().automatedShippingUpgrades.getOrDefault(autoSellEntity.getId(), null);

            if(automatedShipping != null)
                player.getWorld().dropItem(player.getLocation(), automatedShipping.getItemStack(autoSellEntity.getSoldItems(), autoSellEntity.getHeldCoins()));
        }
    }
}
