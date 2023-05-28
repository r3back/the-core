package com.qualityplus.alchemist.base.commands;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to delete recipes
 */
@Component
public final class DeleteRecipeCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final Player player = (Player) sender;
        final Messages.RecipeMessages msg = this.box.getFiles().messages().getRecipeMessages();
        final Messages.PluginMessages pluginMsg = this.box.getFiles().messages().getPluginMessages();

        if (args.length == 2) {
            final String id = args[1];

            final BrewingRecipe exist = Recipes.getByID(id);

            if (exist == null) {
                player.sendMessage(StringUtils.color(msg.getRecipeDoesntExist()));
                return false;
            }

            Recipes.removeByID(exist.getId());

            this.box.getFiles().recipes().getBrewingRecipes().remove(exist);

            player.sendMessage(StringUtils.color(msg.getSuccessfullyDeletedRecipe()));
        } else {
            player.sendMessage(StringUtils.color(pluginMsg.getUseSyntax().replace("%usage%", this.syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        return Recipes.values().stream()
                .map(BrewingRecipe::getId)
                .collect(Collectors.toList());
    }

    /**
     * Register command
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject final Box box) {
        TheAssistantPlugin.getAPI()
                .getCommandProvider()
                .registerCommand(this, e -> e.getCommand().setDetails(box.getFiles().commands().getDeleteCommand()));
    }
}
