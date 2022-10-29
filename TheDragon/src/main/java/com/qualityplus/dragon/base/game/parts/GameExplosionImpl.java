package com.qualityplus.dragon.base.game.parts;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.dragon.api.game.part.GameExplosion;
import com.qualityplus.dragon.base.configs.Config;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.scheduler.PlatformScheduler;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Component
public final class GameExplosionImpl implements GameExplosion {
    private final List<FallingBlock> fallingBlocks = new ArrayList<>();
    private @Inject("scheduler") PlatformScheduler scheduler;
    private CompletableFuture<Void> future;
    private @Inject Config config;
    private @Inject Tasker tasker;

    @Override
    public CompletableFuture<Void> makeBlockExplosion(PasterSession session) {
        this.future = new CompletableFuture<>();

        spawnFallingBlocks(session).thenRun(this::clearBlocks);

        return future;
    }

    private CompletableFuture<Void> spawnFallingBlocks(PasterSession session){
        CompletableFuture<Void> future1 = new CompletableFuture<>();

        /*tasker.newChain()
                        .sync(() -> spawnIndividualBlocks(session))
                        .acceptSync((Consumer<Object>) value -> future1.complete(null))
                        .execute();*/

        scheduler.runSync(() -> {
            Optional.ofNullable(session).ifPresent(s -> s.getAllBlocks().forEach(this::spawnIndividualBlock));

            future1.complete(null);
        });

        return future1;
    }



    private void spawnIndividualBlock(Block block){
        Material material = block.getType();

        byte data = block.getData();

        block.setType(Material.AIR);

        if (!ThreadLocalRandom.current().nextBoolean()) return;

        FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), material, data);

        fallingBlock.setVelocity(new Vector(randomDouble(), 0.95D, randomDouble()));

        fallingBlock.setDropItem(false);

        fallingBlocks.add(fallingBlock);

    }

    private void clearBlocks(){
        scheduler.runLater(this::clearBlocksTask, 60, false);
    }

    private void clearBlocksTask(){
        fallingBlocks.forEach(this::clearIndividualFallingBlock);
        fallingBlocks.clear();
        future.complete(null);
    }

    private void clearIndividualFallingBlock(FallingBlock fallingBlock) {
        if(fallingBlock.isOnGround()) fallingBlock.getLocation().getBlock().setType(Material.AIR);

        if(!fallingBlock.isDead()) fallingBlock.remove();
    }


    private double randomDouble() {
        return -0.5D + ThreadLocalRandom.current().nextDouble();
    }
}
