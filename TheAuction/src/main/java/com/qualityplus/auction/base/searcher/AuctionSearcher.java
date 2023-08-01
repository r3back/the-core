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


public final class AuctionSearcher implements Searcher<AuctionItem> {
    private final CategoryFilter categoryFilter;
    private final @Getter StringFilter stringFilter;
    private final @Getter SortFilter sortFilter;
    private final @Getter BinFilter binFilter;
    private final Box box;

    @Builder
    public AuctionSearcher(StringFilter stringFilter, SortFilter sortFilter, BinFilter binFilter, String category, Box box) {
        this.categoryFilter = new CategoryFilter(category, box);
        this.stringFilter = stringFilter;
        this.sortFilter = sortFilter;
        this.binFilter = binFilter;
        this.box = box;
    }

    @Override
    public List<AuctionItem> getFiltered() {
        List<AuctionItem> auctionItems = new ArrayList<>(box.auctionService().getItems());

        if(sortFilter.equals(SortFilter.RANDOM))
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid() + RandomUtil.randomBetween(0,5)));
        else if(sortFilter.equals(SortFilter.LOWEST_PRICE))
            auctionItems.sort((o1, o2) -> (int) (o1.getHighestBid() - o2.getHighestBid()));
        else if(sortFilter.equals(SortFilter.HIGHEST_PRICE))
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid()));
        else if(sortFilter.equals(SortFilter.ENDING_SOON))
            auctionItems.sort(Comparator.comparingDouble(p -> (int) p.getHighestBid()));


        return auctionItems.stream()
                .filter(binFilter())
                .filter(stringFilter())
                .filter(categoryFilter())
                .collect(Collectors.toList());
    }

    private Predicate<AuctionItem> stringFilter() {
        if(stringFilter == null || stringFilter.getToSearch() == null) return auctionItem -> true;

        return auctionItem -> BukkitItemUtil.getItemLore(auctionItem.getItemStack()).contains(stringFilter.getToSearch()) ||
                              BukkitItemUtil.getName(auctionItem.getItemStack()).equalsIgnoreCase(stringFilter.getToSearch()) ||
                              BukkitItemUtil.getName(auctionItem.getItemStack()).contains(stringFilter.getToSearch());
    }

    private Predicate<AuctionItem> binFilter() {
        if(binFilter.equals(BinFilter.BIN_ONLY)) {
            return AuctionItem::isBuyItNow;
        }else if(binFilter.equals(BinFilter.AUCTION_ONLY)) {
            return auctionItem -> !auctionItem.isBuyItNow();
        }else
            return auctionItem -> true;
    }

    private Predicate<AuctionItem> categoryFilter() {
        if(categoryFilter == null || categoryFilter.getCategory() == null) return auctionItem -> true;

        Optional<AuctionCategory> category = categoryFilter.getBox().files().bankUpgrades().getById(categoryFilter.getCategory());

        return category.<Predicate<AuctionItem>>map(auctionCategory -> auctionItem -> auctionCategory.getMaterials()
                .stream()
                .anyMatch(material -> checkIfItEquals(auctionItem, material)))
                .orElseGet(() -> auctionItem -> true);
    }

    private boolean checkIfItEquals(AuctionItem auctionItem, XMaterial material) {
        return XMaterial.matchXMaterial(auctionItem.getItemStack()).equals(material);
    }

    public String getCategory() {
        return categoryFilter.getCategory();
    }
}
