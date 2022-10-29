package com.qualityplus.auction.base.gui.time;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.create.CreateAuctionGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.persistence.data.AuctionTime;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import com.qualityplus.assistant.util.time.Timer;

public final class AuctionTimeGUI extends AuctionGUI {
    private final Map<Integer, String> timerMap = new HashMap<>();
    private final AuctionTimeConfigGUI config;
    private final AuctionItem auctionItem;
    private final AuctionSearcher searcher;

    public AuctionTimeGUI(Box boxUtil, AuctionItem auctionItem, AuctionSearcher searcher) {
        super(boxUtil.files().inventories().auctionTimeConfigGUI, boxUtil);

        this.config = boxUtil.files().inventories().auctionTimeConfigGUI;
        this.auctionItem = auctionItem;
        this.searcher = searcher;
    }


    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        for(String key : box.files().config().durationPrices.keySet()){
            AuctionTime auctionTime = box.files().config().durationPrices.get(key);

            inventory.setItem(auctionTime.getSlot(), ItemStackUtils.makeItem(config.timeItem, Arrays.asList(
                    new Placeholder("duration_format", getDuration(auctionTime.getTimer())),
                    new Placeholder("duration_fee", auctionTime.getFee())

            ), auctionTime.getIcon().parseItem()));

            timerMap.put(auctionTime.getSlot(), key);
        }

        setItem(config.goBack);

        setItem(config.getCloseGUI());

        return inventory;
    }


    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.goBack)){
            player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
        }else if(timerMap.containsKey(slot)){
            String key = timerMap.get(slot);

            AuctionTime auctionTime = box.files().config().durationPrices.getOrDefault(key, null);

            if(auctionTime == null) return;

            auctionItem.setTimerId(key);

            player.openInventory(new CreateAuctionGUI(box, player, searcher).getInventory());
        }
    }


    private String getDuration(Timer timer){

        List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("duration_type", box.files().messages().auctionMessages.getTimeFormat().get(timer.getType())),
                new Placeholder("duration_time", timer.getAmount())
        );

        return StringUtils.processMulti(box.files().messages().auctionMessages.itemDurationFormat, placeholders);

    }
}
