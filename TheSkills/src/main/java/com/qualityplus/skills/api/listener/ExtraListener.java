package com.qualityplus.skills.api.listener;

import com.qualityplus.skills.base.skill.Skill;
import org.bukkit.event.Listener;

/**
 * Utility class for listener
 */
public interface ExtraListener extends Listener {
    /**
     * Adds a apply skill
     *
     * @param skill {@link Skill}
     */
    public void applySkill(final Skill skill);
}
