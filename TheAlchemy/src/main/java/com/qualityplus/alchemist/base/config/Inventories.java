package com.qualityplus.alchemist.base.config;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
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
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.CustomKey;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Inventories file representation
 */
@Getter
@Setter
@Configuration(path = "inventories.yml")
@Header("================================")
@Header("       Inventories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Inventories extends OkaeriConfig implements DefaultBackgrounds {
    @CustomKey("recipesGUIConfig")
    private RecipesGUIConfig recipesGUIConfig = RecipesGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Recipes",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .recipeItem(ItemBuilder.of(XMaterial.LINGERING_POTION, 1)
                    .title("&e#%alchemist_recipe_id%")
                    .lore(Arrays.asList("", "&e» &7Click to edit this recipe"))
                    .build())
            .nextPage(ItemBuilder.of(XMaterial.BOOK, 52, 1, "&7Next Page", Collections.emptyList()).enabled(true).build())
            .previousPage(ItemBuilder.of(XMaterial.BOOK, 46, 1, "&7Back Page", Collections.emptyList()).enabled(true).build())
            .build();

    @CustomKey("standGUIConfig")
    private AlchemistStandGUIConfig standGUIConfig = AlchemistStandGUIConfig.builder()
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
            .inputSlots(Arrays.asList(38, 40, 42))
            .fuelSlot(13)
            .build();

    @CustomKey("selectItemGUIConfig")
    private SelectItemGUIConfig selectItemGUIConfig = SelectItemGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Select an Item",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            )).build();

    private IndividualRecipeGUIConfig individualRecipeGUIConfig = IndividualRecipeGUIConfig.builder()
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
        final XMaterial black = XMaterial.BLACK_STAINED_GLASS_PANE;
        final XMaterial blue = XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE;

        final String blueTitle = "&7Place Water Bottles or Potions";
        final List<String> blueDescription = Collections.singletonList("&7below to brew.");

        final Item blackPane = ItemBuilder.of(black, 1, " ", Collections.emptyList()).build();
        final Item placeWaterBottles = ItemBuilder.of(blue, 1, blueTitle, blueDescription).build();

        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(0, blackPane)
                .put(1, blackPane)
                .put(2, blackPane)
                .put(3, blackPane)
                .put(4, blackPane)
                .put(5, blackPane)
                .put(6, blackPane)
                .put(7, blackPane)
                .put(8, blackPane)
                .put(9, blackPane)
                .put(10, blackPane)
                .put(11, blackPane)
                .put(12, blackPane)
                .put(14, blackPane)
                .put(15, blackPane)
                .put(16, blackPane)
                .put(17, blackPane)
                .put(18, blackPane)
                .put(19, blackPane)

                .put(20, placeWaterBottles)
                .put(21, placeWaterBottles)
                .put(22, placeWaterBottles)
                .put(23, placeWaterBottles)
                .put(24, placeWaterBottles)
                .put(29, placeWaterBottles)
                .put(31, placeWaterBottles)
                .put(33, placeWaterBottles)

                .put(25, blackPane)
                .put(26, blackPane)
                .put(27, blackPane)
                .put(28, blackPane)
                .put(30, blackPane)
                .put(32, blackPane)
                .put(34, blackPane)
                .put(35, blackPane)
                .put(36, blackPane)
                .put(37, blackPane)
                .put(39, blackPane)
                .put(41, blackPane)
                .put(43, blackPane)
                .put(44, blackPane)
                .put(45, blackPane)
                .put(46, blackPane)
                .put(47, blackPane)
                .put(48, blackPane)
                .put(50, blackPane)
                .put(51, blackPane)
                .put(52, blackPane)
                .put(53, blackPane)
                .build());
    }
}
