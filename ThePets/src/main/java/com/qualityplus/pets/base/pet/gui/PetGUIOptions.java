package com.qualityplus.pets.base.pet.gui;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
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
