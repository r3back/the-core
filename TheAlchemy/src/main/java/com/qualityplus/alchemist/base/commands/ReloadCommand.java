package com.qualityplus.alchemist.base.commands;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.api.recipes.Recipes;
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
public final class ReloadCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1){
            box.files().reloadFiles();

            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.successfullyReloaded));
        }else{
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Recipes.values().stream().map(BrewingRecipe::getId).collect(Collectors.toList());
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().reloadCommand));
    }
}