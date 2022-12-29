package com.qualityplus.bank.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;


@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[TheBank] ";

    @CustomKey("configDatabase")
    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    public ConfigDatabase configDatabase = new ConfigDatabase();

    @CustomKey("loreWrapper")
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    public LoreWrapper loreWrapper = new LoreWrapper(50, "&7");

    @CustomKey("bankInterestDelay")
    public Timer bankInterestDelay = new Timer(24, Timer.TimeType.HOURS);

    @CustomKey("personalBankDelay")
    public Timer personalBankDelay = new Timer(1, Timer.TimeType.HOURS);

    @CustomKey("personalBankItem")
    public Item personalBankItem = ItemBuilder.of(XMaterial.PLAYER_HEAD, 1,
            "&fPersonal Bank Item",
            Arrays.asList("&7Shortcut to your Personal Bank!", "", "&bRight-Click to open bank!", "&eLeft-Click to deposit purse!", "", "&f&lCOMMON"))
            .headData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM2ZTk0ZjZjMzRhMzU0NjVmY2U0YTkwZjJlMjU5NzYzODllYjk3MDlhMTIyNzM1NzRmZjcwZmQ0ZGFhNjg1MiJ9fX0=")
            .build();
}
