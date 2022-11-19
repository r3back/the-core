package com.qualityplus.assistant.base.nms;

import com.mojang.authlib.GameProfile;
import eu.okaeri.injector.annotation.Inject;
import lombok.Getter;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.protocol.EnumProtocolDirection;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.boss.EntityComplexPart;
import net.minecraft.world.entity.player.ProfilePublicKey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public final class v1_19_R1 extends AbstractNMS{
    private @Getter @Inject Plugin plugin;

    @Override
    public void damageBlock(List<Player> player, Block block, int damage) {

    }

    @Override
    public void damageBlock(Player player, Block block, int damage) {
        damageBlock(Collections.singletonList(player), block, damage);
    }

    @Override
    public InventoryView getFakeInventory(Player player) {
        World playerWorld = player.getWorld();
        Location location = player.getLocation();
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) playerWorld).getHandle();
        ProfilePublicKey profilePublicKey = null; //when it this required to be not-null?
        EntityPlayer fakePlayer = new EntityPlayer(minecraftServer, worldServer, new GameProfile(UUID.randomUUID(), "Fake Inventory"), profilePublicKey);
        fakePlayer.getBukkitEntity().setMetadata("NPC", new FixedMetadataValue(plugin, "UUID"));
        fakePlayer.a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        fakePlayer.b = new PlayerConnection(minecraftServer, new NetworkManager(EnumProtocolDirection.a), fakePlayer);
        worldServer.b(fakePlayer);
        (((CraftPlayer) player).getHandle()).b.a(new PacketPlayOutNamedEntitySpawn(fakePlayer));
        (((CraftPlayer) player).getHandle()).b.a(new PacketPlayOutEntityHeadRotation(fakePlayer, (byte) (int) (location.getYaw() * 256F / 360F)));
        player.hidePlayer(fakePlayer.getBukkitEntity());
        return fakePlayer.getBukkitEntity().openWorkbench(fakePlayer.getBukkitEntity().getLocation(), true);
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
