package com.qualityplus.pets.base.pet.entity;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class PetEntityOptions extends OkaeriConfig {
    private String displayName;
    private XMaterial material;
    private String texture;
}
