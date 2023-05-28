package com.qualityplus.assistant.base.commands;

import com.qualityplus.assistant.api.commands.CommandProvider;
import com.qualityplus.assistant.api.commands.LabelProvider;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.api.commands.handler.CommandLabelHandler;
import com.qualityplus.assistant.api.commands.setup.handler.CommandSetupHandler;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.list.ListUtils;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public final class TheCoreCommandProvider implements CommandProvider<AssistantCommand> {
    private final Map<String, List<AssistantCommand>> commands = new HashMap<>();
    private @Inject Logger logger;

    @Override
    public void reloadCommands() {
        commands.values().stream().flatMap(Collection::stream).forEach(AssistantCommand::reload);
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND, async = true)
    public void registerCommands() {
        Iterator<List<AssistantCommand>> iterator = commands.values().iterator();

        while(iterator.hasNext()){
            List<AssistantCommand> list = iterator.next();

            if(list == null) break;

            list.sort(Comparator.comparing(command -> command.getAliases().get(0)));
        }
    }

    @Override
    public void registerCommand(AssistantCommand command, CommandSetupHandler<AssistantCommand> commandSetupHandler) {
        command.setup(commandSetupHandler);

        Optional<LabelProvider> provider = getLabelProvider(command.getLabelProvider());

        if(!provider.isPresent()){
            logger.warning(String.format("Error Registering " + command.getLabelProvider() + " %s", command.getAliases().get(0)));
            return;
        }

        registerBukkitCommand(provider.get(), command.getLabelProvider());

        String label = provider.get().getLabel();

        commands.put(label, ListUtils.listWith(commands.getOrDefault(label, new ArrayList<>()), command));
    }

    @Override
    public void unregisterCommand(AssistantCommand command) {
        getLabelProvider(command.getLabelProvider()).ifPresent(labelProvider -> commands.get(labelProvider.getLabel()).remove(command));
    }

    @Override
    public List<AssistantCommand> getCommands(){
        return commands.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command cmd, @NotNull String label, String[] args) {

        Optional<LabelProvider> labelProvider = getLabelProvider(cmd.getName());

        if (args.length == 0) {
            if (commandSender instanceof Player) {
                commandSender.sendMessage(StringUtils.color(labelProvider.map(LabelProvider::getUseHelpMessage).orElse("")));
                return true;
            }
        }

        for (AssistantCommand command : getCommandsByLabel(cmd.getName())) {
            if (!(command.getAliases().contains(args[0]) && command.isEnabled()))
                continue;

            if (command.isOnlyForPlayers() && !(commandSender instanceof Player)) {
                commandSender.sendMessage(StringUtils.color(labelProvider.map(LabelProvider::getOnlyForPlayersMessage).orElse("")));
                return false;
            }

            if (!(commandSender.hasPermission(command.getPermission()) || command.getPermission()
                    .equalsIgnoreCase("") || command.getPermission()
                    .equalsIgnoreCase("thecore."))) {
                commandSender.sendMessage(StringUtils.color(labelProvider.map(LabelProvider::getNoPermissionMessage).orElse("")));
                return false;
            }

            command.execute(commandSender, args);
            return true;
        }
        commandSender.sendMessage(StringUtils.color(labelProvider.map(LabelProvider::getUnknownCommandMessage).orElse("")));

        return false;
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (args.length == 1) {
            List<String> result = new ArrayList<>();
            for (AssistantCommand command : getCommandsByLabel(cmd.getName())) {
                for (String alias : command.getAliases()) {
                    if (alias.toLowerCase().startsWith(args[0].toLowerCase()) && (
                            command.isEnabled() && (commandSender.hasPermission(command.getPermission())
                                    || command.getPermission().equalsIgnoreCase("") || command.getPermission()
                                    .equalsIgnoreCase("thecore.")))) {
                        result.add(alias);
                    }
                }
            }
            return result;
        }

        for (AssistantCommand command : getCommandsByLabel(cmd.getName())) {
            if (command.getAliases().contains(args[0]) && (command.isEnabled() && (
                    commandSender.hasPermission(command.getPermission()) || command.getPermission().equalsIgnoreCase("")
                            || command.getPermission().equalsIgnoreCase("thecore.")))) {
                return command.onTabComplete(commandSender, cmd, label, args);
            }
        }

        return null;
    }

    private List<AssistantCommand> getCommandsByLabel(String label){
        return getLabelProvider(label)
                .map(LabelProvider::getLabel)
                .map(commands::get)
                .orElse(new ArrayList<>());
    }

    private Optional<LabelProvider> getLabelProvider(String label){
        return CommandLabelHandler.values().stream()
                .filter(labelProvider -> labelProvider.getId().equals(label))
                .findFirst();
    }

    private void registerBukkitCommand(LabelProvider provider, String label){
        JavaPlugin java = (JavaPlugin) provider.getPlugin();

        PluginCommand command = java.getCommand(label);

        if(command == null) return;

        if(!(command.getExecutor() instanceof TheCoreCommandProvider))
            command.setExecutor(this);

        if(command.getTabCompleter() == null || !(command.getTabCompleter() instanceof TheCoreCommandProvider))
            command.setTabCompleter(this);
    }
}
