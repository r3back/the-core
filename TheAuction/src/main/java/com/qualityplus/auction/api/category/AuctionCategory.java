package com.qualityplus.auction.api.category;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.auction.api.gui.GUIDisplayItem;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data @EqualsAndHashCode(callSuper = true)
public final class AuctionCategory extends OkaeriConfig {
    private String id;
    private String displayName;
    private List<XMaterial> materials;
    private GUIDisplayItem displayInfo;
    private List<CategoryFilter> filters;

    @Builder
    public AuctionCategory(String id, String displayName, List<XMaterial> materials, GUIDisplayItem displayInfo, List<CategoryFilter> filters) {
        this.id = id;
        this.filters = filters;
        this.materials = materials;
        this.displayName = displayName;
        this.displayInfo = displayInfo;
    }
}
