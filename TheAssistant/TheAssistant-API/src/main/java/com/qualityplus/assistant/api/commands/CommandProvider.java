package com.qualityplus.assistant.api.commands;

import com.qualityplus.assistant.api.commands.setup.handler.CommandSetupHandler;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;

import java.util.List;

public interface CommandProvider<T> extends TabExecutor, TabCompleter {
    void reloadCommands();

    void registerCommands();

    void registerCommand(T command, CommandSetupHandler<T> commandSetupHandler);

    void unregisterCommand(T command);

    List<T> getCommands();
}