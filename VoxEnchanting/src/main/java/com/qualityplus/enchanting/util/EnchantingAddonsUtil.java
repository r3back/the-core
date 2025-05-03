package com.qualityplus.enchanting.util;

import com.qualityplus.assistant.TheAssistantPlugin;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnchantingAddonsUtil {
    public static final Boolean ECO_ENCHANTMENTS;
    public static final Boolean ADVANCED_ENCHANTMENTS;

    static {
        ECO_ENCHANTMENTS = TheAssistantPlugin.getAPI().getDependencyResolver().isPlugin("EcoEnchants");
        ADVANCED_ENCHANTMENTS = TheAssistantPlugin.getAPI().getDependencyResolver().isPlugin("AdvancedEnchantments");
    }

}
