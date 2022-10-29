package com.qualityplus.pets.base.pet;

import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.pets.base.pet.egg.PetEgg;
import com.qualityplus.pets.base.pet.entity.PetEntityOptions;
import com.qualityplus.pets.base.pet.gui.PetGUIOptions;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public final class Pet extends OkaeriConfig {
    private String id;
    private int maxLevel;
    private PetEgg petEgg;
    private boolean enabled;
    //private PetGUIOptions petGUIOptions;
    private CommandRewards commandRewards;
    private PetEntityOptions petEntityOptions;
    private Map<Integer, Double> xpRequirements;
    /*private Map<Integer, List<String>> skillsInfoInGUI;
    private Map<Integer, List<String>> skillsInfoInMessage;*/

    public double getLevelRequirement(int level){
        return xpRequirements.getOrDefault(level, 1D);
    }
}
