package com.qualityplus.minions.base.gui.main.handler.click;

import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.util.time.RemainingTime;
import com.qualityplus.assistant.util.time.TimeUtils;
import com.qualityplus.minions.VoxMinions;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.gui.main.handler.ClickHandler;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionFuelUpgrade;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.persistance.data.upgrade.FuelEntity;
import com.qualityplus.minions.util.MinionUpgradeUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class FuelClickHandler implements ClickHandler {
    @Override
    public void handle(final InventoryClickEvent event, final MinionEntity minionEntity, final Box box) {
        final Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        if (event.isShiftClick()) {
            return;
        }

        final Optional<MinionData> data = VoxMinions.getApi().getMinionsService().getData(minionEntity.getMinionUniqueId());
        final FuelEntity entity = data.map(MinionData::getFuel).orElse(null);

        final ItemStack cursor = BukkitItemUtil.cloneOrNull(event.getCursor());

        if (entity != null) {
            entity.setRemainingSeconds((int)entity.getMarkable().getRemainingTime().getSeconds());
        }

        final ItemStack oldToGive = getTaken(box, entity, minionEntity);
        final boolean cursorIsEntity = MinionUpgradeUtil.isFuel(cursor);

        // If cursor item is fuel
        if (cursorIsEntity) {
            // New fuel
            final FuelEntity newFuel = getEntityFromItem(box, data, cursor);

            // Set fuel to new fuel
            data.ifPresent(d -> d.setFuel(newFuel));

            // Give old item
            player.setItemOnCursor(oldToGive);

        } else {
            if (entity == null) {
                return;
            }

            if (BukkitItemUtil.isNull(cursor)) {
                Optional.ofNullable(oldToGive).ifPresent(player::setItemOnCursor);
            } else {
                Optional.ofNullable(oldToGive).ifPresent(item -> BukkitItemUtil.dropItem(player, item));
            }

            data.ifPresent(MinionData::removeFuel);
        }
    }

    @SuppressWarnings("all")
    private FuelEntity getEntityFromItem(final Box box,
                                         final Optional<MinionData> minionData,
                                         final @Nullable ItemStack itemStack) {
        final FuelEntity fuelEntity = MinionUpgradeUtil.getFuelFromItem(itemStack.clone());
        final MinionFuelUpgrade fuel = box.files().fuelUpgrades().fuelUpgrades.getOrDefault(
                fuelEntity.getId(),
                null
        );

        final Markable current = fuelEntity.getMarkable();

        if (current.getDelay() == -1 && current.getLastMarked() == -1) {
            fuelEntity.setMarkable(new Markable(System.currentTimeMillis(), fuel.getTimer().getEffectiveTime()));
        } else {
            final long remainingTime = fuelEntity.getRemainingSeconds();

            final HumanTime newRemainingTime = new HumanTime(
                    (int)(remainingTime),
                    HumanTime.TimeType.SECONDS
            );

            fuelEntity.setMarkable(new Markable(System.currentTimeMillis(), newRemainingTime.getEffectiveTime()));
        }
        minionData.ifPresent(data -> data.setFuel(fuelEntity));

        return fuelEntity;
    }

    private long remainingTime(final Markable markable) {
        return markable.getLastMarked() + markable.getDelay();
    }

    private RemainingTime getRemainingTime(final Markable markable) {
        long millis = remainingTime(markable);
        return TimeUtils.getRemainingTime(millis);
    }


    @SuppressWarnings("all")
    private ItemStack getTaken(final Box box, final FuelEntity fuelEntity, final MinionEntity entity) {
        if (fuelEntity == null) {
            return null;
        }

        final MinionFuelUpgrade fuel = box.files().fuelUpgrades().fuelUpgrades.getOrDefault(fuelEntity.getId(), null);

        if (fuel == null) {
            return null;
        }

        final Markable markable = fuelEntity.getMarkable();

        return fuel.getItemStack(markable.getDelay(), markable.getLastMarked(), fuelEntity.getRemainingSeconds());
    }

}
