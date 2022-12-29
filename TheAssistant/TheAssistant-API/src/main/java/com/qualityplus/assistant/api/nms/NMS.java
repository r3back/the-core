package com.qualityplus.assistant.api.nms;

import com.qualityplus.assistant.api.gui.FakeInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface NMS {
    void setBlockAge(Block block, int age);
    int getAge(Block block);
    int getMaxAge(Block block);
    void damageBlock(List<Player> player, Block block, int damage);
    void damageBlock(Player player, Block block, int damage);
    void sendActionBar(Player player, String message);
    void removeFakePlayer(FakeInventory fakeInventory);
    InventoryView createWorkBench(Player player);
    FakeInventory getFakeInventory(Player player, FakeInventory inventory);
    FakeInventory getFakeInventory(Player player, int maxSlots);
    ItemStack setDurability(ItemStack itemStack, short durability);
    Location getDragonPart(EnderDragon enderDragon, DragonPart dragonPart);

    void sendBossBar(Player player, String bossBar);

    void setEnderEye(Block block, boolean setEnderEye);

    @AllArgsConstructor
    enum DragonPart{
        HEAD(3),
        BODY(0);

        public @Getter final int nmsDistance;
    }

    void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
}
