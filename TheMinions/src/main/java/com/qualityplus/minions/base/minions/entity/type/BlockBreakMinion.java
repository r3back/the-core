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

import java.util.*;

public final class BlockBreakMinion extends ArmorStandMinion {
    private BlockBreakMinion(UUID minionUniqueId, UUID owner, Minion minion) {
        super(minionUniqueId, owner, minion);
    }

    public static BlockBreakMinion create(UUID minionUniqueId, UUID owner, Minion minion){
        return new BlockBreakMinion(minionUniqueId, owner, minion);
    }

    @Override
    protected void checkBlockAfterRotate(Block block){
        Material material = minion.getMinionLayout().getToReplaceBlock().parseMaterial();

        ArmorStand entity = armorStand.getEntity();

        if(BlockUtils.isNull(block) || !block.getType().equals(material))
            PlaceAnimation.run(entity, () -> doIfBlockIfNull(block));
        else
            BreakAnimation.run(entity, block, () -> doIfBlockIsNotNull(block));
    }

    @Override
    public void doIfBlockIfNull(Block block){
        XMaterial material = minion.getMinionLayout().getToReplaceBlock();

        BlockUtils.setBlock(block, material);

        teleportBack();
    }

    @Override
    public void doIfBlockIsNotNull(Block block){
        BlockUtils.setBlock(block, XMaterial.AIR);

        addItemsToMinionInventory();

        teleportBack();
    }

}
