package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for player assistant event
 */
@Getter
@Setter
public abstract class SkillEvent extends PlayerAssistantEvent {
    private Skill skill;

    /**
     * adds a skill event
     *
     * @param who    {@link Player}
     * @param skill  {@link Skill}
     */
    public SkillEvent(@NotNull final Player who, final Skill skill) {
        super(who);
        this.skill = skill;
    }
}
