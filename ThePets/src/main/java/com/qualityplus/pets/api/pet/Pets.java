package com.qualityplus.pets.api.pet;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.base.config.pet.Tiger;
import com.qualityplus.pets.base.pet.Pet;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public final class Pets {
    private static final Map<String, Pet> SKILL_REGISTRY = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewSkill(@NotNull final Pet pet) {
        SKILL_REGISTRY.put(pet.getId().toLowerCase(), pet);
    }

    @Nullable
    public static Pet getByID(@NotNull final String id) {
        return SKILL_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static Pet getByKey(@NotNull final NamespacedKey key) {
        return SKILL_REGISTRY.get(key.getKey());
    }

    public static Set<Pet> values() {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values());
    }

    public static Set<Pet> values(Predicate<Pet> filter) {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values().stream().filter(filter).collect(Collectors.toList()));
    }

    @Delayed(time = 1)
    public static void reloadPets(@Inject Tiger box){
        registerNewSkill(box.getPet());

        //box.log().info("Registered " + values().size() + " Skills!");
    }
}
