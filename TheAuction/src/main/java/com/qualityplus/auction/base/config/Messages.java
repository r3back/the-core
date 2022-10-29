package com.qualityplus.auction.base.config;

import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration(path = "messages.yml")
@Header("================================")
@Header("       Messages      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Messages extends OkaeriConfig {
    public PluginMessages pluginMessages = new PluginMessages();
    public AuctionMessages auctionMessages = new AuctionMessages();

    @Getter
    @Setter
    public class AuctionMessages extends OkaeriConfig {
        public List<String> startingBid = Arrays.asList("", "^^^^^^^^^^^^^^", "Your auction", "starting bid");
        public List<String> submitBid = Arrays.asList("", "^^^^^^^^^^^^^^", "Enter an amount", "to bid");
        public List<String> enterQuery = Arrays.asList("", "", "^^^^^^^^^^^^^^", "Enter Query");


        public String itemDurationFormat = "&e%duration_time% %duration_type%";

        public String auctionDurationFormat = "&e%auction_duration_time% %auction_duration_type%";
        public String auctionStarted = "&eAuction started for %auction_item_name%&e!";

        public String auctionEndsInFormat = "%days_placeholder% %hours_placeholder% %minutes_placeholder% %seconds_placeholder%";
        public String auctionHistoryTimeFormat = "%days_placeholder% %hours_placeholder% %minutes_placeholder% %seconds_placeholder% ago";

        public String days = "%days%d";
        public String hours = "%hours%h";
        public String minutes = "%minutes%m";
        public String seconds = "%seconds%s";
        public String noTimeFormat = "&b-";
        public boolean showNoTimeSymbol = true;

        public Map<Timer.TimeType, String> timeFormat = ImmutableMap.<Timer.TimeType, String>builder()
                .put(Timer.TimeType.DAYS, "Days")
                .put(Timer.TimeType.MINUTES, "Minutes")
                .put(Timer.TimeType.SECONDS, "Seconds")
                .put(Timer.TimeType.HOURS, "Hours")
                .build();

        public String invalidAmount = "&cInvalid Amount";
        public String mustBeHigherThanMin = "&cAmount to bid must be higher than %auction_new_bid%!";

        public List<String> noBidsInformation = Collections.singletonList("&7%auction_is_buy_it_now%: &6%auction_starting_bid% coins");
        public List<String> bidsInformation = Arrays.asList("&7Bids: &a%auction_bids_amount% bids", "", "&7Top bid: &6%auction_top_bid% coins", "&7Bidder: %auction_bidder_name%");

        public List<String> soldFor = Arrays.asList("&7Buyer: &a%auction_buyer_name%", "", "&7Sold for: &6%auction_top_bid% coins");
        public List<String> notSoldYet = Collections.singletonList("&7%auction_is_buy_it_now%: &6%auction_starting_bid% coins");


        public List<String> itsYourAuction = Arrays.asList("&aThis is your own auction!", "");

        public List<String> bidHistoryFormat = Arrays.asList("&8————————————————————", "&7Bid: &6%auction_bid_amount% coins", "&7By: %auction_owner_name%", "&b%auction_history_bid_time%");

        public String ownAuction = "&aThis is your own auction!";
        public String canSubmitBin = "&eClick to purchase!";
        public String canSubmit = "&eClick to bid!";
        public String cannotSubmit = "&cCannot afford bid!";
        public String alreadyTopBid = "&aAlready Top Bid!";

        public String youCannotAffordIt = "&cYou cannot afford it!";
        public String youAlreadyHaveTheHighestBid = "&aYou already have the highest bid on this auction!";
        public String yourBidIsLowestThanTopBid = "&cYour bid is lowest than top bid!";

        public String youCannotBidYourOwn = "&aYou cannot bid in your own auction!";
        public String auctionExpired = "&cThat auction just expired!";
        public String bidPlaced = "&eBid of &6%bid_amount% coins &eplaced for %auction_item_name%!";
        public String successfullyBought = "&eYou claimed &a%auction_item_name% &efrom %auction_owner_name%'s auction!";

        public String currentlyBrowsing = "&aCurrently browsing!";
        public String currentlyNotBrowsing = "&eClick to view items!";

        public String sortSelected = "&b► ";
        public String sortNotSelected = "";

        public String auctionIsNotBin = "Starting bid";
        public String auctionIsBin = "Buy It Now";

        public String buyItNowPlaceholder = "BIN Auction";
        public String auctionPlaceholder = "Auction";

        public String inProgressStatus = "&7Ends in: &e%auction_remaining_time%";
        public String normalAuctionEndedStatus = "&7Status: &aEnded!";
        public String binAuctionEndedStatus = "&7Status: &aSold!";
        public String expiredStatus = "&7Status: &cExpired!";

        public String collectedMoney = "&eYou collected &6%auction_top_bid% coins &efrom selling %auction_item_name% &eto %auction_buyer_name% &ein an auction!";
        public String pickUpBack = "&eYou claimed &f%auction_item_name% &eback from your expired auction!";

        public String youPaidMinimumPrice = "&7You paid the minimum price of &6%auction_top_bid% coins&7!";

        public String youHadTheTop = "&7You had the top bid for &6%auction_top_bid% coins&7!";
        public String youHadNotTheTop = "&6%auction_own_bid% coins&7 couldn't reach top bid!";
        public String youMayCollectItem = "&7You may collect the item.";
        public String youMayCollectMoney = "&7You may collect the money.";
        public String clickToPickupItem = "&eClick to pick up item!";
        public String clickToPickupCoins = "&eClick to pick up coins!";
        public String claimedAuctionItem = "&eYou collected &a%auction_item_name% &efrom %auction_owner_name%'s auction!";
        public String claimedMoneyBack = "&eYou claimed back your &6%auction_own_bid% coins &efrom %auction_owner_name%'s auction!";

    }


    public class PluginMessages extends OkaeriConfig {
        public String successfullyReloaded = "&aPlugin has been reloaded successfully!";

        public String invalidPlayer = "&cInvalid Player!";

        public String useSyntax = "&cUsage: %usage%!";

        public String noPermission = "&ecYou don't have permission to do that!";
        public String unknownCommand = "&cUnknown command!";
        public String mustBeAPlayer = "&cYou must be a player to do that!";
        public String invalidArguments = "&cInvalid Arguments!";
        public String invalidAmount = "&cInvalid Amount!";
        public String useHelp = "&cUse: /auction help";

        public String helpMessage = "&7%command% - &e%description%";
        public String helpHeader = "      &6&lTheAuction   ";
        public String helpfooter = "&e<< &6Page %page% of %maxpage% &e>>";
        public String previousPage = "<<";
        public String nextPage = ">>";
        public String helpPageHoverMessage = "Click to go to page %page%";
    }


}
