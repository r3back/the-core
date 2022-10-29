package com.qualityplus.assistant.okaeri.commands;

import eu.okaeri.commands.Commands;
import eu.okaeri.commands.brigadier.CommandsBrigadierPaper;
import eu.okaeri.commands.injector.CommandsInjector;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.bukkit.commands.BukkitCommandsResultHandler;
import eu.okaeri.platform.bukkit.commands.BukkitCommandsTasker;
import eu.okaeri.platform.bukkit.plan.BukkitCommandsSetupTask;
import eu.okaeri.tasker.core.Tasker;

public final class SilentCommandSetupTask extends BukkitCommandsSetupTask {
    private static final boolean BRIGADIER = Boolean.parseBoolean(System.getProperty("okaeri.platform.brigadier", "true"));

    @Override
    public void execute(OkaeriBukkitPlugin platform) {
        Commands commands = SilentCommandsBukkit.make(platform);
        commands.resultHandler(new BukkitCommandsResultHandler());
        commands.registerExtension(new CommandsInjector(platform.getInjector()));
        if (BRIGADIER && this.canUseBrigadierPaper())
            commands.registerExtension(new CommandsBrigadierPaper(platform));

        platform.getInjector().get("tasker", Tasker.class).ifPresent((tasker) -> commands.registerExtension(new BukkitCommandsTasker(tasker)));

        platform.registerInjectable("commands", commands);
    }
}
