package com.qualityplus.auction.api.gui;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.background.Background;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Utility class for gui display item
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public final class GUIDisplayItem extends OkaeriConfig {
    private int slot;
    private XMaterial icon;
    private String texture;
    private List<String> description;
    private Background background;
}
