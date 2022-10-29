package com.qualityplus.skills.base.skill.registry;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.skill.Skill;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
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
import java.util.stream.Stream;

@Component
public final class Skills {
    private static final Map<String, Skill> SKILL_REGISTRY = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewSkill(@NotNull final Skill skill) {
        SKILL_REGISTRY.put(skill.getId().toLowerCase(), skill);
    }

    @Nullable
    public static Skill getByID(@NotNull final String id) {
        return SKILL_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static Skill getByKey(@NotNull final NamespacedKey key) {
        return SKILL_REGISTRY.get(key.getKey());
    }

    public static Set<Skill> values() {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values());
    }

    public static Set<Skill> values(Predicate<Skill> filter) {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values().stream().filter(filter).collect(Collectors.toList()));
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND, async = true)
    public static void reloadSkills(@Inject Box box) {
        values().forEach(Skill::unregisterListeners);

        Stream.of(box.skillFiles().dungeoneering().dungeoneeringSkill,
                        box.skillFiles().runeCrafting().runecraftingSkill,
                        box.skillFiles().discoverer().discovererSkill,
                        box.skillFiles().enchanting().enchantingSkill,
                        box.skillFiles().carpentry().carpentrySkill,
                        box.skillFiles().foraging().foragingSkill,
                        box.skillFiles().alchemy().alchemySkill,
                        box.skillFiles().farming().farmingSkill,
                        box.skillFiles().fishing().fishingSkill,
                        box.skillFiles().combat().combatSkill,
                        box.skillFiles().mining().miningSkill,
                        box.skillFiles().taming().tamingSkill)
                .filter(Skill::isEnabled)
                .forEach(skill -> {
                    skill.register();
                    skill.registerListeners(box);
                });

        box.log().info("Registered " + values().size() + " Skills!");

    }

}
