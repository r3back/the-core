package com.qualityplus.pets.base.pet.entity;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class PetEntityOptions extends OkaeriConfig {
    private String displayName;
    private XMaterial material;
    private String texture;
    private String particle;
}
