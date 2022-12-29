package com.qualityplus.assistant.api.util;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Map;

@UtilityClass
public class CropUtil {
    private final Map<XMaterial, Integer> MAX_AGE = ImmutableMap.<XMaterial, Integer>builder()
            .put(XMaterial.WHEAT, 7)
            .put(XMaterial.CARROTS, 7)
            .put(XMaterial.POTATOES, 3)
            .put(XMaterial.NETHER_WART, 3)
            .build();

    public int getMaxAge(Block block){
        if(block == null) return 0;

        return getMaxAge(block.getType());
    }

    public int getMaxAge(Material mat){
        if(mat == null) return 0;

        XMaterial material = XMaterial.matchXMaterial(mat);

        return MAX_AGE.getOrDefault(material, 0);
    }

    public Material cropFromXMaterial(XMaterial material){
        if (XMaterial.getVersion() > 13)
            return material.parseMaterial();
        else
            try {
                return Material.valueOf(material.getLegacy()[0]);
            }catch (Exception e){
                return null;
            }
    }
}
