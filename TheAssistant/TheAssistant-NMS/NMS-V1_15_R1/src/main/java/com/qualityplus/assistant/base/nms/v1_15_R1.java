package com.qualityplus.assistant.base.nms;

import com.mojang.authlib.GameProfile;
import com.qualityplus.assistant.api.gui.FakeInventory;
import com.qualityplus.assistant.api.gui.fake.FakeInventoryImpl;
import com.qualityplus.assistant.api.util.FakeInventoryFactory;
import eu.okaeri.injector.annotation.Inject;
import lombok.Getter;
import net.minecraft.server.v1_15_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_15_R1.CraftServer;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.*;

public final class v1_15_R1 extends AbstractNMS{
    private @Getter @Inject Plugin plugin;

    @Override
    public void setBlockAge(Block block, int age) {
        if (block.getBlockData() instanceof Ageable) {
            Ageable crop = (Ageable)block.getBlockData();
            crop.setAge(age);
            block.setBlockData(crop);
        }
    }

    @Override
    public int getAge(Block block) {
        if (block.getBlockData() instanceof Ageable) {
            Ageable crop = (Ageable)block.getBlockData();
            return crop.getAge();
        }
        return 0;
    }

    @Override
    public int getMaxAge(Block block) {
        if (block.getBlockData() instanceof Ageable) {
            Ageable crop = (Ageable)block.getBlockData();
            return crop.getMaximumAge();
        }
        return 0;
    }

    @Override
    public void damageBlock(List<Player> players, Block block, int damage) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();

        BlockPosition position = new BlockPosition(x, y, z);

        //Keeps the same id to prevent packet glitch
        Integer id = Optional.ofNullable(clickCache.getIfPresent(block)).orElse(new Random().nextInt(2000));

        clickCache.put(block, id);

        PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(id, position, damage);

        players.stream()
                .filter(Objects::nonNull)
                .forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet));
    }

    @Override
    public void damageBlock(Player player, Block block, int damage) {
        damageBlock(Collections.singletonList(player), block, damage);
    }

    @Override
    public void removeFakePlayer(FakeInventory fakeInventory) {

    }

    @Override
    public InventoryView createWorkBench(Player player) {
        EntityPlayer entityPlayer = getFakePlayer("Fake Inventory");

        return entityPlayer.getBukkitEntity().openWorkbench(entityPlayer.getBukkitEntity().getLocation(), true);
    }

    @Override
    public FakeInventory getFakeInventory(Player player, FakeInventory fakeInventory) {
        int maxSlots = fakeInventory.getSlots();

        ItemStack[] itemStacks = fakeInventory.getInventory().getContents().clone();

        Inventory inventory = FakeInventoryFactory.getInventoryWithSize(itemStacks, maxSlots);

        return getInventory(inventory, maxSlots);
    }


    @Override
    public FakeInventory getFakeInventory(Player player, int maxSlots) {
        Inventory inventory = FakeInventoryFactory.getInventoryWithSize(maxSlots);

        return getInventory(inventory, maxSlots);
    }

    private FakeInventory getInventory(Inventory inventory, int maxSlots){
        return new FakeInventoryImpl(inventory, maxSlots);
    }

    private EntityPlayer getFakePlayer(String name) {
        World playerWorld = Bukkit.getWorlds().get(0);
        Location location = new Location(playerWorld, 0,0,0);
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) playerWorld).getHandle();
        EntityPlayer fakePlayer = new EntityPlayer(minecraftServer, worldServer, new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(worldServer));
        fakePlayer.getBukkitEntity().setMetadata("NPC", new FixedMetadataValue(plugin, "UUID"));
        fakePlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        fakePlayer.playerConnection = new PlayerConnection(minecraftServer, new NetworkManager(EnumProtocolDirection.CLIENTBOUND), fakePlayer);
        worldServer.addEntity(fakePlayer);
        //(((CraftPlayer) player).getHandle()).playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(fakePlayer));
        //(((CraftPlayer) player).getHandle()).playerConnection.sendPacket(new PacketPlayOutEntityHeadRotation(fakePlayer, (byte) (int) (location.getYaw() * 256.0F / 360.0F)));
        Bukkit.getOnlinePlayers().forEach(player1 -> player1.hidePlayer(fakePlayer.getBukkitEntity()));
        return fakePlayer;
    }

    @Override
    public ItemStack setDurability(ItemStack itemStack, short durability) {
        return null;
    }

    @Override
    public Location getDragonPart(EnderDragon enderDragon, DragonPart dragonPart) {
        EntityComplexPart part = ((CraftEnderDragon) enderDragon).getHandle().bw;
        return new Location(enderDragon.getWorld(), part.lastX, dragonPart.equals(DragonPart.HEAD) ? part.lastY : part.lastY - DragonPart.BODY.nmsDistance, part.lastZ);
    }

    @Override
    public void sendBossBar(Player player, String message) {
        if(player == null || message == null || message.equals("")){
            bossBar.removeAll();
            return;
        }
        Optional.ofNullable(bossBar).ifPresent(BossBar::removeAll);

        bossBar = Bukkit.createBossBar(message, BarColor.PURPLE, BarStyle.SEGMENTED_10, BarFlag.DARKEN_SKY);

        bossBar.addPlayer(player);
    }

    @Override
    public void setEnderEye(Block block, boolean setEnderEye) {
        if(!(block.getBlockData() instanceof EndPortalFrame)) return;

        EndPortalFrame altar = (EndPortalFrame) block.getBlockData();

        altar.setEye(setEnderEye);

        block.setBlockData(altar);
    }
}
