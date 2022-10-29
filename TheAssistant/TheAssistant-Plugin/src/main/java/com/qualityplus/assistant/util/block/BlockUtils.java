package com.qualityplus.assistant.util.block;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.block.Block;

@UtilityClass
public class BlockUtils {
    public boolean isNull(Block block){
        return block == null || block.getType().equals(Material.AIR);
    }
    public boolean isNotNull(Block block){
        return !isNull(block);
    }
}
