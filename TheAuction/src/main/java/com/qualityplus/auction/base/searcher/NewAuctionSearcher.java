package com.qualityplus.auction.base.searcher;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.auction.TheAuction;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.api.category.AuctionCategory;
import com.qualityplus.auction.api.filter.AuctionFilter;
import com.qualityplus.auction.api.searcher.Searcher;
import com.qualityplus.auction.base.searcher.filters.BinFilter;
import com.qualityplus.auction.base.searcher.filters.CategoryFilter;
import com.qualityplus.auction.base.searcher.filters.SortFilter;
import com.qualityplus.auction.base.searcher.filters.StringFilter;
import com.qualityplus.auction.persistence.data.AuctionItem;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewAuctionSearcher implements Searcher<AuctionItem> {
    private final AuctionFilter auctionFilter;

    @Override
    public List<AuctionItem> getFiltered() {


        return auctionFilter.filter(TheAuction.getApi().getAuctionService().getItems());
    }



   /* private Predicate<AuctionItem> stringFilter(){
        if(stringFilter == null || stringFilter.getToSearch() == null) return auctionItem -> true;

        return auctionItem -> ItemStackUtils.getItemLore(auctionItem.getItemStack()).contains(stringFilter.getToSearch()) ||
                ItemStackUtils.getName(auctionItem.getItemStack()).equalsIgnoreCase(stringFilter.getToSearch()) ||
                ItemStackUtils.getName(auctionItem.getItemStack()).contains(stringFilter.getToSearch());
    }

    private Predicate<AuctionItem> binFilter(){
        if(binFilter.equals(BinFilter.BIN_ONLY)){
            return AuctionItem::isBuyItNow;
        }else if(binFilter.equals(BinFilter.AUCTION_ONLY)){
            return auctionItem -> !auctionItem.isBuyItNow();
        }else
            return auctionItem -> true;
    }

    private Predicate<AuctionItem> categoryFilter(){
        if(categoryFilter == null || categoryFilter.getCategory() == null) return auctionItem -> true;

        Optional<AuctionCategory> category = categoryFilter.getBox().files().bankUpgrades().getById(categoryFilter.getCategory());

        return category.<Predicate<AuctionItem>>map(auctionCategory -> auctionItem -> auctionCategory.getMaterials()
                        .stream()
                        .anyMatch(material -> checkIfItEquals(auctionItem, material)))
                .orElseGet(() -> auctionItem -> true);
    }

    private boolean checkIfItEquals(AuctionItem auctionItem, XMaterial material){
        return XMaterial.matchXMaterial(auctionItem.getItemStack()).equals(material);
    }

    public String getCategory(){
        return categoryFilter.getCategory();
    }*/
}
