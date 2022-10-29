package com.qualityplus.pets.base.pet.egg;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class PetEgg extends OkaeriConfig {
    private String petId;
    private String texture;
    private List<String> lore;
    private String displayName;
    private XMaterial material;
}
