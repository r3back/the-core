package com.qualityplus.crafting.base.commands;

import com.qualityplus.crafting.api.box.Box;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import com.qualityplus.crafting.api.recipes.Recipes;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class DeleteRecipeCommand extends AssistantCommand {
    private @Inject
    Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 2){
            String id = args[1];

            CustomRecipe exist = Recipes.getByID(id);

            if(exist == null){
                player.sendMessage(StringUtils.color(box.files().messages().recipeMessages.recipeDoesntExist));
                return false;
            }

            box.files().recipes().brewingRecipes.remove(exist);

            player.sendMessage(StringUtils.color(box.files().messages().recipeMessages.successfullyDeletedRecipe));
        }else{
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Recipes.values().stream().map(CustomRecipe::getId).collect(Collectors.toList());
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().deleteCommand));
    }
}