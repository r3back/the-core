package com.qualityplus.assistant.inventory.background;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;

import java.util.Collections;

public interface DefaultBackgrounds {
    default Background getBackGround1(){
        return new Background(ImmutableMap.<Integer, Item>builder().build());
    }

    default Background getBackGroundFiller(){
        return Background.builder()
                .filler(ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .useFiller(true)
                .build();
    }

    default Background getBackGround2(){
        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(45, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(53, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .build());
    }

    default Background getBackGround5() {
        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(0, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(1, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(2, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(3, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(4, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(5, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(6, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(7, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(8, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(9, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(18, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(27, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(28, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(29, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(30, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())

                .put(32, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(33, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(34, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(35, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(17, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(26, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .build());
    }

    default Background getBackGround3() {
        return new Background(ImmutableMap.<Integer, Item>builder()
                .put(0, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(1, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(2, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(3, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(4, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(5, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(6, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(7, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(8, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(9, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(18, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(27, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(36, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(45, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(17, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(26, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(35, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(44, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(53, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", Collections.emptyList()).build())
                .build());
    }
}
