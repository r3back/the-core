package com.qualityplus.pets.base.config.pet;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.egg.PetEgg;
import com.qualityplus.pets.base.pet.entity.PetEntityOptions;
import com.qualityplus.pets.base.pet.gui.PetGUIOptions;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;

import java.util.*;

@Getter
@Configuration(path = "pets/tiger_pet.yml")
@Header("================================")
@Header("       Tiger Pet      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class Tiger extends OkaeriConfig {
    private final PetEntityOptions petEntityOptions = new PetEntityOptions("&6Tiger", XMaterial.PLAYER_HEAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDZkZjM3OGJlZTY5ODBhNzg0NTkzYTFhZTk4OTI4MGE2ZTk2MjFhOTE5MTcyZGE0OWQzMTgxNWYzYzQ5Mjg2ZSJ9fX0=");
    private final PetEgg petEgg = new PetEgg("tiger",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDZkZjM3OGJlZTY5ODBhNzg0NTkzYTFhZTk4OTI4MGE2ZTk2MjFhOTE5MTcyZGE0OWQzMTgxNWYzYzQ5Mjg2ZSJ9fX0=",
            Arrays.asList(
                    "&8Foraging Pet",
                    "",
                    "&7Speed: &a+20.25",
                    "&7Strength: &c+40.5",
                    "&7Ferocity: &a+4.05",
                    "",
                    "&6Primal Force",
                    "&7Adds &c+12.15 Damage &7and",
                    "&c+12.15 Strength &7to your",
                    "&7weapons."
            ),"&6Tiger", XMaterial.PLAYER_HEAD, "&7[Lvl %pet_level_number%] %pet_egg_displayname%");
    private final CommandRewards commandRewards = new CommandRewards(new HashMap<>());
    private final Map<Integer, Double> xpRequirements = FasterMap.builder(Integer.class, Double.class)
            .put(1, 15D)
            .put(2, 25D)
            .put(3, 35D)
            .put(4, 45D)
            .put(5, 55D)
            .put(6, 65D)
            .put(7, 75D)
            .put(8, 85D)
            .put(9, 95D)
            .put(10, 105D)

            .build();
    private final Boolean enabled = true;
    private final String id = "tiger";
    private final int maxLevel = 5;

    public Pet getPet(){
        return Pet.builder()
                .id(id)
                .maxLevel(maxLevel)
                .petEgg(petEgg)
                .enabled(enabled)
                .commandRewards(commandRewards)
                .petEntityOptions(petEntityOptions)
                .xpRequirements(xpRequirements)
                .build();
    }

}
