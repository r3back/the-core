package com.qualityplus.skills.api.event;

import com.qualityplus.assistant.api.event.PlayerAssistantEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class SkillEvent extends PlayerAssistantEvent {
    private Skill skill;

    public SkillEvent(@NotNull Player who, Skill skill) {
        super(who);
        this.skill = skill;
    }
}
