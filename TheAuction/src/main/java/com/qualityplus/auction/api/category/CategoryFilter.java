package com.qualityplus.auction.api.category;

import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.auction.api.filter.type.FilterType;
import lombok.Builder;
import lombok.Getter;

/**
 * Utility class dor category filter
 */
@Getter
public final class CategoryFilter extends OkaeriConfig {
    /**
     * Adds filter type
     */
    private final FilterType type;
    private final String nbtKey;

    /**
     * Adds a category filter
     *
     * @param type   {@link FilterType}
     * @param nbtKey Nbt Key
     */
    @Builder
    public CategoryFilter(final FilterType type, final String nbtKey) {
        this.type = type;
        this.nbtKey = nbtKey;
    }

    /**
     * Add a filter type
     */
    public enum FilterType {
        EXCLUDE,
        INCLUDE,
        INCLUDE_ONLY;
    }
}
