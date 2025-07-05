package com.qualityplus.dragon.base.game.parts;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.scheduler.PlatformScheduler;
import com.qualityplus.dragon.VoxDragon;
import com.qualityplus.dragon.api.game.part.GameExplosion;
import com.qualityplus.dragon.base.configs.Config;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.lib.eu.okaeri.tasker.core.Tasker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Component
public final class GameExplosionImpl implements GameExplosion {
    private final List<FallingBlock> fallingBlocks = new ArrayList<>();
    private @Inject("scheduler") PlatformScheduler scheduler;
    private static final int BATCH_SIZE = 5;
    private CompletableFuture<Void> future;
    private @Inject Config config;
    private @Inject Tasker tasker;

    @Override
    public CompletableFuture<Void> makeBlockExplosion(final PasterSession session) {
        this.future = new CompletableFuture<>();

        final Collection<Player> players = Bukkit.getOnlinePlayers().stream().map(p -> (Player)p).toList();
        spawnFallingBlocksFake(session, players).thenRun(this::clearBlocks);

        return future;
    }

    private static final int ANIMATED_BLOCKS = 300;
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    private CompletableFuture<Void> spawnFallingBlocksFake(PasterSession session, Collection<Player> players) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        List<Block> allBlocks = Optional.ofNullable(session)
                .map(PasterSession::getAllBlocks)
                .orElse(Collections.emptyList());

        if (allBlocks.isEmpty() || players.isEmpty()) {
            future.complete(null);
            return future;
        }

        Collections.shuffle(allBlocks);
        List<Block> chosen = allBlocks.subList(0, Math.min(ANIMATED_BLOCKS, allBlocks.size()));
        Set<Block> chosenSet = new HashSet<>(chosen);

        List<Block> blocksToProcess = new ArrayList<>(chosenSet);
        int batchSize = 3;

        for (final Block block : allBlocks) {
            if (chosenSet.contains(block)) {
                continue;
            }
            block.setType(Material.AIR);
        }

        new BukkitRunnable() {
            int index = 0;

            @Override
            public void run() {
                int end = Math.min(index + batchSize, blocksToProcess.size());
                for (int i = index; i < end; i++) {
                    Block block = blocksToProcess.get(i);

                    final Material material = block.getType();
                    final BlockData data = block.getBlockData().clone();
                    Location location = block.getLocation().clone();
                    block.setType(Material.AIR);

                    if (chosenSet.contains(block) && material != Material.AIR) {
                        int entityID = sendFakeFallingBlock(data, location, players);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                PacketContainer destroyPacket = protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
                                destroyPacket.getIntLists().write(0, Collections.singletonList(entityID));
                                try {
                                    Bukkit.getOnlinePlayers().forEach(p -> {
                                        try {
                                            protocolManager.sendServerPacket(p, destroyPacket);
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.runTaskLater(VoxDragon.getApi().getPlugin(), 40L);
                    }
                }

                index += batchSize;
                if (index >= blocksToProcess.size()) cancel();
            }
        }.runTaskTimer(VoxDragon.getApi().getPlugin(), 0L, 0L);

        Bukkit.getScheduler().runTaskLater(VoxDragon.getApi().getPlugin(), () -> {
            future.complete(null);
        }, 20 * 3);

        return future;
    }

    private int sendFakeFallingBlock(final BlockData material, Location location, Collection<Player> players) {
        int entityId = (int) (Integer.MAX_VALUE * Math.random());
        Location loc = location.add(0.5, 0, 0.5); // centro del bloque

        // Usa el blockdata real del bloque original
        PacketContainer spawnPacket = protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        Vector velocity = new Vector(
                (Math.random() - 0.5) * 0.5,
                Math.random() * 0.8 + 0.4,
                (Math.random() - 0.5) * 0.5
        );

        spawnPacket.getIntegers()
                .write(0, entityId);
        spawnPacket.getUUIDs()
                .write(0, UUID.randomUUID());
        spawnPacket.getDoubles()
                .write(0, loc.getX())
                .write(1, loc.getY())
                .write(2, loc.getZ());

        spawnPacket.getEntityTypeModifier().write(0, EntityType.FALLING_BLOCK);

        // Block state id para falling_block
        /*try {
            Bukkit.getConsoleSender().sendMessage("Spawning block " + material.getMaterial().name());

            WrappedBlockData blockData = WrappedBlockData.createData(material);
            int blockStateID = blockData.getData();
            spawnPacket.getIntegers().write(4, blockStateID);
        } catch (Exception e) {*/
            spawnPacket.getIntegers()
                    .write(4, 1);
        //}


        int x = (int) (velocity.getX() * 8000);
        int y = (int) (velocity.getY() * 8000);
        int z = (int) (velocity.getZ() * 8000);


        spawnPacket.getIntegers()
                .write(1, x)
                .write(2, y)
                .write(3, z);

        try {
            for (Player player : players) {
                protocolManager.sendServerPacket(player, spawnPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entityId;
    }

    // MUEVE EL BLOQUE FAKE en el lado cliente
    /*private void sendMoveFallingBlock(FakeFallingBlock ffb, Collection<Player> players) {
        PacketContainer teleportPacket = protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
        teleportPacket.getIntegers().write(0, ffb.entityId);
        teleportPacket.getDoubles().write(0, ffb.location.getX());
        teleportPacket.getDoubles().write(1, ffb.location.getY());
        teleportPacket.getDoubles().write(2, ffb.location.getZ());
        teleportPacket.getBytes().write(0, (byte) 0); // yaw
        teleportPacket.getBytes().write(1, (byte) 0); // pitch
        teleportPacket.getBooleans().write(0, true); // On ground? (puedes ajustar)

        try {
            for (Player player : players) {
                protocolManager.sendServerPacket(player, teleportPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    // DESTRUYE (borra) EL BLOQUE FAKE
    /*private void sendDestroyFallingBlock(FakeFallingBlock ffb, Collection<Player> players) {
        PacketContainer destroyPacket = protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        destroyPacket.getIntLists().write(0, Collections.singletonList(ffb.entityId));
        try {
            for (Player player : players) {
                protocolManager.sendServerPacket(player, destroyPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    private void clearBlocks() {
        scheduler.runLater(this::clearBlocksTask, 60, false);
    }

    private void clearBlocksTask() {
        fallingBlocks.forEach(this::clearIndividualFallingBlock);
        fallingBlocks.clear();
        future.complete(null);
    }

    private void clearIndividualFallingBlock(FallingBlock fallingBlock) {
        if (fallingBlock.isOnGround()) fallingBlock.getLocation().getBlock().setType(Material.AIR);

        if (!fallingBlock.isDead()) fallingBlock.remove();
    }


    private double randomDouble() {
        return -0.5D + ThreadLocalRandom.current().nextDouble();
    }
}
