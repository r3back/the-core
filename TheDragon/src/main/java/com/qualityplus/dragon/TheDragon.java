package com.qualityplus.dragon;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.dragon.api.box.Box;
import com.qualityplus.dragon.persistance.data.UserData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import com.qualityplus.dragon.api.TheDragonAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.logging.Logger;

@Scan(deep = true)
public final class TheDragon extends OkaeriSilentPlugin {
    private static @Inject @Getter TheDragonAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(Box box){
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .forEach(uuid -> box.usersDB().getData(uuid).ifPresent(UserData::save));

        box.game().finish();

        box.files().saveFiles();
    }

    @Planned(ExecutionPhase.PRE_SETUP)
    private void preSetup(@Inject Logger logger){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/messages.yml";

            File file = new File(path);

            if(!file.exists()){
                logger.info("Everything is fine, starting setup!");
                return;
            }

            logger.info("Checking to auto-fix messages.yml file...");

            FileConfiguration customConfig = new YamlConfiguration();

            customConfig.load(file);

            if(customConfig.contains("setup-messages.game-end-message")){
                customConfig.set("setup-messages.game-end-message", null);
                customConfig.save(file);

                logger.info("messages.yml was corrupt, it has been fixed...");
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