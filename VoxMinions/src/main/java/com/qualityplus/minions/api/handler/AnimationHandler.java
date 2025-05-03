package com.qualityplus.minions.api.handler;

import com.qualityplus.minions.base.minions.entity.mob.MinionMobEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.concurrent.CompletableFuture;

public interface AnimationHandler {
    CompletableFuture<Block> getBlockToRotate(ArmorStandHandler armorStand);

    CompletableFuture<MinionMobEntity> getEntityToRotate(ArmorStandHandler armorStand);
}
