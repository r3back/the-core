package com.qualityplus.skills;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.skills.api.TheSkillsAPI;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.logging.Logger;

@Scan(deep = true)
public final class TheSkills extends OkaeriSilentPlugin {
    private static @Inject @Getter TheSkillsAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(Box box){
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .forEach(uuid -> box.service().getData(uuid).ifPresent(UserData::save));
    }

    @Planned(ExecutionPhase.PRE_SETUP)
    private void preSetup(@Inject Logger logger){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/stats/critic_damage.yml";

            File file = new File(path);

            if(!file.exists()){
                logger.info("Everything is fine, starting setup!");
                return;
            }

            logger.info("Checking to auto-fix critic-damage.yml file...");

            FileConfiguration customConfig = new YamlConfiguration();

            customConfig.load(file);

            if(customConfig.contains("crit-damage-stat.chancePerLevel")){
                file.delete();
                logger.info("critic-damage.yml was corrupt, it has been deleted...");
                logger.info("Now Everything is fine, starting setup!");
            }else{
                logger.info("Everything is fine, starting setup!");
            }
        }catch (Exception e){
            logger.warning("Error trying to fix corrupt file!");
            e.printStackTrace();
        }
    }
}
