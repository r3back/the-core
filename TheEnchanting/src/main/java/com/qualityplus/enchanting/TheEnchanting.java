package com.qualityplus.enchanting;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XEnchantment;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.enchanting.api.TheEnchantingAPI;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.ExecutionPhase;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Scan(deep = true)
public final class TheEnchanting extends OkaeriSilentPlugin {
    private static @Inject
    @Getter TheEnchantingAPI api;

    @Planned(ExecutionPhase.PRE_SETUP)
    private void preSetupFixVanilla(@Inject Logger logger){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/enchantments/vanilla_enchantments.yml";

            File file = new File(path);

            if(!file.exists())
                return;

            logger.info("Checking to auto-fix vanilla_enchantments.yml file...");

            FileConfiguration customConfig = new YamlConfiguration();

            customConfig.load(file);

            ConfigurationSection section = customConfig.getConfigurationSection("vanilla-enchantments");

            if(section == null)
                return;

            for(String configPath : section.getKeys(false)){
                String permissionPath = "vanilla-enchantments." + configPath + ".requiredPermissionsToEnchant";
                String xpPath = "vanilla-enchantments." + configPath + ".requiredXpLevelToEnchant";
                String moneyPath = "vanilla-enchantments." + configPath + ".requiredMoneyToEnchant";

                try {
                    XEnchantment enchantment = XEnchantment.valueOf(configPath);
                    Map<Integer, String> pathValues = new HashMap<>();
                    Map<Integer, Double> xpValues = new HashMap<>();
                    Map<Integer, Double> moneyValues = new HashMap<>();

                    if(enchantment.getEnchant() == null) continue;

                    for(int i = 1; i<= enchantment.getEnchant().getMaxLevel(); i++){
                        String numberPath = permissionPath + "." + i;

                        if(customConfig.contains(numberPath))
                            pathValues.put(i, customConfig.getString(numberPath));
                        else
                            pathValues.put(i, "ench."+key(enchantment)+".level." + i);

                    }
                    customConfig.set(permissionPath, null);

                    for(Map.Entry<Integer, String> entry : pathValues.entrySet()){
                        String numberPath = permissionPath + "." + entry.getKey();

                        customConfig.set(numberPath, entry.getValue());
                    }

                    for(String toChangePath : Arrays.asList(xpPath, moneyPath)){
                        boolean isXpPath = toChangePath.contains("requiredXpLevelToEnchant");

                        for(int i = 1; i<= enchantment.getEnchant().getMaxLevel(); i++){
                            String numberPath = toChangePath + "." + i;

                            if(customConfig.contains(numberPath)) {
                                if(isXpPath)
                                    xpValues.put(i, customConfig.getDouble(numberPath));
                                else
                                    moneyValues.put(i, customConfig.getDouble(numberPath));
                            }else {
                                if(isXpPath)
                                    xpValues.put(i, (double) i);
                                else
                                    moneyValues.put(i, 15D * i);
                            }
                            customConfig.set(toChangePath, null);
                        }
                        Map<Integer, Double> toCheckMap = isXpPath ? xpValues : moneyValues;

                        for(Map.Entry<Integer, Double> entry : toCheckMap.entrySet()){
                            String numberPath = toChangePath + "." + entry.getKey();

                            customConfig.set(numberPath, entry.getValue());
                        }
                    }


                    if(pathValues.size() > 0 || moneyValues.size() > 0 || xpValues.size() > 0)
                        customConfig.save(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            logger.warning("Error trying to fix corrupt file!");
            e.printStackTrace();
        }
    }

    private String key(XEnchantment enchantment){
        return enchantment.toString().replace(" ", "_").toLowerCase();
    }

    @Planned(ExecutionPhase.PRE_SETUP)
    private void preSetup(@Inject Logger logger){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/config.yml";

            File file = new File(path);

            if(!file.exists()){
                logger.info("Everything is fine, starting setup!");
                return;
            }

            logger.info("Checking to auto-fix config.yml file...");

            FileConfiguration customConfig = new YamlConfiguration();

            customConfig.load(file);

            if(!customConfig.contains("enchantments-display.display-enchantments-in-lore")){
                customConfig.set("enchantments-display.display-enchantments-in-lore", true);
                customConfig.save(file);

                logger.info("config.yml was corrupt, it has been fixed...");
                logger.info("Now Everything is fine, starting setup!");
            }else{
                logger.info("Everything is fine, starting setup!");
            }
        }catch (Exception e){
            logger.warning("Error trying to fix corrupt file!");
            e.printStackTrace();
        }
    }

    @Planned(ExecutionPhase.PRE_SETUP)
    private void preSetupRecipes(){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/recipes.yml";

            File file = new File(path);

            if(!file.exists())
                return;

            Optional.ofNullable(file).ifPresent(File::delete);
        }catch (Exception ignored){
        }
    }
}
