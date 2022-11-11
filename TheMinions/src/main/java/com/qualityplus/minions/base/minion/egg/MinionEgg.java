package com.qualityplus.minions.base.minion.egg;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class MinionEgg extends OkaeriConfig {
    private String petId;
    private String texture;
    //private List<String> lore;
    private String displayName;
    private XMaterial material;
    private String eggDisplayName;

    public String getEggDisplayName(){
        return StringUtils.processMulti(eggDisplayName, new Placeholder("pet_egg_displayname", displayName).alone());
    }
}
