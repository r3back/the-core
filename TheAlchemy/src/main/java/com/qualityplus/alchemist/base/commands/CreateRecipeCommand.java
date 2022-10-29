package com.qualityplus.alchemist.base.commands;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.time.Timer;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@Component
public final class CreateRecipeCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 2){
            String id = args[1];

            BrewingRecipe exist = Recipes.getByID(id);

            if(exist != null){
                player.sendMessage(StringUtils.color(box.files().messages().recipeMessages.recipeAlreadyExist));
                return false;
            }

            BrewingRecipe recipe1 = BrewingRecipe.builder()
                    .id(id)
                    .displayName(id)
                    .description(StringUtils.color(box.files().messages().recipeMessages.defaultDescription))
                    .recipePermission("permission." + id)
                    .timer(new Timer(10, Timer.TimeType.SECONDS))
                    .build();

            recipe1.register();

            box.files().recipes().brewingRecipes.add(recipe1);

            player.openInventory(new IndividualRecipeGUI(box, recipe1).getInventory());
        }else{
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().createCommand));
    }
}