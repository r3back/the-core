package com.qualityplus.auction.base.config;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Comment;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.CustomKey;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.auction.persistence.data.AuctionTime;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;

import java.util.Map;

/***
 * Utility class for database
 */
@Getter
@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    private String prefix = "[TheAuction] ";

    @CustomKey("configDatabase")
    @Comment("Database Configuration")
    @Comment("Allowed Database Types")
    @Comment("- H2")
    @Comment("- FLAT")
    @Comment("- MYSQL")
    @Comment("- REDIS")
    private ConfigDatabase configDatabase = new ConfigDatabase();

    @CustomKey("loreWrapper")
    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    private  LoreWrapper loreWrapper = new LoreWrapper(50, "&7");

    private  Map<String, AuctionTime> durationPrices = ImmutableMap.<String, AuctionTime>builder()
            .put("15s", new AuctionTime(new HumanTime(15, HumanTime.TimeType.SECONDS), 1D, XMaterial.RED_TERRACOTTA, 10))

            .put("2h", new AuctionTime(new HumanTime(2, HumanTime.TimeType.HOURS), 200D, XMaterial.RED_TERRACOTTA, 11))
            .put("6h", new AuctionTime(new HumanTime(6, HumanTime.TimeType.HOURS), 400D, XMaterial.ORANGE_TERRACOTTA, 12))
            .put("12h", new AuctionTime(new HumanTime(12, HumanTime.TimeType.HOURS), 500D, XMaterial.YELLOW_TERRACOTTA, 13))
            .put("18h", new AuctionTime(new HumanTime(18, HumanTime.TimeType.HOURS), 800D, XMaterial.TERRACOTTA, 14))
            .put("24h", new AuctionTime(new HumanTime(24, HumanTime.TimeType.HOURS), 900D, XMaterial.PURPLE_TERRACOTTA, 15))
            .build();

    private double startBidPrice = 500;

    private double serverFeePercentage = 5;

    private String defaultCategory = "weapons";

    @Comment("The new amount required to submit a new bid")
    @Comment("By example if there's a bid of 50 coins")
    @Comment("The new bid should be 2.5 coins higher")
    @Comment("which is the 5% of 50")
    private double minPercentageToSubmitNewBid = 5;
}
