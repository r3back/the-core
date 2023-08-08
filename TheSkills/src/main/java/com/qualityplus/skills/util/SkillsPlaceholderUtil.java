package com.qualityplus.skills.util;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.actionbar.ActionBarUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.persistance.data.UserData;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for Skills placeholder
 */
@UtilityClass
public class SkillsPlaceholderUtil {
    /**
     *Makes a skills placeholders
     *
     * @param userData {@link UserData}
     * @param skill    {@link Skill}
     * @return List of {@link IPlaceholder}
     */
    public List<IPlaceholder> getSkillsPlaceholders(final UserData userData, final Skill skill) {
        final int level = userData.getSkills().getLevel(skill.getId());

        return getSkillsPlaceholders(userData, skill, level);
    }

    /**
     * Makes an skills placeholder
     *
     * @param userData {@link UserData}
     * @param skill    {@link Skill}
     * @param level    Level
     * @return List of {@link IPlaceholder}
     */
    public List<IPlaceholder> getSkillsPlaceholders(final UserData userData, final Skill skill, final int level) {
        final double xp = userData.getSkills().getXp(skill.getId());
        final double maxXp = skill.getLevelRequirement(level + 1);
        final double percentage = ActionBarUtils.getPercentageFromTotal(xp, maxXp);

        return Arrays.asList(
                new Placeholder("skill_displayname", skill.getDisplayName()),
                new Placeholder("skill_description", skill.getDescription()),
                new Placeholder("skill_level_roman", NumberUtil.toRoman(level)),
                new Placeholder("skill_level_number", level),
                new Placeholder("skill_level_progress", percentage),
                new Placeholder("skill_action_bar", ActionBarUtils.getReplacedBar(percentage)),
                new Placeholder("skill_xp", xp),
                new Placeholder("skill_max_xp", maxXp)
        );
    }

    /**
     * Makes a perks placeholder
     *
     * @param userData {@link UserData}
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getPerksPlaceholders(final UserData userData) {
        final List<IPlaceholder> placeholders = new ArrayList<>();

        final PlaceholderBuilder builder = PlaceholderBuilder.create();

        for (Perk perk : Perks.values()) {
            placeholders.addAll(getCommonPlaceholders(perk, userData.getSkills().getLevel(perk.getId())));
        }
        builder.with(placeholders);

        return builder;
    }

    /**
     * Makes a perks placeholder
     *
     * @param level Level
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getPerksPlaceholders(final Integer level) {
        final List<IPlaceholder> placeholders = new ArrayList<>();

        final PlaceholderBuilder builder = PlaceholderBuilder.create();

        for (Perk perk : Perks.values()) {
            placeholders.addAll(getCommonPlaceholders(perk, level));
        }

        builder.with(placeholders);

        return builder;
    }

    /**
     * Makes stat placeholder
     *
     * @param userData {@link UserData}
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getStatPlaceholders(final UserData userData) {
        final List<IPlaceholder> placeholders = new ArrayList<>();

        final PlaceholderBuilder builder = PlaceholderBuilder.create();

        for (Stat stat : Stats.values()) {
            placeholders.addAll(getCommonPlaceholders(stat, userData.getSkills().getLevel(stat.getId())));
        }
        builder.with(placeholders);


        return builder;
    }

    /**
     * Makes stat placeholder
     *
     * @param level Level
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getStatPlaceholders(final Integer level) {
        final List<IPlaceholder> placeholders = new ArrayList<>();

        final PlaceholderBuilder builder = PlaceholderBuilder.create();

        for (Stat stat : Stats.values()) {
            placeholders.addAll(getCommonPlaceholders(stat, level));
        }
        builder.with(placeholders);


        return builder;
    }

    private static List<IPlaceholder> getCommonPlaceholders(final CommonObject object, final int level) {
        final List<IPlaceholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("skill_level_" + object.getId() + "_number", level));
        placeholders.add(new Placeholder("skill_level_" + object.getId() + "_roman", NumberUtil.toRoman(level)));
        placeholders.add(new Placeholder("skill_" + object.getId() + "_displayname", object.getDisplayName()));
        placeholders.add(new Placeholder("skill_" + object.getId() + "_description", object.getFormattedDescription(level)));
        return placeholders;
    }

    /**
     * Makes a health player
     *
     * @param player {@link Player}
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getHealthPlaceholders(final Player player) {
        return PlaceholderBuilder.create()
                .with(new Placeholder("player_max_health", NumberUtil.toInt(player.getMaxHealth())))
                .with(new Placeholder("player_health", NumberUtil.toInt(player.getHealth())));
    }

    /**
     * Makes an all placeholder builder
     *
     * @param userData {@link UserData}
     * @param skill    {@link Skill}
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getAllPlaceholders(final UserData userData, final Skill skill) {
        return PlaceholderBuilder.create()
                .with(getSkillsPlaceholders(userData, skill))
                .with(getPerksPlaceholders(userData))
                .with(getStatPlaceholders(userData));
    }

    /**
     * Makes an all placeholder builder
     *
     * @param userData {@link UserData}
     * @param skill    {@link Skill}
     * @param level    Level
     * @return {@link PlaceholderBuilder}
     */
    public static PlaceholderBuilder getAllPlaceholders(final UserData userData, final Skill skill, final int level) {
        return PlaceholderBuilder.create()
                .with(getSkillsPlaceholders(userData, skill, level))
                .with(getPerksPlaceholders(level))
                .with(getStatPlaceholders(level));
    }
}
