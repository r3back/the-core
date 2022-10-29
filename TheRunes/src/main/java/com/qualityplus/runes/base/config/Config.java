package com.qualityplus.runes.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.runes.api.config.RuneTableConfig;
import com.qualityplus.runes.base.table.effects.RuneTableEffect;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import com.qualityplus.runes.api.config.RuneTableConfig.RuneItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[TheRunes] ";
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");

    public RuneTableConfig runeTableConfig = RuneTableConfig.builder()
            .centerItem(new RuneItem(XMaterial.PLAYER_HEAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQyY2RhOWY0YWJlYWFkOGQzODdjNTA1NWI1ZWFlNjE2M2ZlNzkyZDg2MTAzYTJmMjlmNTMyMGNiMjllNDg3MiJ9fX0="))
            .baseItem(XMaterial.COBBLESTONE_WALL)
            .cornerItem(new RuneItem(XMaterial.STONE_SLAB, null))
            .build();

    public Item runeTableItem = ItemBuilder.of(XMaterial.PLAYER_HEAD, 1, "&dRuneTable &e(Right Click to Place)", Arrays.asList(""))
            .headData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQyY2RhOWY0YWJlYWFkOGQzODdjNTA1NWI1ZWFlNjE2M2ZlNzkyZDg2MTAzYTJmMjlmNTMyMGNiMjllNDg3MiJ9fX0=")
            .build();

    public Item runeItem = ItemBuilder.of(XMaterial.STONE, 1, "%rune_displayname% %rune_level%", Arrays.asList("%rune_description%", "",
                    "&7Apply this rune to weapons or",
                    "&7fuse two together at the Runic",
                    "&7Pedestal",
                    "",
                    "&f&lCOMMON"))
            .build();
    public Item removerToolItem = ItemBuilder.of(XMaterial.BLAZE_ROD, 1, "&dRemover Tool &e(Right Click to remove)", Arrays.asList(""))
            .build();

    public Map<Integer, Double> xpForItemAndRune = ImmutableMap.<Integer,Double>builder()
            .put(1, 5D)
            .put(2, 10D)
            .put(3, 15D)
            .put(4, 20D)
            .put(5, 25D)
            .build();

    public Map<Integer, Double> xpForRuneAndRune = ImmutableMap.<Integer,Double>builder()
            .put(1, 5D)
            .put(2, 10D)
            .put(3, 15D)
            .put(4, 20D)
            .put(5, 25D)
            .build();

    public boolean removeRuneTableFromInventoryOnPlace = true;
}
