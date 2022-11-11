package com.qualityplus.skills;

import com.qualityplus.skills.api.TheSkillsAPI;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.converter.TheSkillsConverterPlugin;
import com.qualityplus.skills.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Scan(deep = true)
public final class TheSkills extends TheSkillsConverterPlugin {
    private static @Inject @Getter TheSkillsAPI api;

    @Override
    @Planned(ExecutionPhase.POST_SETUP)
    protected void convertSkills(Box box) {
        super.convertSkills(box);
        super.moveSeaFortuneFile(box);
        super.convertStatsAndPerks(box, "stats", "stat");
        super.convertStatsAndPerks(box, "perks", "perk");
    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(Box box){
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .forEach(uuid -> box.service().getData(uuid).ifPresent(UserData::save));
    }
}
