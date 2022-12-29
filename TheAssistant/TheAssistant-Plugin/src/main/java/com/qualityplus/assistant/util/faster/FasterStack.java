package com.qualityplus.assistant.util.faster;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


@UtilityClass
public class FasterStack extends OkaeriConfig {
    public ItemStack fast(XMaterial material){
        return fast(material, 1);
    }

    public ItemStack fast(XMaterial material, int amount){
        return new ItemStack(material.parseMaterial(), amount);
    }

    public ItemStack fastWithColor(XMaterial material, Color color){
        ItemStack item = material.parseItem();

        if(color == null) return item;

        ItemMeta meta = item.getItemMeta();
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(color);
        item.setItemMeta(leatherArmorMeta);

        return item;
    }
}
