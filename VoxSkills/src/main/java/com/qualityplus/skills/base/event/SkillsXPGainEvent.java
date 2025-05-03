package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.SkillEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SkillsXPGainEvent extends SkillEvent {
    private @Getter final boolean showXpActionBar;
    private @Getter @Setter double xp;

    public SkillsXPGainEvent(@NotNull Player who, Skill skill, double xp, boolean showXpActionBar) {
        super(who, skill);
        this.showXpActionBar = showXpActionBar;
        this.xp = xp;
    }
}
