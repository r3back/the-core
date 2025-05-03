package com.qualityplus.runes.base.gui.runetable.effect;

import com.qualityplus.runes.api.session.RuneSession;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface EffectHandler<T>{
    void handle(Player player, T gui, Inventory inventory);

    ItemStack getResult(Player player, RuneSession session);

    void setHasBeenClosed(boolean hasBeenClosed);

    void cancelFusing();

}
