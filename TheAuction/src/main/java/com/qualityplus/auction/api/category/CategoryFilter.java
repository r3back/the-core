package com.qualityplus.auction.api.category;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class CategoryFilter extends OkaeriConfig {
    private final FilterType type;
    private final String nbtKey;

    @Builder
    public CategoryFilter(FilterType type, String nbtKey) {
        this.type = type;
        this.nbtKey = nbtKey;
    }

    public enum FilterType{
        EXCLUDE,
        INCLUDE,
        INCLUDE_ONLY;
    }
}
