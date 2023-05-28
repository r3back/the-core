package com.qualityplus.assistant.api.commands.command;

import com.qualityplus.assistant.api.commands.CommandProvider;
import com.qualityplus.assistant.api.commands.details.CommandDetails;
import com.qualityplus.assistant.api.commands.setup.event.CommandSetupEvent;
import com.qualityplus.assistant.api.commands.setup.handler.CommandSetupHandler;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract command class
 */
public abstract class AssistantCommand extends CommandDetails {
    public CommandSetupHandler<AssistantCommand> commandCompleteHandler;
    public CommandDetails commandDetails;

    /**
     * Retrieves if command was successfully executed
     *
     * @param sender {@link CommandSender}
     * @param args   command arguments
     * @return true if command was executed
     */
    public abstract boolean execute(CommandSender sender, String[] args);

    /**
     * Handle command tab completion
     *
     * @param commandSender {@link CommandSender}
     * @param command       {@link Command}
     * @param label         command label
     * @param args          command arguments
     * @return List of {@link String}
     */
    public abstract List<String> onTabComplete(final CommandSender commandSender, final Command command, final String label, final String[] args);

    /**
     * Set command details
     *
     * @param details {@link CommandDetails}
     */
    public void setDetails(final CommandDetails details){
        this.commandDetails = details;
    }

    /**
     *
     * @param commandSetupHandler CommandSetupHandler of {@link AssistantCommand}
     */
    public void setup(final CommandSetupHandler<AssistantCommand> commandSetupHandler){
        this.commandCompleteHandler = commandSetupHandler;
        this.reload();
    }

    /**
     * Reload command info
     */
    public void reload(){
        //Setup Details
        this.commandCompleteHandler.onCompleteCommand(new CommandSetupEvent<>(this));
        super.cooldownInSeconds = commandDetails.getCooldownInSeconds();
        super.onlyForPlayers = commandDetails.isOnlyForPlayers();
        super.labelProvider = commandDetails.getLabelProvider();
        super.description = commandDetails.getDescription();
        super.permission = commandDetails.getPermission();
        super.enabled = commandDetails.isEnabled();
        super.aliases = commandDetails.getAliases();
        super.syntax = commandDetails.getSyntax();
    }


    /**
     *
     * @param args          Command arguments
     * @param sender        {@link CommandSender}
     * @param gui           {@link InventoryHolder}
     * @param useSyntax     Use syntax message
     * @param mustBeAPlayer Must be a player message
     * @param invalidPlayer Invalid player message
     * @return true if inventory was open
     */
    protected boolean openInventory(final String[] args, final CommandSender sender, final InventoryHolder gui, final String useSyntax,
                                    final String mustBeAPlayer, final String invalidPlayer){
        if (args.length != 1 && args.length != 2) {
            sender.sendMessage(useSyntax);
            return false;
        }

        if (args.length == 1 && !(sender instanceof Player)) {
            sender.sendMessage(mustBeAPlayer);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 2) {
            player = Bukkit.getPlayer(args[1]);
        }

        if (player == null || !player.isOnline()) {
            sender.sendMessage(invalidPlayer);
            return false;
        }

        player.openInventory(gui.getInventory());

        return true;
    }


    /**
     *
     * @param sender               {@link CommandSender}
     * @param args                 Help arguments
     * @param commands             CommandProvider of {@link AssistantCommand}
     * @param helpHeader           Help header message
     * @param helpMessage          Help message
     * @param helpFooter           Help footer message
     * @param nextPage             Next page message
     * @param previousPage         Previous page message
     * @param helpPageHoverMessage Help Page Hover Message
     */
    protected void sendHelpCommands(final CommandSender sender, final String[] args, final CommandProvider<AssistantCommand> commands, final String helpHeader, final String helpMessage,
                                    final String helpFooter, final String nextPage, final String previousPage, final String helpPageHoverMessage){
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
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandDetails.getLabelProvider() +" help " + (page + 1)));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(helpPageHoverMessage.replace("%page%", "" + (page + 1))).create()));
                }
            } else if (ChatColor.stripColor(component.toLegacyText()).contains(previousPage)) {
                if (page > 1) {
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandDetails.getLabelProvider() +" help " + (page - 1)));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(helpPageHoverMessage.replace("%page%", "" + (page - 1))).create()));
                }
            }
        }
        p.getPlayer().spigot().sendMessage(components);
    }
}
