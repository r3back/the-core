package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.SkillEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for skills level up
 */
public final class SkillsLevelUPEvent extends SkillEvent {
    private @Getter final int newLevel;

    /**
     * Makes a skills level up
     *
     * @param who      Who
     * @param skill    {@link Skill}
     * @param newLevel New Level
     */
    public SkillsLevelUPEvent(@NotNull final Player who, final Skill skill, final int newLevel) {
        super(who, skill);

        this.newLevel = newLevel;
    }
}
