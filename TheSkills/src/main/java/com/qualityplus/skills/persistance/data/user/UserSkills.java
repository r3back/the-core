package com.qualityplus.skills.persistance.data.user;

import com.qualityplus.assistant.api.data.Levellable;
import com.qualityplus.assistant.api.data.Progressable;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Exclude;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.persistance.data.user.armor.LevellableArmorData;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for user skills
 */
@Getter
@Setter
public final class UserSkills extends Document implements Levellable<String, Integer>, Progressable<String, Double>,
        LevellableArmorData<String> {
    @Exclude
    private Map<String, Integer> fromArmor = new HashMap<>();
    private Map<String, Integer> level = new HashMap<>();
    private Map<String, Double> xp = new HashMap<>();

    /**
     * Adds a fill if empty
     */
    public void fillIfEmpty() {
        Skills.values().stream().map(Skill::getId).forEach(skill -> this.level.putIfAbsent(skill, 0));
        Skills.values().stream().map(Skill::getId).forEach(skill -> this.xp.putIfAbsent(skill, 0D));
        Skills.values().stream().map(Skill::getId).forEach(skill -> this.fromArmor.putIfAbsent(skill, 0));

        //Perks
        Perks.values().stream().map(Perk::getId).forEach(perk -> this.level.putIfAbsent(perk, 0));
        Perks.values().stream().map(Perk::getId).forEach(perk -> this.fromArmor.putIfAbsent(perk, 0));
        //Stats
        Stats.values().stream().map(Stat::getId).forEach(perk -> this.level.putIfAbsent(perk, 0));
        Stats.values().stream().map(Stat::getId).forEach(perk -> this.fromArmor.putIfAbsent(perk, 0));
    }

    @Override
    public Integer getDefault() {
        return 0;
    }

    @Override
    public Double getDefaultXp() {
        return 0D;
    }
}
