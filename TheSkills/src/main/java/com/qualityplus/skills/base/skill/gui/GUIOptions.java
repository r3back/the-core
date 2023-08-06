package com.qualityplus.skills.base.skill.gui;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Utility class for GUI options
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GUIOptions extends OkaeriConfig {
    private int slot;
    private int page;
    private XMaterial item;
    private String texture;
    private int customModelData;
    private List<String> mainMenuLore;

    /**
     * Makes a GUI options
     *
     * @param slot             Slot
     * @param page             Page
     * @param item             {@link XMaterial}
     * @param texture          Texture
     * @param mainMenuLore     Main Menu Lore
     * @param customModelData  Custom Model Data
     */
    @Builder
    public GUIOptions(final int slot, final int page, final XMaterial item, final String texture, final List<String> mainMenuLore, final int customModelData) {
        this.slot = slot;
        this.page = page;
        this.item = item;
        this.texture = texture;
        this.mainMenuLore = mainMenuLore;
        this.customModelData = customModelData;
    }
}
