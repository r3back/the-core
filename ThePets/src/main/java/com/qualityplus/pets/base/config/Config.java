package com.qualityplus.pets.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.config.ConfigMessages;
import com.qualityplus.assistant.api.config.ConfigSound;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;

import java.util.Arrays;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[ThePets] ";

    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    public ConfigDatabase configDatabase = new ConfigDatabase();

    public int petXpToGiveBlockBreakAmount = 2;
    public int petXpToGiveSkillAmount = 2;

    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(40, "&7");


    public Item petEggItem = ItemBuilder.of(XMaterial.STONE, 1, "%pet_egg_egg_displayname%", Arrays.asList("&8%pet_category_displayname% Pet", "","%pet_description_gui%",
                    "",
                    "&7Progress to next level: &e%pet_level_progress%%",
                    "%pet_action_bar% &e%pet_xp%&6/&e%pet_max_xp%",
                    "",
                    "&eRight-click to add this pet to", "&eyour pet menu!", "", "&e&lCOMMON"))

            .build();

    public LevelUpSettings levelUpSettings = new LevelUpSettings();

    @Getter
    public static class LevelUpSettings extends OkaeriConfig{
        public ConfigSound sound = new ConfigSound(XSound.ENTITY_EXPERIENCE_ORB_PICKUP, true, 0.2f, 1f);
        public ConfigMessages message = new ConfigMessages(Arrays.asList(
                "&3&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&c&b&lPET LEVEL UP &3%pet_egg_displayname% %pet_level_roman%",
                "&c  ",
                "&c&a&lREWARDS",
                "",
                "&c%pet_info_message%",
                "&3&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"


        ), true);
    }
}
