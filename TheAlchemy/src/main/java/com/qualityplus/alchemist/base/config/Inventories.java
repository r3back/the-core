package com.qualityplus.alchemist.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.alchemist.base.gui.brewing.AlchemistStandGUIConfig;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUIConfig;
import com.qualityplus.alchemist.base.gui.recipes.RecipesGUIConfig;
import com.qualityplus.alchemist.base.gui.select.SelectItemGUIConfig;
import com.qualityplus.alchemist.base.stand.StandEffect;
import com.qualityplus.alchemist.base.stand.StandEffects;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.background.Background;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Configuration(path = "inventories.yml")
@Header("================================")
@Header("       Inventories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Inventories extends OkaeriConfig implements DefaultBackgrounds {
    @CustomKey("recipesGUIConfig")
    public RecipesGUIConfig recipesGUIConfig = RecipesGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Recipes",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .recipeItem(ItemBuilder.of(XMaterial.LINGERING_POTION, 1, "&e#%alchemist_recipe_id%", Arrays.asList("", "&e» &7Click to edit this recipe")).build())
            .nextPage(ItemBuilder.of(XMaterial.BOOK, 52, 1, "&7Next Page", Collections.emptyList()).enabled(true).build())
            .previousPage(ItemBuilder.of(XMaterial.BOOK, 46, 1, "&7Back Page", Collections.emptyList()).enabled(true).build())
            .build();

    @CustomKey("standGUIConfig")
    public AlchemistStandGUIConfig standGUIConfig = AlchemistStandGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Brewing Stand",
                    54,
                    getStandBackground(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .standEffects(new StandEffects(ImmutableMap.<Integer, List<StandEffect>>builder()
                    .put(0, Arrays.asList(
                            new StandEffect(20, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(21, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(22, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(23, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(24, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(29, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(31, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(33, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                            ))
                    .put(1, Collections.singletonList(
                            new StandEffect(22, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                    ))
                    .put(2, Arrays.asList(
                            new StandEffect(21, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(23, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(31, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                            ))
                    .put(3, Arrays.asList(
                            new StandEffect(20, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(24, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                            ))
                    .put(4, Arrays.asList(
                            new StandEffect(29, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(31, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(33, ItemBuilder.of(XMaterial.ORANGE_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                            ))
                    .put(5, Collections.singletonList(
                            new StandEffect(22, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                    ))
                    .put(6, Arrays.asList(
                            new StandEffect(21, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(23, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(31, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                    ))
                    .put(7, Arrays.asList(
                            new StandEffect(20, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build()),
                            new StandEffect(24, ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%remaining_time%s", Collections.emptyList()).build())
                    ))
                    .build()

            ))
            .inputSlots(Arrays.asList(38,40,42))
            .fuelSlot(13)
            .build();

    @CustomKey("selectItemGUIConfig")
    public SelectItemGUIConfig selectItemGUIConfig = SelectItemGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Select an Item",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            )).build();

    public IndividualRecipeGUIConfig individualRecipeGUIConfig = IndividualRecipeGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Edit Recipe",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .emptyItem(XMaterial.BARRIER)
            .infoItem(ItemBuilder.of(XMaterial.PAPER, 13, 1, "&e%alchemist_recipe_displayname%", Arrays.asList(
                    "&7%alchemist_recipe_description%",
                    "",
                    "&eRecipe Id: &6%alchemist_recipe_id%",
                    "&ePermission: &6%alchemist_recipe_permission%",
                    "",
                    "&e» &7Click to delete this recipe!")).build())
            .fuelItem(ItemBuilder.of(XMaterial.STONE, 28, 1, "&e%alchemist_recipe_fuel_item_displayname%", Arrays.asList("",
                    "&7%alchemist_recipe_fuel_item_lore%",
                    "&e————————————————————",
                    "&7This is the item you need to put",
                    "&7as fuel in order to make a brew",
                    "&7a recipe.",
                    "",
                    "&e» &7Click to change this item!")).build())
            .inputItem(ItemBuilder.of(XMaterial.STONE, 30, 1, "&e%alchemist_recipe_input_item_displayname%", Arrays.asList("",
                    "&7%alchemist_recipe_input_item_lore%",
                    "&e————————————————————",
                    "&7This is the item you need to put",
                    "&7in order to get output item.",
                    "",
                    "&e» &7Click to change this item!")).build())
            .outputItem(ItemBuilder.of(XMaterial.STONE, 32, 1, "&e%alchemist_recipe_output_item_displayname%", Arrays.asList("",
                    "&7%alchemist_recipe_output_item_lore%",
                    "&e————————————————————",
                    "&7This is the item you get when recipe",
                    "&7duration time ends",
                    "",
                    "&e» &7Click to change this item!")).build())
            .durationItem(ItemBuilder.of(XMaterial.CLOCK, 34, 1, "&eRecipe Duration", Arrays.asList("",
                    "&7Duration: &e%alchemist_recipe_duration% &6seconds",
                    "&e————————————————————",
                    "&7This is the time that a recipe",
                    "&7takes to be done.",
                    "",
                    "&e» &7Left Click to add 1",
                    "&e» &7Right Click to remove 1",
                    "&e» &7Press Shift to make from 5 in 5",
                    "",
                    "&e» &7Click to change this item!")).build())
            .backPage(ItemBuilder.of(XMaterial.BOOK, 48, 1, "&7Back Page", Arrays.asList("", "&e» &7Click to view all recipes")).enabled(true).build())
            .build();

    private Background getStandBackground() {
        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(0, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(1, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(2, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(3, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(4, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(5, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(6, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(7, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(8, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(9, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(10, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(11, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(12, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(14, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(15, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(16, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(17, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(18, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(19, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())


                .put(20, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(21, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(22, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(23, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(24, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(29, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(31, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())
                .put(33, ItemBuilder.of(XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "&7Place Water Bottles or Potions", Collections.singletonList("&7below to brew.")).build())

                .put(25, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(26, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(27, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(28, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(30, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(32, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(34, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(35, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(36, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(37, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(39, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(41, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(43, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(44, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(45, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(53, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .build());
    }
}
