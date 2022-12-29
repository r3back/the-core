package com.qualityplus.assistant.util.block;

import com.cryptomorin.xseries.XMaterial;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class BlockUtils {
    public boolean isNull(Block block){
        return block == null || block.getType().equals(Material.AIR);
    }

    public boolean isNotNull(Block block){
        return !isNull(block);
    }

    public void setBlock(Block block, XMaterial material){
        Material mat = material.parseMaterial();

        if(mat == null) return;

        block.setType(mat);
    }

    public void setBlock(Block block, ItemStack itemStack){

        try {
            Material mat = itemStack.getType();

            block.setType(mat);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
