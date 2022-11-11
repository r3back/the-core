package com.qualityplus.minions.base.minion.registry;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableSet;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minion.entity.MinionEntityOptions;
import com.qualityplus.minions.base.minion.layout.LayoutType;
import com.qualityplus.minions.base.minion.layout.MinionLayout;
import com.qualityplus.minions.base.minion.level.MinionLevel;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public final class Minions {
    private static final Map<String, Minion> MINIONS_REGISTRY = new HashMap<>();
    private static @Inject Logger LOGGER;

    @ApiStatus.Internal
    public static void registerNewPet(@NotNull final Minion pet) {
        MINIONS_REGISTRY.put(pet.getId().toLowerCase(), pet);
    }

    @Nullable
    public static Minion getByID(@NotNull final String id) {
        return MINIONS_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static Minion getByKey(@NotNull final NamespacedKey key) {
        return MINIONS_REGISTRY.get(key.getKey());
    }

    public static Set<Minion> values() {
        return ImmutableSet.copyOf(MINIONS_REGISTRY.values());
    }

    public static Set<Minion> values(Predicate<Minion> filter) {
        return ImmutableSet.copyOf(MINIONS_REGISTRY.values().stream().filter(filter).collect(Collectors.toList()));
    }

    @Delayed(time = 1)
    public static void reloadPets(){

        Minion minion = Minion.builder()
                .id("miner")
                .displayName("Miner")
                .minionLayout(MinionLayout.builder()
                        .type(LayoutType.TWO_X_TWO)
                        .build())
                .minionEntityOptions(MinionEntityOptions.builder()
                        .material(XMaterial.PLAYER_HEAD)
                        .particle("")
                        .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVlMjhjMjYyMDhjMjQ1OGU3MzQ1NGY4NGRlOTkwNDNlNGExOTkxNjhiOGFhZDA0YjQwNjI4ZjM3NzNhN2FlYSJ9fX0=")
                        .displayName("Miner Entity")
                        .build())
                .minionEgg(MinionEgg.builder()
                        .petId("miner")
                        .displayName("Miner Egg")
                        .material(XMaterial.PLAYER_HEAD)
                        .eggDisplayName("Miner Egg DisplayName")
                        .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVlMjhjMjYyMDhjMjQ1OGU3MzQ1NGY4NGRlOTkwNDNlNGExOTkxNjhiOGFhZDA0YjQwNjI4ZjM3NzNhN2FlYSJ9fX0=")
                        .build())
                .minionLevels(FasterMap.builder(Integer.class, MinionLevel.class)
                        .put(1, MinionLevel.builder()
                                .executeActionsTime(new Timer(3, Timer.TimeType.SECONDS))
                                .build())
                        .build())
                .description(Arrays.asList("Line 1", "Line 2"))
                .build();

        registerNewPet(minion);

    }
}
