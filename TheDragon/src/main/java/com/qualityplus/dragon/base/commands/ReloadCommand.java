package com.qualityplus.dragon.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.dragon.api.box.Box;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.CommandSender;

import java.util.List;

@Component
public final class ReloadCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            box.files().reloadFiles();

            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.successfullyReloaded.replace("%prefix%", box.files().config().prefix)));
        }else{
            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return null;
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().reloadCommand));
    }
}
