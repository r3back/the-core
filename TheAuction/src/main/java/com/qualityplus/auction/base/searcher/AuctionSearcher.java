package com.qualityplus.auction.base.searcher;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.api.category.AuctionCategory;
import com.qualityplus.auction.api.searcher.Searcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.CategoryFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import com.qualityplus.auction.persistence.data.AuctionItem;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Utility class for auction searcher
 */
public final class AuctionSearcher implements Searcher<AuctionItem> {
    private final CategoryFilter categoryFilter;
    private final @Getter StringFilter stringFilter;
    private final @Getter SortFilter sortFilter;
    private final @Getter BinFilter binFilter;
    private final Box box;

    /**
     * Makes an auctions searcher
     *
     * @param stringFilter Filter
     * @param sortFilter   {@link SortFilter}
     * @param binFilter    {@link BinFilter}
     * @param category     category
     * @param box          {@link Box}
     */
    @Builder
    public AuctionSearcher(final StringFilter stringFilter, final  SortFilter sortFilter, final BinFilter binFilter, final String category, final Box box) {
        this.categoryFilter = new CategoryFilter(category, box);
        this.stringFilter = stringFilter;
        this.sortFilter = sortFilter;
        this.binFilter = binFilter;
        this.box = box;
    }

    @Override
    public List<AuctionItem> getFiltered() {
        final List<AuctionItem> auctionItems = new ArrayList<>(this.box.auctionService().getItems());

        if (this.sortFilter.equals(SortFilter.RANDOM)) {
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid() + RandomUtil.randomBetween(0, 5)));
        } else if (this.sortFilter.equals(SortFilter.LOWEST_PRICE)) {
            auctionItems.sort((o1, o2) -> (int) (o1.getHighestBid() - o2.getHighestBid()));
        } else if (this.sortFilter.equals(SortFilter.HIGHEST_PRICE)) {
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid()));
        } else if (this.sortFilter.equals(SortFilter.ENDING_SOON)) {
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid()));
        }

        return auctionItems.stream()
                .filter(binFilter())
                .filter(stringFilter())
                .filter(categoryFilter())
                .collect(Collectors.toList());
    }

    private Predicate<AuctionItem> stringFilter() {
        if (this.stringFilter == null || this.stringFilter.getToSearch() == null) {
            return auctionItem -> true;
        }

        return auctionItem -> BukkitItemUtil.getItemLore(auctionItem.getItemStack()).contains(this.stringFilter.getToSearch()) ||
                              BukkitItemUtil.getName(auctionItem.getItemStack()).equalsIgnoreCase(this.stringFilter.getToSearch()) ||
                              BukkitItemUtil.getName(auctionItem.getItemStack()).contains(this.stringFilter.getToSearch());
    }

    private Predicate<AuctionItem> binFilter() {
        if (this.binFilter.equals(BinFilter.BIN_ONLY)) {
            return AuctionItem::isBuyItNow;
        } else if (this.binFilter.equals(BinFilter.AUCTION_ONLY)) {
            return auctionItem -> !auctionItem.isBuyItNow();
        }
        return auctionItem -> true;
    }

    private Predicate<AuctionItem> categoryFilter() {
        if (this.categoryFilter == null || this.categoryFilter.getCategory() == null) {
            return auctionItem -> true;
        }

        final Optional<AuctionCategory> category = this.categoryFilter.getBox().files().bankUpgrades().getById(this.categoryFilter.getCategory());

        return category.<Predicate<AuctionItem>>map(auctionCategory -> auctionItem -> auctionCategory.getMaterials()
                .stream()
                .anyMatch(material -> checkIfItEquals(auctionItem, material)))
                .orElseGet(() -> auctionItem -> true);
    }

    private boolean checkIfItEquals(final AuctionItem auctionItem, final XMaterial material) {
        return XMaterial.matchXMaterial(auctionItem.getItemStack()).equals(material);
    }

    /**
     * makes a string for category
     *
     * @return Category
     */
    public String getCategory() {
        return this.categoryFilter.getCategory();
    }
}
