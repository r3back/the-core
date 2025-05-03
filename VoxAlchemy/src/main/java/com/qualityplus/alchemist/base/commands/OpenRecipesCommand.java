package com.qualityplus.alchemist.base.commands;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.alchemist.base.gui.recipes.RecipesGUI;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Command to open recipes gui
 */
@Component
public final class OpenRecipesCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(final CommandSender sender, final String[] args) {
        final Messages.PluginMessages messages = this.box.getFiles().messages().getPluginMessages();

        final String syntaxMsg = StringUtils.color(messages.getUseSyntax().replace("%usage%", this.syntax));
        final String mustBeAPlayer = StringUtils.color(messages.getMustBeAPlayer().replace("%usage%", this.syntax));
        final String invalidPlayer = StringUtils.color(messages.getInvalidPlayer().replace("%usage%", this.syntax));

        return openInventory(args, sender, new RecipesGUI(this.box, 1), syntaxMsg, mustBeAPlayer, invalidPlayer);
    }

    @Override
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        return args.length == 2 ? null : Collections.emptyList();
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
                .registerCommand(this, e -> e.getCommand().setDetails(box.getFiles().commands().getRecipesCommand()));
    }
}
