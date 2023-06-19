package com.qualityplus.minions.base.minions.entity.type;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XBlock;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.CropUtil;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.minions.animations.BreakAnimation;
import com.qualityplus.minions.base.minions.animations.PlaceAnimation;
import com.qualityplus.minions.base.minions.entity.ArmorStandMinion;
import com.qualityplus.minions.base.minions.minion.Minion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.Optional;
import java.util.UUID;

public final class CropBreakMinion extends ArmorStandMinion<Block> {
    private CropBreakMinion(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded) {
        super(minionUniqueId, owner, minion, loaded);
    }

    public static CropBreakMinion create(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded){
        return new CropBreakMinion(minionUniqueId, owner, minion, loaded);
    }

    @Override
    protected void checkBlockAfterRotate(Block block){
        armorStand.manipulateEntity(entity -> {
            if(!cropHasMaxLevel(block))
                PlaceAnimation.start(() -> doIfItsNull(block), entity);
            else
                BreakAnimation.start(() -> doIfItsNotNull(block), entity, getCropBlock(block));
        });
    }

    @Override
    public void doIfItsNull(Block block){
        XMaterial material = minion.getMinionLayout().getToReplaceBlock();
        XMaterial crop = minion.getMinionLayout().getToReplaceCrop();

        Block toPlace = getCropBlock(block);

        if(BlockUtils.isNull(toPlace)){
            Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {
                BlockUtils.setBlock(block, material);

                Optional.ofNullable(CropUtil.cropFromXMaterial(crop)).ifPresent(toPlace::setType);

                XBlock.setAge(toPlace, 0);
            });
        }else{
            Bukkit.getScheduler().runTask(TheMinions.getInstance(), () -> {
                int age = TheAssistantPlugin.getAPI().getNms().getAge(toPlace);

                XBlock.setAge(toPlace, age + 1);
            });
        }

        teleportBack();
    }

    @Override
    public void doIfItsNotNull(Block block){
        Block toPlace = getCropBlock(block);

        if(toPlace == null || toPlace.getType().equals(Material.AIR)){
            teleportBack();
            return;
        }

        BlockUtils.setBlock(toPlace, XMaterial.AIR);

        addItemsToMinionInventory();

        teleportBack();
    }

    private boolean cropHasMaxLevel(Block block){
        Block toPlace = getCropBlock(block);

        if(BlockUtils.isNull(toPlace)) return false;

        int age = TheAssistantPlugin.getAPI().getNms().getAge(toPlace);
        int maxAge = TheAssistantPlugin.getAPI().getNms().getMaxAge(toPlace);

        return age >= maxAge;
    }

    private Block getCropBlock(Block below){
        return below.getLocation().clone().add(new Vector(0,1,0)).getBlock();
    }
}
