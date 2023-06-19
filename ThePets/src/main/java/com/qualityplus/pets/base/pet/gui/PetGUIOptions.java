package com.qualityplus.pets.base.pet.gui;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public final class PetGUIOptions extends OkaeriConfig {
    private int slot;
    private XMaterial item;
    private String texture;
    private List<String> mainMenuLore;
}
