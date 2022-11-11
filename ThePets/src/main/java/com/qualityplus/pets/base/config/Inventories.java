package com.qualityplus.pets.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.pets.gui.main.PetsGUIConfig;
import com.qualityplus.pets.gui.sub.PetLevelsGUIConfig;
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
    public PetsGUIConfig mainGUIConfig = new PetsGUIConfig(
            new CommonGUI(
                    "Main Menu",
                    54,
                    getBackGround3(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.PLAYER_HEAD, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet", "",
                    "&7%pet_description_gui%",
                    "",
                    "&7Progress to next level: &e%pet_level_progress%%",
                    "%pet_action_bar% &e%pet_xp%&6/&e%pet_max_xp%",
                    "",
                    "%pet_summon_status%!", "&eRight-Click to view!")).build(),
            ItemBuilder.of(XMaterial.BONE,  4, 1, "&aPets", Arrays.asList(
                    "&7View and manage all of your",
                    "&7Pets.",
                    "",
                    "&7Level up your pets faster by",
                    "&7gaining xp in their favorite",
                    "&7skill!",
                    "",
                    "&7Selected Pet: %pet_selected_pet%")).build(),

            ItemBuilder.of(XMaterial.LIME_DYE,  50, 1, "&aConvert Pet to an Item", Arrays.asList("&7Enable this setting and click", "&7any pet to convert it to an", "&7item.", "", "&aEnabled")).build(),
            ItemBuilder.of(XMaterial.GRAY_DYE,  50, 1, "&aConvert Pet to an Item", Arrays.asList("&7Enable this setting and click", "&7any pet to convert it to an", "&7item.", "", "&cDisabled")).build(),
            ItemBuilder.of(XMaterial.STONE_BUTTON,  51, 1, "&aHide Pets", Arrays.asList(
                    "&7Hide all pets which are little",
                    "&7heads from being visible in the",
                    "&7world.",
                    "",
                    "&7Pet effects remain active.",
                    "",
                    "&7Currently: &aPets shown!",
                    "&7Selected Pet: &c%pet_selected_pet%",
                    "",
                    "&eClick to hide!")).build(),
            ItemBuilder.of(XMaterial.STONE_BUTTON,  51, 1, "&cHide Pets", Arrays.asList(
                    "&7Hide all pets which are little",
                    "&7heads from being visible in the",
                    "&7world.",
                    "",
                    "&7Pet effects remain active.",
                    "",
                    "&7Currently: &cPets hidden!",
                    "&7Selected Pet: &a%pet_selected_pet%",
                    "",
                    "&eClick to show!")).build(),
            Arrays.asList(10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34)

    );

    @CustomKey("petLevelsGUI")
    public PetLevelsGUIConfig petLevelsGUIConfig = new PetLevelsGUIConfig(
            new CommonGUI(
                    "%pet_egg_displayname% &8Pet (%current_page%/%max_page%)",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet",
                    "",
                    "&7%pet_description_gui%",
                    "",
                    "&7Progress to next level: &e%pet_level_progress%%",
                    "%pet_action_bar% &e%pet_xp%&6/&e%pet_max_xp%",
                    "")).build(),
            ItemBuilder.of(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet", "", "&7%pet_description_gui%")).build(),
            ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet", "", "&7%pet_description_gui%")).build(),

            ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aLevels 1 - 25", Collections.singletonList("&eClick to view")).build(),
            ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aLevels 26 - 50", Collections.singletonList("&eClick to view")).build(),
            ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aGo Back", Collections.singletonList("&7To Your Pets")).build(),
            Arrays.asList(9,18,27,28,29,20,11,2,3,4,13,22,31,32,33,24,15,6,7,8,17,26,35,44,53),
            ItemBuilder.of(XMaterial.PLAYER_HEAD,  0, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet", "",
                    "&7%pet_description_gui%",
                    "",
                    "&7Progress to next level: &e%pet_level_progress%%",
                    "%pet_action_bar% &e%pet_xp%&6/&e%pet_max_xp%"
                    )).build()
    );
}
