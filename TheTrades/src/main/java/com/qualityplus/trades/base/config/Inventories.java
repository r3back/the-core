package com.qualityplus.trades.base.config;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.trades.base.gui.trades.TradesGUIConfig;
import com.qualityplus.trades.base.gui.options.TradeOptionsGUIConfig;
import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.background.DefaultBackgrounds;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
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

    @CustomKey("tradesGUIConfig")
    public TradesGUIConfig tradesGUIConfig = TradesGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Trades",
                    54,
                    getBackGround3(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .goBack(ItemBuilder.of(XMaterial.ARROW,  48, 1, "&eGo Back", Arrays.asList("", "&e» &7Click to go back")).build())
            .nextPage(ItemBuilder.of(XMaterial.ARROW,  53, 1, "&eNext Page", Arrays.asList("", "&e» &7Click to go page %next_page%")).build())
            .previousPage(ItemBuilder.of(XMaterial.ARROW,  45, 1, "&ePrevious Page", Arrays.asList("", "&e» &7Click to go page %next_page%")).build())
            .unlockedItem(ItemBuilder.of(XMaterial.STONE, 1, "&f%trade_id% %trade_result_item_amount%", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick To Trade!", "&eRight-Click for more trading", "&eoptions!")).build())
            .lockedItem(ItemBuilder.of(XMaterial.GRAY_DYE, 1, "&c???", Arrays.asList("&7Progress trough your item", "&7collections and explore the", "&7world to unlock new trades!")).build())
            .showIconInLockedItem(false)
            .showIconInUnlockedItem(true)
            .build();

    @CustomKey("tradeOptionsGUIConfig")
    public TradeOptionsGUIConfig tradeOptionsGUIConfig = TradeOptionsGUIConfig.builder()
            .commonGUI(new CommonGUI(
                    "Shop Trading Options",
                    54,
                    getBackGroundFiller(),
                    ItemBuilder.of(XMaterial.BARRIER,  49, 1, "&cClose", Arrays.asList("", "&e» &7Click to close")).build()
            ))
            .goBack(ItemBuilder.of(XMaterial.ARROW,  48, 1, "&eGo Back", Arrays.asList("", "&e» &7Click to go back")).build())
            .itemsAndAmountToTrade(FasterMap.builder(Integer.class, Item.class)
                    .put(1, ItemBuilder.of(XMaterial.STONE, 20, 1, "&f%trade_id% &8x1", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick to purchase!")).build())
                    .put(5, ItemBuilder.of(XMaterial.STONE, 21, 5, "&f%trade_id% &8x5", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick to purchase!")).build())
                    .put(10, ItemBuilder.of(XMaterial.STONE, 22, 10, "&f%trade_id% &8x10", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick to purchase!")).build())
                    .put(32, ItemBuilder.of(XMaterial.STONE, 23, 32, "&f%trade_id% &8x32", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick to purchase!")).build())
                    .put(64, ItemBuilder.of(XMaterial.STONE, 24, 64, "&f%trade_id% &8x64", Arrays.asList("", "%trade_description%", "", "%trade_cost%", "", "&eClick to purchase!")).build())
                    .build()
            )
            .build();


}
