package com.qualityplus.skills.base.skill.registry;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.api.provider.MinionsProvider;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.skills.blockbreak.BlockBreakSkill;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for skills
 */
@Component
public final class Skills {
    private static final String REGISTERED_SKILLS_MESSAGE = "Registered %s Skills!";
    private static final Map<String, Skill> SKILL_REGISTRY = new HashMap<>();

    /**
     * Adds a skill
     *
     * @param skill {@link Skill}
     */
    @ApiStatus.Internal
    public static void registerNewSkill(@NotNull final Skill skill) {
        SKILL_REGISTRY.put(skill.getId().toLowerCase(), skill);
    }

    /**
     * Adds a by id
     *
     * @param id Id
     * @return   {@link Skill}
     */
    @Nullable
    public static Skill getByID(@NotNull final String id) {
        return SKILL_REGISTRY.get(id.toLowerCase());
    }

    /**
     * Adds a by key
     *
     * @param key Key
     * @return    {@link NamespacedKey}
     */
    @Nullable
    public static Skill getByKey(@NotNull final NamespacedKey key) {
        return SKILL_REGISTRY.get(key.getKey());
    }


    /**
     * Adds a skill
     *
     * @return {@link Skill}
     */
    public static Set<Skill> values() {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values());
    }

    /**
     * Adds a filter
     *
     * @param filter Filter
     * @return       {@link Skill}
     */
    public static Set<Skill> values(final Predicate<Skill> filter) {
        return ImmutableSet.copyOf(SKILL_REGISTRY.values().stream().filter(filter).collect(Collectors.toList()));
    }

    /**
     * Makes a reload skill
     *
     * @param box      {@link Box}
     * @param provider Provider
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND, async = true)
    public static void reloadSkills(@Inject final Box box, @Inject final MinionsProvider provider) {
        values().forEach(Skill::unregisterListeners);

        Stream.of(box.skillFiles().dungeoneering().getSkill(),
                        box.skillFiles().runeCrafting().getSkill(),
                        box.skillFiles().discoverer().getSkill(),
                        box.skillFiles().enchanting().getSkill(),
                        box.skillFiles().carpentry().getSkill(),
                        box.skillFiles().foraging().getSkill(),
                        box.skillFiles().alchemy().getSkill(),
                        box.skillFiles().farming().getSkill(),
                        box.skillFiles().fishing().getSkill(),
                        box.skillFiles().combat().getSkill(),
                        box.skillFiles().mining().getSkill(),
                        box.skillFiles().taming().getSkill())
                .filter(Skill::isEnabled)
                .forEach(Skill::register);

        Skills.registerListeners(box, provider);

        box.log().info(String.format(REGISTERED_SKILLS_MESSAGE, values().size()));

    }

    private static void registerListeners(final Box box, final MinionsProvider provider) {
        for (Skill skill : SKILL_REGISTRY.values()) {

            if (skill instanceof BlockBreakSkill) {
                final Optional<Class<? extends ExtraListener>> listener = provider.getExtraListener();

                listener.ifPresent(skill::addExtraListener);
            }

            skill.registerListeners(box);
        }
    }
}
