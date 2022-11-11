package com.qualityplus.pets.listener.skills;

import com.qualityplus.skills.base.event.SkillsXPGainEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkillsListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onGainXp(SkillsXPGainEvent event){

    }
}
