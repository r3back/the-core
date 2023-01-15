package com.qualityplus.minions.api.handler;

import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import java.util.concurrent.CompletableFuture;

public interface AnimationHandler {
    CompletableFuture<Block> getBlockToRotate(ArmorStandHandler armorStand);
}
