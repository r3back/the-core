package com.qualityplus.collections.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.collections.gui.category.CategoryGUIConfig;
import com.qualityplus.collections.gui.main.MainGUIConfig;
import com.qualityplus.collections.gui.collection.CollectionGUIConfig;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
@Configuration(path = "inventories.yml")
@Header("================================")
@Header("       Inventories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Inventories extends OkaeriConfig implements DefaultBackgrounds {
    @CustomKey("mainGUI")
    public MainGUIConfig mainGUIConfig = new MainGUIConfig(
            new CommonGUI(
                    "Main Menu",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.STONE, 1, "&a%collection_category_displayname% Collection", Arrays.asList("",
                    "&7View your %collection_category_displayname% Collection!",
                    "",
                    "%collection_category_is_maxed_out%: &e%collection_category_level_progress%%",
                    "%collection_category_action_bar% &e%collection_category_xp%&6/&e%collection_category_max_xp%",
                    "",
                    "&eClick to view!")).build(),
            ItemBuilder.of(XMaterial.PAINTING,  4, 1, "&aCollection", Arrays.asList("&7View all of the items available", "&7in SkyBlock. Collect ore of an",
                            "&7item to unlock rewards on your", "&7way to becoming a master of",
                            "&7SkyBlock!",
                            "",
                            "&7Collection Unlocked: &e%collection_all_categories_progress%",
                            "%collection_all_categories_action_bar% &e%collection_all_categories_xp%&6/&e%collection_all_categories_max_xp%",
                            "",
                            "&eClick to show rankings!"
                            ))
                    .build()
    );

    @CustomKey("categoryGUI")
    public CategoryGUIConfig categoryGUIConfig = new CategoryGUIConfig(
            new CommonGUI(
                    "Main Menu",
                    54,
                    getBackGround3(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.STONE, 1, "&a%collection_displayname%", Arrays.asList("",
                    "&7View all your %collection_displayname%",
                    "&7Collection progress and rewards!",
                    "",
                    "&7Progress to %collection_displayname% %collection_next_level_roman%: &e%collection_level_progress%%",
                    "%collection_action_bar% &e%collection_xp%&6/&e%collection_max_xp%",
                    "",
                    "&eClick to view!")).build(),
            ItemBuilder.of(XMaterial.GRAY_DYE, 1, "&c%collection_displayname% Collection", Arrays.asList(
                            "&7Find this item to add it to your",
                            "&7collection and unlock collection",
                            "&7rewards!"
                    ))
                    .build(),
            ItemBuilder.of(XMaterial.STONE,  4, 1, "&a%collection_category_displayname% Collection", Arrays.asList(
                            "&7View your %collection_category_displayname% Collection!",
                            "",
                            "%collection_category_is_maxed_out%: &e%collection_category_level_progress%%",
                            "%collection_category_action_bar% &e%collection_category_xp%&6/&e%collection_category_max_xp%"
                    ))
                    .build(),
            ItemBuilder.of(XMaterial.ARROW,  48, 1, "&eGo Back", Collections.singletonList(
                            "&7Click to go back."
                    ))
                    .build()
    );

    @CustomKey("collectionGUI")
    public CollectionGUIConfig collectionGUIConfig = new CollectionGUIConfig(
            new CommonGUI(
                    "%collection_displayname% Collection",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&e%collection_displayname% %collection_level_roman%", Arrays.asList(
                    "",
                    "&7Progress to next level: &e%collection_level_progress%%",
                    "%collection_action_bar% &e%collection_xp%&6/&e%collection_max_xp%",
                    "",
                    "%collection_info_gui%")).build(),
            ItemBuilder.of(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&a%collection_displayname% %collection_level_roman%", Arrays.asList(
                    "",
                    "&7Progress to next level: &e%collection_level_progress%%",
                    "%collection_action_bar% &e%collection_xp%&6/&e%collection_max_xp%",
                    "",
                    "%collection_info_gui%")).build(),
            ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE, 1, "&c%collection_displayname% %collection_level_roman%", Arrays.asList(
                    "",
                    "&7Progress to next level: &e%collection_level_progress%%",
                    "%collection_action_bar% &e%collection_xp%&6/&e%collection_max_xp%",
                    "",
                    "%collection_info_gui%")).build(),

            ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aGo Back", Collections.singletonList("&7To %collection_category_displayname%")).build(),
            Arrays.asList(18,19,20,21,22,23,24,25,26),
            ItemBuilder.of(XMaterial.STONE,  4, 1, "&a%collection_displayname% %collection_level_roman%", Arrays.asList(
                    "&7View all your %collection_displayname%",
                    "&7Collection progress and rewards!",
                    "",
                    "&7Total Collected: &e%collection_xp%")).build()
    );

}
