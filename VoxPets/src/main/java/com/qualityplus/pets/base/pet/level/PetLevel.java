package com.qualityplus.pets.base.pet.level;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.pets.base.pet.potion.PetPotion;
import com.qualityplus.pets.base.rewards.StatReward;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class PetLevel extends OkaeriConfig {
    private double requiredXp;
    private List<String> petInfoInGUI;
    private List<PetPotion> petPotions;
    private List<StatReward> statRewards;
    private List<String> petInfoInMessage;
    private List<CommandReward> commandRewards;
    private List<CommandReward> commandsWhenSpawn;
    private List<CommandReward> commandWhenDeSpawn;
}
