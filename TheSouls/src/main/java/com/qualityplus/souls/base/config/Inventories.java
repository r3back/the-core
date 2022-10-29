package com.qualityplus.souls.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.souls.base.gui.addcommands.AddCommandsGUIConfig;
import com.qualityplus.souls.base.gui.addmessages.AddMessagesGUIConfig;
import com.qualityplus.souls.base.gui.allsouls.AllSoulsGUI;
import com.qualityplus.souls.base.gui.allsouls.AllSoulsGUIConfig;
import com.qualityplus.souls.base.gui.confirmdelete.ConfirmDeleteGUIConfig;
import com.qualityplus.souls.base.gui.editgui.SoulsEditGUIConfig;
import com.qualityplus.souls.base.gui.tia.TiaGUIConfig;
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
    @CustomKey("allSoulsGUI")
    public AllSoulsGUIConfig allSoulsGUI = AllSoulsGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Fairy Souls",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .backPageItem(ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aPrevious Page", Collections.singletonList("&8Page %previous_page%")).build())
            .nextPageItem(ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aNext Page", Collections.singletonList("&8Page %next_page%")).build())

            .soulItem(ItemBuilder.of(XMaterial.PLAYER_HEAD, 1, "&eSoul", Arrays.asList("", "&6Location:",
                    "&7X: &e%soul_location_x%",
                    "&7Y: &e%soul_location_y%",
                    "&7Z: &e%soul_location_z%",
                    "&7World: &e%soul_location_world%",
                    "",
                    "&e» Click to edit this soul"
            )).headData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=").build())
            .soulsPerPage(43)
            .build();

    @CustomKey("soulsEditGUI")
    public SoulsEditGUIConfig soulsEditGUI = SoulsEditGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Soul Edition",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .goBackItem(ItemBuilder.of(XMaterial.ARROW,  31, 1, "&aGo Back", Collections.singletonList("&7Click to go back")).build())
            .teleportItem(ItemBuilder.of(XMaterial.GRASS_BLOCK,  11, 1, "&eTeleport to the Soul", Arrays.asList("", "&6» &7Click to teleport to the soul")).build())
            .addCommandItem(ItemBuilder.of(XMaterial.COMMAND_BLOCK,  12, 1, "&eAdd Command", Arrays.asList("", "&6» &7Click to add a command to the soul")).build())
            .addMessageItem(ItemBuilder.of(XMaterial.BOOK,  13, 1, "&eAdd Message", Arrays.asList("", "&6» &7Click to add a message to the soul")).build())

            .deleteItem(ItemBuilder.of(XMaterial.BARRIER,  15, 1, "&eDelete Soul", Arrays.asList("", "&6» &7Click to delete this soul")).build())

            .build();

    @CustomKey("confirmDeleteGUI")
    public ConfirmDeleteGUIConfig confirmDeleteGUI = ConfirmDeleteGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Confirm Deletion",
                    36,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .goBackItem(ItemBuilder.of(XMaterial.ARROW,  31, 1, "&aGo Back", Collections.singletonList("&7Click to go back")).build())
            .cancelItem(ItemBuilder.of(XMaterial.RED_WOOL,  11, 1, "&cCancel", Collections.singletonList("&7Click to cancel soul deletion")).build())
            .confirmItem(ItemBuilder.of(XMaterial.LIME_WOOL,  15, 1, "&aConfirm", Collections.singletonList("&7Click to confirm soul deletion")).build())

            .build();

    @CustomKey("addMessagesGUI")
    public AddMessagesGUIConfig addMessagesGUIConfig = AddMessagesGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Soul Messages",
                    36,
                    getBackGround5(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .goBackItem(ItemBuilder.of(XMaterial.ARROW,  30, 1, "&aGo Back", Collections.singletonList("&7Click to go back")).build())
            .messageItem(ItemBuilder.of(XMaterial.BOOK, 1, "&aMessage: %soul_message%", Collections.singletonList("&7Right-Click to remove this message")).build())
            .addMessageItem(ItemBuilder.of(XMaterial.LIME_DYE,  31, 1, "&aAdd a new Message", Collections.singletonList("&7Click to add a new message")).build())
            .messagesSlots(Arrays.asList(10,11,12,13,14,15,16,19,20,21,22,23,24,25))
            .previousPageItem(ItemBuilder.of(XMaterial.ARROW,  27, 1, "&aPrevious Page", Collections.singletonList("&8Page %previous_page%")).build())
            .nextPageItem(ItemBuilder.of(XMaterial.ARROW,  35, 1, "&aNext Page", Collections.singletonList("&8Page %next_page%")).build())
            .build();

    @CustomKey("addCommandsGUI")
    public AddCommandsGUIConfig addCommandsGUIConfig = AddCommandsGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Soul Messages",
                    36,
                    getBackGround5(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).enabled(false).build()
            ))
            .goBackItem(ItemBuilder.of(XMaterial.ARROW,  30, 1, "&aGo Back", Collections.singletonList("&7Click to go back")).build())
            .commandItem(ItemBuilder.of(XMaterial.COMMAND_BLOCK, 1, "&aCommand: %soul_command%", Collections.singletonList("&7Right-Click to remove this command")).build())
            .addCommandItem(ItemBuilder.of(XMaterial.LIME_DYE,  31, 1, "&aAdd a new Command", Collections.singletonList("&7Click to add a new command")).build())
            .commandsSlots(Arrays.asList(10,11,12,13,14,15,16,19,20,21,22,23,24,25))
            .previousPageItem(ItemBuilder.of(XMaterial.ARROW,  27, 1, "&aPrevious Page", Collections.singletonList("&8Page %previous_page%")).build())
            .nextPageItem(ItemBuilder.of(XMaterial.ARROW,  35, 1, "&aNext Page", Collections.singletonList("&8Page %next_page%")).build())
            .build();

    @CustomKey("tiaGUI")
    public TiaGUIConfig tiaGUI = TiaGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Fairy",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Collections.emptyList()).build()
            ))
            .tiaItem(ItemBuilder.of(XMaterial.PLAYER_HEAD,  22, 1, "&aExchange Fairy Souls", Arrays.asList(
                    "&7Find &dFairy Souls &7around the",
                    "&7world and bring them back to me",
                    "&7and I will reward you with",
                    "&7permanent stat boosts!",
                    "",
                    "&7Fairy Souls: &e%souls_player_tia_collected%&7/&d5",
                    "",
                    "%souls_tia_placeholder%"))
                .headData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk2OTIzYWQyNDczMTAwMDdmNmFlNWQzMjZkODQ3YWQ1Mzg2NGNmMTZjMzU2NWExODFkYzhlNmIyMGJlMjM4NyJ9fX0=")
                .build())
            .build();
}
