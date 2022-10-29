package com.qualityplus.auction.base.gui;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.assistant.util.time.RemainingTime;
import com.qualityplus.assistant.util.time.TimeUtils;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AuctionGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public AuctionGUI(int size, String title, Box box) {
        super(size, title);

        this.box = box;
    }

    public AuctionGUI(SimpleGUI simpleGUI, Box box) {
        super(simpleGUI);

        this.box = box;
    }

    public void addContent(){}

    protected String getRemainingTime(AuctionItem auctionItem){
        RemainingTime time = auctionItem.getMarkable().getRemainingTime();

        return TimeUtils.getParsedTime(
                time,
                box.files().messages().auctionMessages.auctionEndsInFormat,
                box.files().messages().auctionMessages.days,
                box.files().messages().auctionMessages.hours,
                box.files().messages().auctionMessages.minutes,
                box.files().messages().auctionMessages.seconds,
                box.files().messages().auctionMessages.noTimeFormat,
                box.files().messages().auctionMessages.showNoTimeSymbol
        );
    }

    protected List<IPlaceholder> getAuctionItemPlaceholders(AuctionItem auctionItem){
        return PlaceholderBuilder.create(new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack())),
                new Placeholder("auction_item_lore", ItemStackUtils.getItemLore(auctionItem.getItemStack())),
                new Placeholder("auction_owner_name", auctionItem.getOwnerName()),
                new Placeholder("auction_bids_amount", auctionItem.getBidsWithoutOwner().size()),
                new Placeholder("auction_status", getStatusPlaceholder(auctionItem)))
                .with(getBidPlaceholders(auctionItem))
                .get();
    }

    protected List<IPlaceholder> getBidPlaceholders(AuctionItem auctionItem){
        return Arrays.asList(
                new Placeholder("bid_information", getBidInformation(auctionItem)),
                new Placeholder("auction_is_buy_it_now", auctionItem.isBuyItNow() ? box.files().messages().auctionMessages.auctionIsBin : box.files().messages().auctionMessages.auctionIsNotBin),
                new Placeholder("auction_is_your_bid", auctionItem.getOwner().equals(uuid) ? StringUtils.color(box.files().messages().auctionMessages.itsYourAuction) : Collections.emptyList())
        );
    }

    protected String getStatusPlaceholder(AuctionItem auctionItem){
        if(auctionItem.isBuyItNow()){
            if(auctionItem.getWhoBought() == null)
                return auctionItem.isExpired() ? box.files().messages().auctionMessages.expiredStatus :
                                                 box.files().messages().auctionMessages.inProgressStatus
                                                    .replace("%auction_remaining_time%", getRemainingTime(auctionItem));
            else
                return box.files().messages().auctionMessages.binAuctionEndedStatus;

        }else{
            if(auctionItem.getWhoBought() == null)
                return auctionItem.isExpired() ? box.files().messages().auctionMessages.expiredStatus :
                                                 box.files().messages().auctionMessages.inProgressStatus
                                                    .replace("%auction_remaining_time%", getRemainingTime(auctionItem));
            else
                return box.files().messages().auctionMessages.normalAuctionEndedStatus;
        }
    }

    public List<String> getHistoryPlaceholders(AuctionItem auctionItem){
        List<AuctionBid> withoutOwner = auctionItem.getBidsWithoutOwner();

        withoutOwner.sort((o1, o2) -> (int) (o1.getBidAmount() - o2.getBidAmount()));

        List<String> toReturn = new ArrayList<>();

        for(AuctionBid auctionBid : withoutOwner){

            RemainingTime time = TimeUtils.getTimeWhenAgo(auctionBid.getTimeAgo());

            String timeAgoMsg = TimeUtils.getParsedTime(
                    time, box.files().messages().auctionMessages.auctionHistoryTimeFormat,
                    box.files().messages().auctionMessages.days,
                    box.files().messages().auctionMessages.hours,
                    box.files().messages().auctionMessages.minutes,
                    box.files().messages().auctionMessages.seconds,
                    box.files().messages().auctionMessages.noTimeFormat,
                    box.files().messages().auctionMessages.showNoTimeSymbol
            );

            List<IPlaceholder> placeholders1 = Arrays.asList(
                    new Placeholder("auction_bid_amount", auctionBid.getBidAmount()),
                    new Placeholder("auction_owner_name", PlayerUtils.getPlayerName(auctionBid.getBidder())),
                    new Placeholder("auction_history_bid_time", timeAgoMsg)
            );

            toReturn.addAll(StringUtils.processMulti(box.files().messages().auctionMessages.bidHistoryFormat, placeholders1));
        }

        return toReturn;
    }

    private List<String> getBidInformation(AuctionItem auctionItem){
        if(auctionItem.isBuyItNow()){
            List<IPlaceholder> placeholders = Arrays.asList(
                    new Placeholder("auction_buyer_name", PlayerUtils.getPlayerName(auctionItem.getWhoBought())),
                    new Placeholder("auction_top_bid", auctionItem.getHighestBid()),
                    new Placeholder("auction_starting_bid", auctionItem.getHighestBid())
            );
            return StringUtils.processMulti(auctionItem.getWhoBought() == null ? box.files().messages().auctionMessages.notSoldYet : box.files().messages().auctionMessages.soldFor, placeholders);
        }else{
            if(auctionItem.getBids().size() == 1){
                double bid = auctionItem.getBid(auctionItem.getOwner())
                        .map(AuctionBid::getBidAmount)
                        .orElse(0D);

                return StringUtils.processMulti(box.files().messages().auctionMessages.noBidsInformation, Collections.singletonList(new Placeholder("auction_starting_bid", bid)));
            }else{

                Optional<AuctionBid> bid = auctionItem.getBids()
                        .stream()
                        .max(Comparator.comparingInt(p -> (int) p.getBidAmount()));

                double ownerBid = auctionItem.getBid(auctionItem.getOwner())
                        .map(AuctionBid::getBidAmount)
                        .orElse(0D);

                if(!bid.isPresent()) return Collections.emptyList();

                return StringUtils.processMulti(box.files().messages().auctionMessages.bidsInformation, Arrays.asList(
                        new Placeholder("auction_starting_bid", ownerBid),
                        new Placeholder("auction_top_bid", bid.map(AuctionBid::getBidAmount).orElse(0D)),
                        new Placeholder("auction_bids_amount", auctionItem.getBidsWithoutOwner().size()),
                        new Placeholder("auction_bidder_name", PlayerUtils.getPlayerName(bid.map(AuctionBid::getBidder).orElse(null)))
                ));
            }
        }

    }

    protected List<AuctionItem> getAuctionsWherePlayerBid(UUID uuid){
        return box.auctionService().getItems().stream()
                .filter(auctionItem -> !auctionItem.getOwner().equals(uuid))
                .filter(auctionItem -> auctionItem.getBid(uuid).isPresent())
                .filter(auctionItem -> !isClaimed(auctionItem, uuid))
                .collect(Collectors.toList());
    }

    protected List<AuctionItem> getNotClaimedOwned(UUID uuid){
        return box.auctionService().getItems().stream()
                .filter(auctionItem -> auctionItem.getOwner().equals(uuid))
                .filter(auctionItem -> !isClaimed(auctionItem, uuid))
                .collect(Collectors.toList());
    }

    private boolean isClaimed(AuctionItem auctionItem, UUID uuid){
        Optional<AuctionBid> bid = auctionItem.getBid(uuid);

        return bid.isPresent() && bid.get().isClaimedBack();
    }

    public void setItem(Item item) {
        setItem(item, box.files().config().loreWrapper);
    }

    public void setItem(Item item, List<IPlaceholder> placeholderList) {
        setItem(item, placeholderList, box.files().config().loreWrapper);
    }
}
