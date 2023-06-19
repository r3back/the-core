package com.qualityplus.alchemist.base.commands;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.recipes.Recipes;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.gui.individual.IndividualRecipeGUI;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Command to create recipes
 */
@Component
public final class CreateRecipeCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final Player player = (Player) sender;
        final Messages.RecipeMessages msg = this.box.getFiles().messages().getRecipeMessages();
        final Messages.PluginMessages pluginMsg = this.box.getFiles().messages().getPluginMessages();

        if (args.length == 2) {
            final String id = args[1];

            final BrewingRecipe exist = Recipes.getByID(id);

            if (exist != null) {
                player.sendMessage(StringUtils.color(msg.getRecipeAlreadyExist()));
                return false;
            }

            final BrewingRecipe recipe1 = BrewingRecipe.builder()
                    .id(id)
                    .displayName(id)
                    .description(StringUtils.color(msg.getDefaultDescription()))
                    .recipePermission("permission." + id)
                    .timer(new HumanTime(10, HumanTime.TimeType.SECONDS))
                    .build();

            recipe1.register();

            this.box.getFiles().recipes().getBrewingRecipes().add(recipe1);

            player.openInventory(new IndividualRecipeGUI(this.box, recipe1).getInventory());
        } else {
            player.sendMessage(StringUtils.color(pluginMsg.getUseSyntax().replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        return Collections.emptyList();
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
                .registerCommand(this, e -> e.getCommand().setDetails(box.getFiles().commands().getCreateCommand()));
    }
}
