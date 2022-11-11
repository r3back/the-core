package com.qualityplus.pets.base.pet.egg;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class PetEgg extends OkaeriConfig {
    private String petId;
    private String texture;
    //private List<String> lore;
    private String displayName;
    private XMaterial material;
    private String eggDisplayName;
    private PetModelEngine petModelEngine;

    public String getEggDisplayName(){
        return StringUtils.processMulti(eggDisplayName, new Placeholder("pet_egg_displayname", displayName).alone());
    }
}
