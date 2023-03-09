package com.qualityplus.crafting;

import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.crafting.api.TheCraftingAPI;
import com.qualityplus.crafting.base.config.RecipesFile;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Scan(deep = true)
public final class TheCrafting extends OkaeriSilentPlugin {
    private static @Inject @Getter TheCraftingAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void saveOnShutdown(@Inject RecipesFile recipesFile) {
        recipesFile.saveRecipes();
    }

    /*@Planned(ExecutionPhase.POST_SETUP)
    private void preSetup(@Inject Logger logger, @Inject RecipesFile recipesFile){
        try {
            String path = this.getDataFolder().getAbsolutePath() + "/old/recipes.yml";

            Bukkit.getConsoleSender().sendMessage("Path" + path);
            File file = new File(path);

            if(!file.exists()){
                logger.info("Everything is fine, starting setup!");
                return;
            }

            logger.info("Checking to auto-fix recipes.yml file...");

            YamlConfiguration customConfig = new YamlConfiguration();

            customConfig.load(file);

            int amount = 0;

            for(String id : customConfig.getConfigurationSection("recipes").getKeys(false)){
                ItemStack result = customConfig.getItemStack("recipes." + id + ".result");

                int page = customConfig.getInt("recipes." + id + ".page");
                int slot = customConfig.getInt("recipes." + id + ".slot");
                String category = customConfig.getString("recipes." + id + ".category");
                String displayName = customConfig.getString("recipes." + id + ".displayName");

                Map<Integer, String> ingredients = new HashMap<>();

                for(int i = 1; i <= 10; i++){

                    if(!customConfig.contains("recipes." + id + "." + i)) continue;

                    //printIf(id, i + " | " + "A ");

                    ItemStack item = customConfig.getItemStack("recipes." + id + "." + i);

                    if(BukkitItemUtil.isNull(item)) continue;
                    //printIf(id, i + " | " + "B ");

                    ingredients.put(i+ 1, BukkitItemUtil.serialize(item));

                }


                CustomRecipe customRecipe = CustomRecipe.builder()
                        .displayName(displayName)
                        .recipePermission("recipe." + id)
                        .ingredientsSerialized(ingredients)
                        .resultSerialized(BukkitItemUtil.serialize(result))
                        .id(id)
                        .category(category)
                        .slot(slot)
                        .page(page)
                        .build();

                recipesFile.craftingRecipes.add(customRecipe);
                amount++;

            }

            recipesFile.save();

            logger.info("Loaded " + amount + " Old recipes from UC!");

            Bukkit.getServer().shutdown();

        }catch (Exception e){
            logger.warning("Error trying to fix corrupt file!");
            e.printStackTrace();
        }
    }

    private void printIf(String id, String toPrint){
        if(!id.equals("blobfish_hat")) return;

        Bukkit.getConsoleSender().sendMessage(toPrint);
    }*/
}
