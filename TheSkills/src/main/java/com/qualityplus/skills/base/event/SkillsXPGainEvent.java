package com.qualityplus.skills.base.event;

import com.qualityplus.skills.api.event.SkillEvent;
import com.qualityplus.skills.base.skill.Skill;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class dor skills xp gain
 */
public final class SkillsXPGainEvent extends SkillEvent {
    private @Getter final boolean showXpActionBar;
    private @Getter @Setter double xp;

    /**
     * Makes a skills xp gain
     *
     * @param who             Who
     * @param skill           {@link Skill}
     * @param xp              Xp
     * @param showXpActionBar Show Xp Action Bar
     */
    public SkillsXPGainEvent(@NotNull final Player who, final Skill skill, final double xp, final boolean showXpActionBar) {
        super(who, skill);
        this.showXpActionBar = showXpActionBar;
        this.xp = xp;
    }
}
