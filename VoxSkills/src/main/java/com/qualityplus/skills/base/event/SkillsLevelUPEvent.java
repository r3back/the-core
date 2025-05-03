package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.SkillEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SkillsLevelUPEvent extends SkillEvent {
    private @Getter final int newLevel;

    public SkillsLevelUPEvent(@NotNull Player who, Skill skill, int newLevel) {
        super(who, skill);

        this.newLevel = newLevel;
    }
}
