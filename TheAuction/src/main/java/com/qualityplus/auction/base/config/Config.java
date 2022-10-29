package com.qualityplus.auction.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.auction.persistence.data.AuctionTime;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Map;

@Configuration()
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Config extends OkaeriConfig {
    public String prefix = "[TheAuction] ";

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

    public Map<String, AuctionTime> durationPrices = ImmutableMap.<String, AuctionTime>builder()
            .put("15s", new AuctionTime(new Timer(15, Timer.TimeType.SECONDS), 1D, XMaterial.RED_TERRACOTTA, 10))

            .put("2h", new AuctionTime(new Timer(2, Timer.TimeType.HOURS), 200D, XMaterial.RED_TERRACOTTA, 11))
            .put("6h", new AuctionTime(new Timer(6, Timer.TimeType.HOURS), 400D, XMaterial.ORANGE_TERRACOTTA, 12))
            .put("12h", new AuctionTime(new Timer(12, Timer.TimeType.HOURS), 500D, XMaterial.YELLOW_TERRACOTTA, 13))
            .put("18h", new AuctionTime(new Timer(18, Timer.TimeType.HOURS), 800D, XMaterial.TERRACOTTA, 14))
            .put("24h", new AuctionTime(new Timer(24, Timer.TimeType.HOURS), 900D, XMaterial.PURPLE_TERRACOTTA, 15))
            .build();

    public double startBidPrice = 500;

    public double serverFeePercentage = 5;

    public String defaultCategory = "weapons";

    @Comment("The new amount required to submit a new bid")
    @Comment("By example if there's a bid of 50 coins")
    @Comment("The new bid should be 2.5 coins higher")
    @Comment("which is the 5% of 50")
    public double minPercentageToSubmitNewBid = 5;
}
