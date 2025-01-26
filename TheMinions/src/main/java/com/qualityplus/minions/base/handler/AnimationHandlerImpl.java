package com.qualityplus.minions.base.handler;

import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.handler.AnimationHandler;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.base.minions.entity.getter.DataGetter;
import com.qualityplus.minions.base.minions.entity.mob.MinionMobEntity;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.mob.MinionMob;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public final class AnimationHandlerImpl implements AnimationHandler, DataGetter {
    private final UUID minionUniqueId;
    private final Minion minion;

    @Override
    public CompletableFuture<Block> getBlockToRotate(final ArmorStandHandler handler) {
        return null;
    }

    @Override
    public CompletableFuture<MinionMobEntity> getEntityToRotate(ArmorStandHandler handler) {
        return null;
    }

    private CompletableFuture<Entity> getNearEntity(Location location) {
        CompletableFuture<Entity> future = new CompletableFuture<>();
        MinionMob minionMob = minion.getMinionLayout().getMinionMob();

        try {
            //TODO Check if this is necesarie maybe it's not necesarie to check for primary thread
            // and in terms of optimization is better to don't have it
            if (Bukkit.getServer().isPrimaryThread()) {
                run(location, future, minionMob);
            } else {
                Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> run(location, future, minionMob));
            }

        } catch (Exception e) {
            future.complete(null);
        }

        return future;
    }

    @Override
    public UUID getMinionUniqueId() {
        return minionUniqueId;
    }

    // TODO CHANGE NAME AND OPTIMIZE THIS CALL
    private void run(final Location location, final CompletableFuture<Entity> future, final MinionMob minionMob) {
        final Collection<Entity> entities = Optional.ofNullable(location.getWorld())
                .map(world -> world.getNearbyEntities(location, 2, 2, 2))
                .orElse(Collections.emptyList());

        future.complete(entities.stream()
                .filter(entity -> entity.getType().equals(minionMob.getEntityType()))
                .findFirst().orElse(null));
    }

            /*TODO SUGAR MINION
        int time = 0;
        for (Map.Entry<Integer, VectorSection> sectionEntry : MinionAnimationUtil.SUGAR_WATER_POSITIONS.entrySet()) {

            //AtomicInteger time = new AtomicInteger();

            (new BukkitRunnable() {
                public void run() {

                    sectionEntry.getValue().getFirsts().forEach(vectorr -> {
                        Location newLocation = location.clone().add(vectorr);
                        newLocation.getBlock().setType(XMaterial.DIAMOND_BLOCK.parseMaterial());
                    });

                }
            }).runTaskLater(TheMinions.getInstance(), 20 + time);

            time+=10;
        }
        */
}
