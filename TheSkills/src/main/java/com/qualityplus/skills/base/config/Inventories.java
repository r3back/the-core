package com.qualityplus.skills.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.skills.gui.main.MainGUIConfig;
import com.qualityplus.skills.gui.stats.StatsAndPerksGUIConfig;
import com.qualityplus.skills.gui.sub.SubGUIConfig;
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
            ItemBuilder.of(XMaterial.STONE, 1, "&a%skill_displayname% %skill_level_roman%", Arrays.asList("",
                    "&7%skill_description%",
                    "",
                    "&7Progress to next level: &e%skill_level_progress%%",
                    "%skill_action_bar% &e%skill_xp%&6/&e%skill_max_xp%",
                    "",
                    "%skill_info_gui%",
                    "",
                    "&eClick to view!")).build(),
            ItemBuilder.of(XMaterial.PLAYER_HEAD,  4, 1, "&a%player%'s Stats", Arrays.asList("", "&e» &7Click to view specific stats."))
                    .headOwner("%player%")
                    .build()

    );

    @CustomKey("subGUI")
    public SubGUIConfig subGUIConfig = new SubGUIConfig(
            new CommonGUI(
                    "%category% (%current_page%/%max_page%)",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ),
            ItemBuilder.of(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&e%skill_displayname% %skill_level_roman%", Arrays.asList("",
                    "&7%skill_description%",
                    "",
                    "&7Progress to next level: &e%skill_level_progress%%",
                    "%skill_action_bar% &e%skill_xp%&6/&e%skill_max_xp%",
                    "",
                    "%skill_info_gui%")).build(),
            ItemBuilder.of(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&a%skill_displayname% %skill_level_roman%", Arrays.asList("",
                    "&7%skill_description%",
                    "",
                    "%skill_info_gui%")).build(),
            ItemBuilder.of(XMaterial.RED_STAINED_GLASS_PANE, 1, "&c%skill_displayname% %skill_level_roman%", Arrays.asList("",
                    "&7%skill_description%",
                    "",
                    "%skill_info_gui%")).build(),

            ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aLevels 1 - 25", Arrays.asList("&eClick to view")).build(),
            ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aLevels 26 - 50", Arrays.asList("&eClick to view")).build(),
            ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aGo Back", Arrays.asList("&7To Your Skills")).build(),
            Arrays.asList(9,18,27,28,29,20,11,2,3,4,13,22,31,32,33,24,15,6,7,8,17,26,35,44,53),
            ItemBuilder.of(XMaterial.STONE,  0, 1, "&a%skill_displayname% %skill_level_roman%", Arrays.asList("",
                    "&7%skill_description%",
                    "",
                    "&7Progress to next level: &e%skill_level_progress%%",
                    "%skill_action_bar% &e%skill_xp%&6/&e%skill_max_xp%",
                    "")).build()
    );

    @CustomKey("statsAndPerksGUI")
    public StatsAndPerksGUIConfig statsAndPerksGUI = StatsAndPerksGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Your stats",
                    54,
                    getBackGround3(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .previousPage(ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aPrevious Page", Arrays.asList("", "&e» &7Click to go previous page.")).build())
            .nextPage(ItemBuilder.of(XMaterial.ARROW,  50, 1, "&aNext Page", Arrays.asList("", "&e» &7Click to go to next page.")).build())
            .goBack(ItemBuilder.of(XMaterial.ARROW,  48, 1, "&aGo Back", Arrays.asList("", "&e» &7Click to go to main menu.")).build())
            .statItem(ItemBuilder.of(XMaterial.STONE, 1, "%stat_displayname%", Collections.singletonList("&7%stat_description%")).build())
            .perkItem(ItemBuilder.of(XMaterial.STONE, 1, "&a%perk_displayname%", Collections.singletonList("&7%perk_description%")).build())
            .statInfoItem(ItemBuilder.of(XMaterial.COMPASS, 4, 1, "&dStats", Arrays.asList("&7Statistics useful in combat", "&7and everyday situations!"))
                    .build())
            .perkInfoItem(ItemBuilder.of(XMaterial.CLOCK, 4, 1, "&dPerks", Arrays.asList("&7Perks stats use to", "&7better your farming, mining,", "&7etc."))
                    .build())
            .switchMode(ItemBuilder.of(XMaterial.PUFFERFISH, 51, 1, "&aSwitch Mode", Arrays.asList("&7Switch whether you want to see", "&aPERKS &7or &aSTATS&7.", "", "&7Current Mode: %current_mode%", "", "&eClick to switch")).build())

            .build();
}
