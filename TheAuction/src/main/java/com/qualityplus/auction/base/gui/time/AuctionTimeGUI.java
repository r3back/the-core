package com.qualityplus.auction.base.gui.time;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.time.HumanTime;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for auction time
 */
public final class AuctionTimeGUI extends AuctionGUI {
    private final Map<Integer, String> timerMap = new HashMap<>();
    private final AuctionTimeConfigGUI config;
    private final AuctionItem auctionItem;
    private final AuctionSearcher searcher;

    /**
     * Makes auction time
     *
     * @param boxUtil     {@link Box}
     * @param auctionItem {@link AuctionItem}
     * @param searcher    {@link AuctionSearcher}
     */
    public AuctionTimeGUI(final Box boxUtil, final AuctionItem auctionItem, final AuctionSearcher searcher) {
        super(boxUtil.files().inventories().getAuctionTimeConfigGUI(), boxUtil);

        this.config = boxUtil.files().inventories().getAuctionTimeConfigGUI();
        this.auctionItem = auctionItem;
        this.searcher = searcher;
    }


    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);

        for (String key : box.files().config().getDurationPrices().keySet()) {
            final AuctionTime auctionTime = box.files().config().getDurationPrices().get(key);

            inventory.setItem(auctionTime.getSlot(), ItemStackUtils.makeItem(this.config.getTimeItem(), Arrays.asList(
                    new Placeholder("duration_format", getDuration(auctionTime.getTimer())),
                    new Placeholder("duration_fee", auctionTime.getFee())

            ), auctionTime.getIcon().parseItem()));

            this.timerMap.put(auctionTime.getSlot(), key);
        }

        setItem(this.config.getGoBack());

        setItem(this.config.getCloseGUI());

        return inventory;
    }


    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getGoBack())) {
            player.openInventory(new CreateAuctionGUI(box, player, this.searcher).getInventory());
        } else if (this.timerMap.containsKey(slot)) {
            final String key = this.timerMap.get(slot);

            final AuctionTime auctionTime = box.files().config().getDurationPrices().getOrDefault(key, null);

            if (auctionTime == null) {
                return;
            }

            this.auctionItem.setTimerId(key);

            player.openInventory(new CreateAuctionGUI(box, player, this.searcher).getInventory());
        }
    }


    private String getDuration(final HumanTime timer) {

        final List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("duration_type", box.files().messages().getAuctionMessages().getTimeFormat().get(timer.getType())),
                new Placeholder("duration_time", timer.getAmount())
        );

        return StringUtils.processMulti(box.files().messages().getAuctionMessages().getItemDurationFormat(), placeholders);

    }
}
