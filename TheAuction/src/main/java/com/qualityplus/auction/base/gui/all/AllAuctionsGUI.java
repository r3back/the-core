package com.qualityplus.auction.base.gui.all;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.SignGUI;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.api.category.AuctionCategory;
import com.qualityplus.auction.base.gui.AuctionGUI;
import com.qualityplus.auction.base.gui.main.MainAuctionGUI;
import com.qualityplus.auction.base.gui.view.ViewOpener;
import com.qualityplus.auction.base.gui.view.normal.NormalAuctionViewGUI;
import com.qualityplus.auction.base.searcher.AuctionSearcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import com.qualityplus.auction.persistence.data.AuctionItem;
import com.qualityplus.auction.util.AuctionItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public final class AllAuctionsGUI extends AuctionGUI {
    private final Map<Integer, AuctionCategory> categoriesMap = new HashMap<>();
    private final Map<Integer, AuctionItem> allAuctions = new HashMap<>();
    private final AllAuctionsGUIConfig config;
    private final AuctionSearcher searcher;
    private final int itemsSize;

    public AllAuctionsGUI(Box boxUtil, int page, UUID uuid, AuctionSearcher auctionSearcher) {
        super(boxUtil.files().inventories().allAuctionsGUIConfig, boxUtil);

        List<AuctionItem> items = getNoExpiredItems(auctionSearcher);

        this.maxPerPage = box.files().inventories().allAuctionsGUIConfig.auctionSlots.size();
        this.config = boxUtil.files().inventories().allAuctionsGUIConfig;
        this.hasNext = items.size() > maxPerPage * page;
        this.searcher = auctionSearcher;
        this.itemsSize = items.size();
        this.page = page;
        this.uuid = uuid;
    }

    private static List<AuctionItem> getNoExpiredItems(AuctionSearcher searcher){
        return searcher.getFiltered().stream()
                .filter(auctionItem -> !auctionItem.isExpired())
                .filter(auctionItem -> !auctionItem.hasBeenBought())
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        setCategoryItems();

        setItem(config.getCloseGUI());

        setItem(config.goBack);

        addContent();

        if (this.page > 1)
            inventory.setItem(config.previousPage.slot, ItemStackUtils.makeItem(config.previousPage));

        if (hasNext)
            inventory.setItem(config.nextPage.slot, ItemStackUtils.makeItem(config.nextPage));


        setItem(config.sortItem, getSortPlaceholders());
        setItem(config.binFilterItem, getBinPlaceholders());

        if(searcher.getStringFilter().getToSearch() == null)
            setItem(config.byNameFilterEmptyItem);
        else
            setItem(config.byNameFilterFilledItem, Collections.singletonList(new Placeholder("auction_by_name_search", searcher.getStringFilter().getToSearch())));

        setItem(config.resetSettings);


        return inventory;
    }

    private void deleteExpired(){
        if(itemsSize == getNoExpiredItems(searcher).size()) return;

        Optional.ofNullable(Bukkit.getPlayer(uuid)).ifPresent(player -> player.openInventory(new AllAuctionsGUI(box, page, uuid, searcher).getInventory()));
    }

    @Override
    public void addContent() {
        deleteExpired();

        List<AuctionItem> auctions = getNoExpiredItems(searcher);

        try {

            int slot = 0;
            int i = maxPerPage * (page - 1);
            if(auctions.size() > 0){
                while (slot < maxPerPage) {
                    if (auctions.size() > i && i >= 0) {
                        AuctionItem auctionItem = auctions.get(i);
                        int finalSlot = config.auctionSlots.get(slot);

                        inventory.setItem(finalSlot, ItemStackUtils.makeItem(config.auctionItem, getAuctionItemPlaceholders(auctionItem), auctionItem.getItemStack()));
                        allAuctions.put(finalSlot, auctionItem);
                        slot++;
                        i++;
                        continue;
                    }
                    slot++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<IPlaceholder> getBinPlaceholders(){
        BinFilter filter = searcher.getBinFilter();
        return Arrays.asList(
                new Placeholder("auction_browser_show_all", getAnswer(filter.equals(BinFilter.SHOW_ALL))),
                new Placeholder("auction_browser_bin_only", getAnswer(filter.equals(BinFilter.BIN_ONLY))),
                new Placeholder("auction_browser_auctions_only", getAnswer(filter.equals(BinFilter.AUCTION_ONLY)))
        );
    }

    private List<IPlaceholder> getSortPlaceholders(){
        SortFilter filter = searcher.getSortFilter();
        return Arrays.asList(
                new Placeholder("auction_browser_is_by_highest", getAnswer(filter.equals(SortFilter.HIGHEST_PRICE))),
                new Placeholder("auction_browser_is_by_lowest", getAnswer(filter.equals(SortFilter.LOWEST_PRICE))),
                new Placeholder("auction_browser_is_by_ending_soon", getAnswer(filter.equals(SortFilter.ENDING_SOON))),
                new Placeholder("auction_browser_is_by_random", getAnswer(filter.equals(SortFilter.RANDOM)))
        );
    }

    private String getAnswer(boolean value){
        return value ? box.files().messages().auctionMessages.sortSelected : box.files().messages().auctionMessages.sortNotSelected;
    }

    private void setCategoryItems(){
        for(AuctionCategory category : box.files().bankUpgrades().categoryList){
            if(category.getId().equals(searcher.getCategory()))
                InventoryUtils.fillInventory(inventory, category.getDisplayInfo().getBackground());

            categoriesMap.put(category.getDisplayInfo().getSlot(), category);

            inventory.setItem(category.getDisplayInfo().getSlot(), AuctionItemStackUtils.makeCategoryItem(config.categoryItem,
                    Arrays.asList(new Placeholder("auction_category_displayname", category.getDisplayName()),
                                  new Placeholder("auction_category_lore", category.getDisplayInfo().getDescription()),
                                  new Placeholder("auction_is_current_category", category.getId().equals(searcher.getCategory()) ? box.files().messages().auctionMessages.currentlyBrowsing :
                                                                                                                                   box.files().messages().auctionMessages.currentlyNotBrowsing)),
                    category.getDisplayInfo(), box.files().config().loreWrapper));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        } else if(isItem(slot, config.nextPage) && hasNext){
            player.openInventory(new AllAuctionsGUI(box, page + 1, uuid, searcher).getInventory());
        }else if(isItem(slot, config.previousPage) && page > 1){
            player.openInventory(new AllAuctionsGUI(box, page - 1, uuid, searcher).getInventory());
        }else if(allAuctions.containsKey(slot)) {
            AuctionItem auctionItem = allAuctions.get(slot);

            if (auctionItem == null) return;

            ViewOpener.open(player, auctionItem, box, searcher, -1);
        }else if(categoriesMap.containsKey(slot)) {
            AuctionCategory category = categoriesMap.getOrDefault(slot, null);

            if (category == null) return;

            player.openInventory(new AllAuctionsGUI(box, 1, uuid, getBuilder()
                    .category(category.getId())
                    .build()).getInventory());
        }else if(isItem(slot, config.sortItem)){
            player.openInventory(new AllAuctionsGUI(box, 1, uuid, getBuilder()
                    .sortFilter(searcher.getSortFilter().getNext())
                    .build()).getInventory());
        }else if(isItem(slot, config.binFilterItem)) {
            player.openInventory(new AllAuctionsGUI(box, 1, uuid, getBuilder()
                    .binFilter(searcher.getBinFilter().getNext())
                    .build()).getInventory());
        }else if(isItem(slot, config.byNameFilterEmptyItem) || isItem(slot, config.byNameFilterFilledItem)) {
            if(searcher.getStringFilter().getToSearch() != null && event.isRightClick()){
                player.openInventory(new AllAuctionsGUI(box, 1, uuid, getBuilder()
                        .stringFilter(new StringFilter(null))
                        .build()).getInventory());
                return;
            }
            player.closeInventory();

            SignGUI.builder()
                    .plugin(box.plugin())
                    .uuid(player.getUniqueId())
                    .withLines(box.files().messages().auctionMessages.enterQuery)
                    .action(e -> e.getPlayer().openInventory(
                            new AllAuctionsGUI(box, 1, uuid, getBuilder()
                                    .stringFilter(new StringFilter(e.getLines().get(0)))
                                    .build()).getInventory()
                    ))
                    .build()
                    .open();
        }else if(isItem(slot, config.goBack)){
            player.openInventory(new MainAuctionGUI(box, searcher, uuid).getInventory());
        }
    }

    private AuctionSearcher.AuctionSearcherBuilder getBuilder(){
        return AuctionSearcher.builder()
                .stringFilter(searcher.getStringFilter())
                .sortFilter(searcher.getSortFilter())
                .binFilter(searcher.getBinFilter())
                .category(box.files().config().defaultCategory)
                .box(box);
    }

}
