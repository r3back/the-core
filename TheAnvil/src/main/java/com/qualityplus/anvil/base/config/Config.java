package com.qualityplus.anvil.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[TheAnvil] ";
    @Comment("Mark as true if you want to open plugin's gui")
    @Comment("with the vanilla anvil")
    public boolean openAsVanillaAnvil = true;
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");
    public List<XMaterial> allowedItems = getMaterials();

    private List<XMaterial> getMaterials(){
        List<XMaterial> allowed = new ArrayList<>();
        for(XMaterial material : XMaterial.VALUES){
            String parsed = material.toString().toLowerCase();
            if(parsed.contains("waxed")) continue;
            if(parsed.contains("axe") || parsed.contains("pickaxe") || parsed.contains("shovel") || parsed.contains("hoe") || parsed.contains("sword") || parsed.contains("leggings") ||
               parsed.contains("chestplate") || parsed.contains("boots") || parsed.contains("helmet"))
                allowed.add(material);
        }
        allowed.add(XMaterial.ENCHANTED_BOOK);
        return allowed;
    }
}
