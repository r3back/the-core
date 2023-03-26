package com.qualityplus.assistant.base.nms;

import com.mojang.authlib.GameProfile;
import com.qualityplus.assistant.api.gui.FakeInventory;
import com.qualityplus.assistant.api.gui.fake.FakeInventoryImpl;
import com.qualityplus.assistant.api.util.FakeInventoryFactory;
import eu.okaeri.injector.annotation.Inject;
import lombok.Getter;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.protocol.EnumProtocolDirection;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.boss.EntityComplexPart;
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
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.stream.Collectors;


public final class v1_19_R2 extends AbstractNMS{
    private @Getter @Inject Plugin plugin;
    private final int players = 0;

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
                .forEach(player -> ((CraftPlayer) player).getHandle().b.a(packet));
    }

    @Override
    public void damageBlock(Player player, Block block, int damage) {
        damageBlock(Collections.singletonList(player), block, damage);
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


    @Override
    public void removeFakePlayer(FakeInventory fakeInventory) {
        /*for(Player player : Bukkit.getOnlinePlayers()){
            ((CraftPlayer) player).getHandle().b.a(new PacketPlayOutEntityDestroy(fakeInventory.getEntityId()));
        }*/
    }


    private FakeInventory getInventory(Inventory inventory, int maxSlots){
        return new FakeInventoryImpl(inventory, maxSlots);
    }

    private EntityPlayer getFakePlayer(String name) {
        World playerWorld = Bukkit.getWorlds().get(0);
        Location location = new Location(playerWorld, 0,0,0);
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) playerWorld).getHandle();
        UUID uuid = UUID.randomUUID();
        EntityPlayer fakePlayer = new EntityPlayer(minecraftServer, worldServer, new GameProfile(uuid, name));
        fakePlayer.getBukkitEntity().setMetadata("NPC", new FixedMetadataValue(plugin, "UUID"));
        fakePlayer.a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        fakePlayer.b = new PlayerConnection(minecraftServer, new NetworkManager(EnumProtocolDirection.a), fakePlayer);
        worldServer.b(fakePlayer);
        //(((CraftPlayer) player).getHandle()).b.a(new PacketPlayOutNamedEntitySpawn(fakePlayer));
        //(((CraftPlayer) player).getHandle()).b.a(new PacketPlayOutEntityHeadRotation(fakePlayer, (byte) (int) (location.getYaw() * 256F / 360F)));
        Bukkit.getOnlinePlayers().forEach(player1 -> player1.hidePlayer(fakePlayer.getBukkitEntity()));
        //players+=1;
        //int total = Bukkit.getWorlds().get(0).getPlayers().size();

        //Bukkit.getWorlds().get(0).getPlayers().forEach(player -> Bukkit.getConsoleSender().sendMessage("Player name: " + name));

        //Bukkit.getConsoleSender().sendMessage("Fake Players: " + players);
        //Bukkit.getConsoleSender().sendMessage("Fake Players in World: " + total);

        return fakePlayer;
    }

    @Override
    public ItemStack setDurability(ItemStack itemStack, short durability) {
        return null;
    }

    @Override
    public Location getDragonPart(EnderDragon enderDragon, DragonPart dragonPart) {
        EntityComplexPart part = ((CraftEnderDragon) enderDragon).getHandle().e;

        double x = part.t;
        double y = dragonPart.equals(DragonPart.HEAD) ? part.u : part.u - DragonPart.BODY.nmsDistance;
        double z = part.v;

        return new Location(enderDragon.getWorld(), x, y, z);
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
