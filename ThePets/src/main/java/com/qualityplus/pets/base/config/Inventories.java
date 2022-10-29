package com.qualityplus.pets.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.pets.gui.main.PetsGUIConfig;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@Configuration(path = "inventories.yml")
@Header("================================")
@Header("       Inventories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Inventories extends OkaeriConfig implements DefaultBackgrounds {
    /*
       private Item petItem;
    private Item petMenuInfoItem;
    private Item convertPetToItemEnabled;
    private Item convertPetToItemDisabled;
    private Item hidePetsShown;
    private Item hidePetsHidden;
     */
    @CustomKey("mainGUI")
    public PetsGUIConfig mainGUIConfig = new PetsGUIConfig(
            new CommonGUI(
                    "Main Menu",
                    54,
                    getBackGround3(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.STONE, 1, "&a%pet_egg_egg_displayname%", Arrays.asList("",
                    "&7%pet_egg_description%",
                    "",
                    "&7Progress to next level: &e%pet_level_progress%%",
                    "%pet_action_bar% &e%pet_xp%&6/&e%pet_max_xp%",
                    "",
                    "%pet_summon_status%!")).build(),
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
}
