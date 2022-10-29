package com.qualityplus.runes.util;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.runes.api.enchantment.legacy.RuneEnchantLegacy;
import com.qualityplus.runes.api.enchantment.newest.RuneEnchantNewest;
import lombok.experimental.UtilityClass;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;

@UtilityClass
public class GlowFinderUtil {

    private final static Enchantment GLOW_ENCHANTMENT;

    static {
        GLOW_ENCHANTMENT = XMaterial.getVersion() >= 13 ? new RuneEnchantNewest() : new RuneEnchantLegacy();

        if(XMaterial.getVersion() < 13){
            try {
                Field field = Enchantment.class.getDeclaredField("acceptingNew");
                field.setAccessible(true);
                field.set(null, true);
                Enchantment.registerEnchantment(GLOW_ENCHANTMENT);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public Enchantment getEnchantment(){
        return GLOW_ENCHANTMENT;
    }
}
