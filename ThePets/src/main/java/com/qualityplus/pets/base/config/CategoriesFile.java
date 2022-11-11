package com.qualityplus.pets.base.config;

import com.qualityplus.pets.api.pet.category.PetCategory;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import eu.okaeri.platform.core.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration(path = "categories.yml")
@Header("================================")
@Header("       Categories      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class CategoriesFile extends OkaeriConfig {
    public List<PetCategory> petCategories = Arrays.asList(new PetCategory("foraging", "Foraging"), new PetCategory("fishing", "Fishing"));
}
