package com.qualityplus.minions.api.minion;

import com.qualityplus.assistant.api.gui.FakeInventory;
import com.qualityplus.minions.base.minions.entity.getter.DataGetter;
import com.qualityplus.minions.base.minions.entity.getter.LevelGetter;
import com.qualityplus.minions.base.minions.entity.getter.MinionItemsGetter;
import com.qualityplus.minions.base.minions.entity.state.MinionState;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface MinionEntity extends LevelGetter, DataGetter, MinionItemsGetter {
    void load();
    void unload();

    MinionState getState();
    void tick();

    Collection<ItemStack> pickUpAllItems();

    void spawn(Location location, boolean load);
    void deSpawn(DeSpawnReason reason);

    void updateSkin();
    void updateInventory();

    public enum DeSpawnReason{
        SERVER_TURNED_OFF,
        PLAYER_DE_SPAWN_PET
    }
}
