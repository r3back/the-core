package com.qualityplus.pets.base.config.pet;

import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.egg.PetEgg;
import com.qualityplus.pets.base.pet.egg.PetModelEngine;
import com.qualityplus.pets.base.pet.entity.PetEntityOptions;
import com.qualityplus.pets.base.pet.level.PetLevel;
import com.qualityplus.pets.base.pet.potion.PetPotion;
import com.qualityplus.pets.base.rewards.StatReward;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class PetConfig extends OkaeriConfig {
    @CustomKey("pet-entity-options")
    private PetEntityOptions petEntityOptions;
    @CustomKey("pet-egg")
    private PetEgg petEgg;
    @CustomKey("command-rewards")
    private CommandRewards commandRewards;
    @CustomKey("pet-levels")
    private Map<Integer, PetLevel> petLevels;
    @CustomKey("pet-model-engine")
    private PetModelEngine petModelEngine;
    @CustomKey("category")
    private String category;
    @CustomKey("enabled")
    private Boolean enabled;
    @CustomKey("id")
    private String id;
    @CustomKey("max-level")
    private int maxLevel;

    public Pet getPet(){
        return Pet.builder()
                .id(id)
                .category(category)
                .maxLevel(maxLevel)
                .petEgg(petEgg)
                .enabled(enabled)
                .petEntityOptions(petEntityOptions)
                .petLevels(petLevels)
                .build();
    }

    private Map<Integer, PetLevel> getPetLevelsMap(){
        return FasterMap.builder(Integer.class, PetLevel.class)
                .put(1, PetLevel.builder()
                        .requiredXp(15)
                        .petInfoInGUI(Arrays.asList("&8%pet_category_displayname% Pet", "", "&8+&a5 &9☠ Critic Damage",
                                "&8+&a5 &9☣ Critic Chance",
                                "",
                                "&6Fire Resistance Force",
                                "&fAdds &c10% &fFire resistance",
                                "&fprovided from a &cmagic potion&f!"))
                        .petPotions(Collections.singletonList(PetPotion.builder().potion("FIRE_RESISTANCE").level(2).build()))
                        .statRewards(Arrays.asList(new StatReward("critic_chance", 5), new StatReward("critic_damage", 5)))
                        .petInfoInMessage(Arrays.asList("&8+&a5 &9☠ Critic Damage",
                                "&8+&a5 &9☣ Critic Chance",
                                "",
                                "&6Fire Resistance Force",
                                "&fAdds &c+10% &fFire resistance",
                                "&fprovided from a &cmagic potion&f!"))
                        .commandRewards(Collections.emptyList())
                        .build())
                .put(5, PetLevel.builder()
                        .requiredXp(25)
                        .petInfoInGUI(Arrays.asList("&8%pet_category_displayname% Pet", "", "&8+&a10 &9☠ Critic Damage",
                                "&8+&a10 &9☣ Critic Chance",
                                "",
                                "&6Fire Resistance Force",
                                "&fAdds &c+25% &fFire resistance",
                                "&fprovided from a &cmagic potion&f!"))
                        .petPotions(Collections.singletonList(PetPotion.builder().potion("FIRE_RESISTANCE").level(3).build()))
                        .statRewards(Arrays.asList(new StatReward("critic_chance", 10), new StatReward("critic_damage", 10)))
                        .petInfoInMessage(Arrays.asList("&8+&a10 &9☠ Critic Damage",
                                "&8+&a10 &9☣ Critic Chance",
                                "",
                                "&6Fire Resistance Force",
                                "&fAdds &c+25% &fFire resistance",
                                "&fprovided from a &cmagic potion&f!"))
                        .commandRewards(Collections.emptyList())
                        .build())
                .build();

    }

}
