package com.qualityplus.assistant.api.commands.command;

import com.qualityplus.assistant.api.commands.CommandProvider;
import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.api.commands.setup.event.CommandSetupEvent;
import com.qualityplus.assistant.api.commands.setup.handler.CommandSetupHandler;
import eu.okaeri.injector.annotation.Inject;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AssistantCommand extends CommandDetails {
    public CommandSetupHandler<AssistantCommand> commandCompleteHandler;
    public CommandDetails commandDetails;

    public abstract boolean execute(CommandSender sender, String[] arguments);

    public abstract List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args);

    public void setDetails(CommandDetails details){
        this.commandDetails = details;
    }

    public void setup(CommandSetupHandler<AssistantCommand> commandSetupHandler){
        this.commandCompleteHandler = commandSetupHandler;
        this.reload();
    }

    public void reload(){
        //Setup Details
        this.commandCompleteHandler.onCompleteCommand(new CommandSetupEvent<>(this));
        super.cooldownInSeconds = commandDetails.cooldownInSeconds;
        super.onlyForPlayers = commandDetails.onlyForPlayers;
        super.labelProvider = commandDetails.labelProvider;
        super.description = commandDetails.description;
        super.permission = commandDetails.permission;
        super.enabled = commandDetails.enabled;
        super.aliases = commandDetails.aliases;
        super.syntax = commandDetails.syntax;
    }


    protected void sendHelpCommands(CommandSender sender, String[] args, CommandProvider<AssistantCommand> commands, String helpHeader, String helpMessage, String helpFooter,
                                    String nextPage, String previousPage, String helpPageHoverMessage){
        Player p = (Player) sender;
        int page = 1;
        if (args.length == 2) {
            if (!org.apache.commons.lang.StringUtils.isNumeric(args[1])) return;

            page = Integer.parseInt(args[1]);
        }

        double maxPerPage = 8;
        List<AssistantCommand> list = commands.getCommands().stream().filter(command -> command.labelProvider.equals(labelProvider)).collect(Collectors.toList());

        int maxpage = (int) Math.ceil(list.size() / maxPerPage);
        int current = 0;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpHeader));
        for (AssistantCommand command : list) {
            if ((p.hasPermission(command.permission) || command.permission.equalsIgnoreCase("") || command.permission.equalsIgnoreCase(command.labelProvider + ".")) && command.enabled) {
                if (current >= (page - 1) * maxPerPage && current < page * maxPerPage)
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpMessage.replace("%command%", command.syntax).replace("%description%", command.description)));
                current++;
            }
        }
        BaseComponent[] components = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', helpFooter.replace("%maxpage%", maxpage + "").replace("%page%", page + "")));

        for (BaseComponent component : components) {
            if (ChatColor.stripColor(component.toLegacyText()).contains(nextPage)) {
                if (page < maxpage) {
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandDetails.labelProvider +" help " + (page + 1)));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(helpPageHoverMessage.replace("%page%", "" + (page + 1))).create()));
                }
            } else if (ChatColor.stripColor(component.toLegacyText()).contains(previousPage)) {
                if (page > 1) {
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandDetails.labelProvider +" help " + (page - 1)));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(helpPageHoverMessage.replace("%page%", "" + (page - 1))).create()));
                }
            }
        }
        p.getPlayer().spigot().sendMessage(components);
    }
}
