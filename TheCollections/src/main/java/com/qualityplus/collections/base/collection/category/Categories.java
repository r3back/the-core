package com.qualityplus.collections.base.collection.category;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.collections.base.collection.gui.GUIOptions;
import lombok.Getter;

@Getter
public enum Categories {
    FARMING(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .slot(20)
                    .item(XMaterial.GOLDEN_HOE)
                    .build())
            .displayName("Farming")
            .id("farming")
            .build()),
    MINING(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .slot(21)
                    .item(XMaterial.STONE_PICKAXE)
                    .build())
            .displayName("Mining")
            .id("mining").build()),
    COMBAT(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .slot(22)
                    .item(XMaterial.STONE_SWORD)
                    .build())
            .displayName("Combat")
            .id("combat").build()),
    FORAGING(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .slot(23)
                    .item(XMaterial.OAK_SAPLING)
                    .build())
            .displayName("Foraging")
            .id("foraging").build()),
    FISHING(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .slot(24)
                    .item(XMaterial.FISHING_ROD)
                    .build())
            .displayName("Fishing")
            .id("fishing").build()),

    BOSS(CollectionCategory.builder()
            .options(GUIOptions.builder()
                    .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjVlYzk2NDY0NWE4ZWZhYzc2YmUyZjE2MGQ3Yzk5NTYzNjJmMzJiNjUxNzM5MGM1OWMzMDg1MDM0ZjA1MGNmZiJ9fX0=")
                    .slot(31)
                    .item(XMaterial.PLAYER_HEAD)
                    .build())
            .displayName("Boss")
            .id("boss").build());

    private final CollectionCategory category;
    private final String id;

    Categories(CollectionCategory category) {
        this.category = category;
        this.id = category.getId();
    }
}
