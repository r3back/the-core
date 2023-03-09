package com.qualityplus.minions.base.minions.entity.type;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.base.minions.animations.BreakAnimation;
import com.qualityplus.minions.base.minions.animations.PlaceAnimation;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.entity.ArmorStandMinion;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public final class BlockBreakMinion extends ArmorStandMinion<Block> {

    private BlockBreakMinion(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded) {
        super(minionUniqueId, owner, minion, loaded);
    }

    public static BlockBreakMinion create(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded){
        return new BlockBreakMinion(minionUniqueId, owner, minion, loaded);
    }

    @Override
    protected void checkBlockAfterRotate(Block block) {
        if (!state.isLoaded()) {
            addItemsToMinionInventory();
            return;
        }

        armorStand.manipulateEntity(entity -> handleAnimationCallBack(entity, block));
    }

    @Override
    public void doIfItsNull(Block block){
        XMaterial material = minion.getMinionLayout().getToReplaceBlock();

        BlockUtils.setBlock(block, material);

        teleportBack();
    }

    @Override
    public void doIfItsNotNull(Block block){
        if(block == null || block.getType().equals(Material.AIR)){
            teleportBack();
            return;
        }

        BlockUtils.setBlock(block, XMaterial.AIR);

        addItemsToMinionInventory();

        teleportBack();
    }

    private void handleAnimationCallBack(ArmorStand entity, Block block){
        Material material = minion.getMinionLayout().getToReplaceBlock().parseMaterial();

        if (BlockUtils.isNull(block) || !block.getType().equals(material))
            this.breakingAnimation = PlaceAnimation.start(() -> doIfItsNull(block), entity);
        else
            this.breakingAnimation = BreakAnimation.start(() -> doIfItsNotNull(block), entity, block);

    }

}
