package com.qualityplus.skills.api.listener;

import com.qualityplus.skills.base.skill.Skill;
import org.bukkit.event.Listener;

public interface ExtraListener extends Listener {
    public void applySkill(final Skill skill);
}
