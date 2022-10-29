package com.qualityplus.skills.base.addons;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.addons.registrable.Registrable;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class PlaceholdersRegistry {
    @Delayed(time = MinecraftTimeEquivalent.SECOND * 5)
    public void registerSkillsPlaceholders(@Inject SkillsService service){
        PlaceholdersAddon addon = TheAssistantPlugin.getAPI().getAddons().getPlaceholders();

        for(Skill skill : Skills.values()){
            addon.registerPlaceholders("skill_" + skill.getId() + "_xp",
                    e -> String.valueOf(service.getSkillsData(e.getPlayer().getUniqueId()).map(data -> data.getSkills().getXp(skill.getId())).orElse(0D)));
            addon.registerPlaceholders("skill_" + skill.getId() + "_level",
                    e -> String.valueOf(service.getSkillsData(e.getPlayer().getUniqueId()).map(data -> data.getSkills().getLevel(skill.getId())).orElse(0)));
        }

        for(Perk perk : Perks.values())
            addon.registerPlaceholders("perk_" + perk.getId() + "_level",
                    e -> String.valueOf(service.getSkillsData(e.getPlayer().getUniqueId()).map(data -> data.getSkills().getLevel(perk.getId())).orElse(0)));

        for(Stat stat : Stats.values())
            addon.registerPlaceholders("stat_" + stat.getId() + "_level",
                    e -> String.valueOf(service.getSkillsData(e.getPlayer().getUniqueId()).map(data -> data.getSkills().getLevel(stat.getId())).orElse(0)));


        if(addon instanceof Registrable) ((Registrable) addon).registerAddon();
    }
}
