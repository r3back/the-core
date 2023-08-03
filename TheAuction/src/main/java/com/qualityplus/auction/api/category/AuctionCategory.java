package com.qualityplus.auction.api.category;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.auction.api.gui.GUIDisplayItem;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Utility class for auction category
 */
@Data @EqualsAndHashCode(callSuper = true)
public final class AuctionCategory extends OkaeriConfig {
    private String id;
    private String displayName;
    private List<XMaterial> materials;
    private GUIDisplayItem displayInfo;
    private List<CategoryFilter> filters;

    /**
     * Makes an auction category
     *
     * @param id          Id
     * @param displayName Display Name
     * @param materials   {@link XMaterial}
     * @param displayInfo {@link GUIDisplayItem}
     * @param filters     {@link CategoryFilter}
     */
    @Builder
    public AuctionCategory(final String id, final String displayName, final List<XMaterial> materials,
             final GUIDisplayItem displayInfo, final List<CategoryFilter> filters) {
        this.id = id;
        this.filters = filters;
        this.materials = materials;
        this.displayName = displayName;
        this.displayInfo = displayInfo;
    }
}
