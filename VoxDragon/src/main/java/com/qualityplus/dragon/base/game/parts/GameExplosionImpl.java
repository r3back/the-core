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
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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

    private static final int ANIMATED_BLOCKS = 5;
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    private final static Map<UUID, Integer> tasks = new HashMap<>();

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

        // Guarda info de cada Fake FallingBlock para animación/movimiento
        //List<FakeFallingBlock> fakeBlocks = new ArrayList<>();

        Bukkit.getScheduler().runTask(VoxDragon.getApi().getPlugin(), () -> {
            for (Block block : allBlocks) {
                if (chosenSet.contains(block)) {
                    // 1. Desaparece el bloque real
                    block.setType(Material.AIR);

                    // 2. Spawnea FAKE FallingBlock sólo en cliente
                    sendFakeFallingBlock(block, players);
                    //fakeBlocks.add(ffb);
                } else {
                    // 3. Los demás sólo se borran
                    block.setType(Material.AIR);
                }
            }
        });

        final UUID uuid = UUID.randomUUID();
        // 4. Animación de movimiento fake durante n ticks (puedes mejorar el movimiento aquí)
        /*final int taskID = Bukkit.getScheduler().runTaskTimer(VoxDragon.getApi().getPlugin(), new Runnable() {
            int tick = 0;
            @Override
            public void run() {
                tick++;
                Iterator<FakeFallingBlock> it = fakeBlocks.iterator();
                while (it.hasNext()) {
                    FakeFallingBlock ffb = it.next();
                    // movimiento simple: simula caída (puedes hacer algo más complejo!)
                    ffb.location.add(ffb.motion);

                    // Teleporta a los clientes
                    sendMoveFallingBlock(ffb, players);

                    // Opcional: destruye después de 25 ticks (~1.25 seg)
                    if (tick > 25) {
                        sendDestroyFallingBlock(ffb, players);
                        it.remove();
                    }
                }
                if (fakeBlocks.isEmpty()) {
                    int toCancel = tasks.get(uuid);
                    if (toCancel > 0) {
                        tasks.remove(uuid);
                        Bukkit.getScheduler().cancelTask(toCancel);
                    }
                    future.complete(null);
                }
            }
        }, 1, 1).getTaskId();*/

        //tasks.put(uuid, taskID);
        return future;
    }

    // Estructura para llevar la info de cada bloque fake
    /*private static class FakeFallingBlock {
        int entityId;
        Location location;
        WrappedBlockData blockData;
        Vector motion;

        FakeFallingBlock(int entityId, Location location, WrappedBlockData blockData, Vector motion) {
            this.entityId = entityId;
            this.location = location.clone();
            this.blockData = blockData;
            this.motion = motion;
        }
    }*/

    // SPAWNEA EL BLOQUE FAKE
    private void sendFakeFallingBlock(Block block, Collection<Player> players) {
        int entityId = (int) (Integer.MAX_VALUE * Math.random());
        Location loc = block.getLocation().add(0.5, 0, 0.5); // centro del bloque

        // Usa el blockdata real del bloque original
        PacketContainer spawnPacket = protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);

        spawnPacket.getIntegers()
                .write(0, entityId);

        spawnPacket.getUUIDs().write(0, UUID.randomUUID()); // UUID
        spawnPacket.getDoubles().write(0, loc.getX()); // X
        spawnPacket.getDoubles().write(1, loc.getY()); // Y
        spawnPacket.getDoubles().write(2, loc.getZ()); // Z
        spawnPacket.getEntityTypeModifier().write(0, EntityType.FALLING_BLOCK);

        // Block state id para falling_block
        WrappedBlockData blockData = WrappedBlockData.createData(block.getType());
        int blockStateID = blockData.getData();
        spawnPacket.getIntegers().write(4, blockStateID); // SÓLO index 1, no index 5


        // block state ID
        //int blockStateId = (int) blockData.getHandle(); // En 1.13+ esto es safe
        //spawnPacket.getIntegers().write(6, blockStateId);

        // Sin velocidad (puedes agregar motion directo por paquete si quieres)
        //spawnPacket.getShorts().write(0, (short) 0);
        //spawnPacket.getShorts().write(1, (short) 0);
        //spawnPacket.getShorts().write(2, (short) 0);

        try {
            for (Player player : players) {
                protocolManager.sendServerPacket(player, spawnPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Genera una motion aleatoria para el efecto
       // Vector motion = new Vector(Math.random() - 0.5, 0.95, Math.random() - 0.5);

        //return new FakeFallingBlock(entityId, loc, blockData, motion);
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
