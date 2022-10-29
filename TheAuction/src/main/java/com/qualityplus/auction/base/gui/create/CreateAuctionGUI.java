package com.qualityplus.auction.base.gui.create;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.SignGUI;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.gui.view.ViewOpener;
import com.qualityplus.auction.base.gui.view.normal.NormalAuctionViewGUI;
import com.qualityplus.auction.base.gui.time.AuctionTimeGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionBid;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.persistence.data.User;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import com.qualityplus.assistant.util.time.Timer;

public final class CreateAuctionGUI extends AuctionGUI {
    private final CreateAuctionGUIConfig config;
    private final AuctionSearcher searcher;
    private final String name;

    public CreateAuctionGUI(Box boxUtil, Player player, AuctionSearcher searcher) {
        super(boxUtil.files().inventories().createAuctionGUIConfig, boxUtil);

        this.config = boxUtil.files().inventories().createAuctionGUIConfig;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.searcher = searcher;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        Optional<User> user = box.service().getUser(uuid);

        String timer = box.files().config().durationPrices.keySet().stream().findFirst().orElse(null);

        if(timer == null)
            return inventory;


        AuctionItem auctionItem = user.map(User::getTemporalAuction)
                .orElse(AuctionItem.builder()
                        .owner(uuid)
                        .ownerName(name)
                        .timer(timer)
                        .isBuyItNow(false)
                        .bids(defaultBid())
                        .build());

        List<IPlaceholder> placeholders = getPlaceholders(auctionItem);

        if(auctionItem.getItemStack() == null){
            setItem(config.currentItemEmpty);
            setItem(config.createAuctionEmpty, Collections.singletonList(getIsBuyItNowPlaceholder(auctionItem)));
        }else{
            inventory.setItem(config.currentItemFilled.slot, ItemStackUtils.makeItem(config.currentItemFilled, placeholders, auctionItem.getItemStack()));

            inventory.setItem(config.createAuctionFilled.slot, ItemStackUtils.makeItem(config.createAuctionFilled, placeholders));
        }

        if(auctionItem.isBuyItNow()) {
            setItem(config.auctionItemPrice, placeholders);
            setItem(config.switchToAuction);
        }else {
            setItem(config.auctionInitialBid, placeholders);
            setItem(config.switchToBin);
        }


        setItem(config.auctionDuration, placeholders);
        setItem(config.goBack, placeholders);

        setItem(config.getCloseGUI());

        user.ifPresent(u -> u.setTemporalAuction(auctionItem));

        return inventory;
    }


    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        int slot = e.getSlot();

        Optional<User> user = box.service().getUser(uuid);

        AuctionItem auctionItem = user
                .map(User::getTemporalAuction)
                .orElse(null);

        if(auctionItem == null) return;

        if(getTarget(e).equals(ClickTarget.PLAYER)){
            ItemStack itemStack = e.getCurrentItem();

            if(ItemStackUtils.isNull(itemStack))
                return;

            Optional.ofNullable(auctionItem.getItemStack()).ifPresent(item -> player.getInventory().addItem(item));

            auctionItem.setItemStack(itemStack.clone());

            e.setCurrentItem(null);

            player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
        }else{

            if(isItem(slot, config.getCloseGUI())){
                player.closeInventory();
            }else if(isItem(slot, config.goBack)){
                player.openInventory(new MainAuctionGUI(box, searcher, uuid).getInventory());
            }else if(isItem(slot, config.auctionDuration)){
                player.openInventory(new AuctionTimeGUI(box, auctionItem, searcher).getInventory());
            }else if(isItem(slot, config.auctionInitialBid)){

                player.closeInventory();

                SignGUI.builder()
                        .action(event1 -> changeBidPrice(player, auctionItem, event1.getLines().get(0)))
                        .withLines(box.files().messages().auctionMessages.startingBid)
                        .uuid(player.getUniqueId())
                        .plugin(box.plugin())
                        .build()
                        .open();

            }else if(isItem(slot, config.createAuctionFilled)){
                if(auctionItem.getItemStack() == null) return;

                double fees = getPrice(auctionItem);

                //Set timer
                Timer timer = box.files().config().durationPrices.get(auctionItem.getTimerId()).getTimer();

                auctionItem.setMarkable(new Markable(timer.getEffectiveTime(), System.currentTimeMillis()));

                //Add to all auction houses
                box.auctionService().getAuctionHouse().ifPresent(auctionHouse -> auctionHouse.getNormalItems().add(auctionItem));

                //Remove from player ah
                user.ifPresent(user1 -> user1.setTemporalAuction(null));

                //Remove Fees money
                TheAssistantPlugin.getAPI().getAddons().getEconomy().withdrawMoney(player, fees);

                String name = ItemStackUtils.getName(auctionItem.getItemStack());

                player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.auctionStarted.replace("%auction_item_name%", name)));

                ViewOpener.open(player, auctionItem, box, searcher, -1);

                //Add Created Stat
                user.ifPresent(user1 -> user1.getAuctionStats().setAuctionsCreated(user1.getAuctionStats().getAuctionsCreated() + 1));
                user.ifPresent(user1 -> user1.getAuctionStats().setMoneySpentOnFees(user1.getAuctionStats().getMoneySpentOnFees() + fees));

            }else if(isItem(slot, config.currentItemFilled)){
                if(ItemStackUtils.isNull(auctionItem.getItemStack())) return;

                ItemStack toGive = auctionItem.getItemStack().clone();

                auctionItem.setItemStack(null);

                player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());

                player.getInventory().addItem(toGive);

            }else if(isItem(slot, config.switchToAuction) || isItem(slot, config.switchToBin)){
                user.ifPresent(user1 -> user1.getTemporalAuction().setBuyItNow(!auctionItem.isBuyItNow()));
                player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
            }
        }
    }

    private void changeBidPrice(Player player, AuctionItem auctionItem, String input){
        double newPrice;

        if(auctionItem == null) return;

        try {
            newPrice = Double.parseDouble(input);

            if(newPrice <= 0) throw new NumberFormatException();

        }catch (NumberFormatException e){
            player.sendMessage(StringUtils.color(box.files().messages().auctionMessages.invalidAmount));
            return;
        }

        auctionItem.getBid(uuid).ifPresent(bid -> bid.setBidAmount(newPrice));

        player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
    }


    private List<AuctionBid> defaultBid(){
        return new ArrayList<>(Collections.singletonList(new AuctionBid(uuid, box.files().config().startBidPrice, System.currentTimeMillis(), false)));
    }

    private List<IPlaceholder> getPlaceholders(AuctionItem auctionItem){

        double startingBid = auctionItem.getBid(uuid).map(AuctionBid::getBidAmount).orElse(0D);

        double timeFee = box.files().config().durationPrices.get(auctionItem.getTimerId()).getFee();

        int percentage = (int) ((5 * startingBid) / 100);

        int creationPrice = (int) (percentage + timeFee);

        return Arrays.asList(
                new Placeholder("auction_item_name", ItemStackUtils.getName(auctionItem.getItemStack())),
                new Placeholder("auction_item_lore", ItemStackUtils.getItemLore(auctionItem.getItemStack())),
                new Placeholder("auction_creation_price", creationPrice),
                new Placeholder("auction_fee", percentage),
                new Placeholder("auction_duration_fee", timeFee),
                new Placeholder("auction_duration", getDuration(auctionItem)),
                new Placeholder("auction_starting_bid", startingBid),
                getIsBuyItNowPlaceholder(auctionItem)

        );
    }

    private Placeholder getIsBuyItNowPlaceholder(AuctionItem item){
        return new Placeholder("auction_is_buy_it_now_placeholder", item.isBuyItNow() ? box.files().messages().auctionMessages.buyItNowPlaceholder : box.files().messages().auctionMessages.auctionPlaceholder);
    }

    private double getPrice(AuctionItem auctionItem){
        double startingBid = auctionItem.getBid(uuid).map(AuctionBid::getBidAmount).orElse(0D);

        double timeFee = box.files().config().durationPrices.get(auctionItem.getTimerId()).getFee();

        int percentage = (int) ((5 * startingBid) / 100);

        return  (int) (percentage + timeFee);
    }

    private String getDuration(AuctionItem auctionItem){
        Timer timer = box.files().config().durationPrices.get(auctionItem.getTimerId()).getTimer();

        List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("auction_duration_type", box.files().messages().auctionMessages.getTimeFormat().get(timer.getType())),
                new Placeholder("auction_duration_time", timer.getAmount())
        );

        return StringUtils.processMulti(box.files().messages().auctionMessages.auctionDurationFormat, placeholders);

    }
}
