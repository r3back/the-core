package com.qualityplus.assistant.okaeri;

import com.qualityplus.assistant.okaeri.commands.SilentCommandSetupTask;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.persistence.document.ConfigurerProvider;
import eu.okaeri.placeholders.bukkit.BukkitPlaceholders;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.bukkit.component.BukkitComponentCreator;
import eu.okaeri.platform.bukkit.component.BukkitCreatorRegistry;
import eu.okaeri.platform.bukkit.i18n.PlayerLocaleProvider;
import eu.okaeri.platform.bukkit.plan.BukkitCommandsI18nManifestTask;
import eu.okaeri.platform.bukkit.plan.BukkitExternalResourceProviderSetupTask;
import eu.okaeri.platform.bukkit.scheduler.PlatformScheduler;
import eu.okaeri.platform.core.placeholder.SimplePlaceholdersFactory;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.ExecutionPlan;
import eu.okaeri.platform.core.plan.ExecutionTask;
import eu.okaeri.platform.core.plan.Planned;
import eu.okaeri.platform.core.plan.task.*;
import eu.okaeri.platform.minecraft.task.CommandsI18nSetupTask;
import eu.okaeri.tasker.bukkit.BukkitTasker;
import lombok.NonNull;

public abstract class OkaeriSilentPlugin extends OkaeriBukkitPlugin {
    private static final boolean DEBUG = false;

    @Override
    public void log(@NonNull String message) {
        if(DEBUG) this.getLogger().info(message);
    }

    @Override
    public void plan(@NonNull ExecutionPlan plan) {
        if (plan == null) {
            throw new NullPointerException("plan is marked non-null but is null");
        } else {
            plan.add(ExecutionPhase.PRE_SETUP, new InjectorSetupTask());
            plan.add(ExecutionPhase.PRE_SETUP, (ExecutionTask<OkaeriBukkitPlugin>) platform -> {
                platform.registerInjectable("server", platform.getServer());
                platform.registerInjectable("dataFolder", platform.getDataFolder());
                platform.registerInjectable("jarFile", platform.getFile());
                platform.registerInjectable("logger", platform.getLogger());
                platform.registerInjectable("plugin", platform);
                platform.registerInjectable("placeholders", BukkitPlaceholders.create(true));
                platform.registerInjectable("scheduler", new PlatformScheduler(platform, platform.getServer().getScheduler()));
                platform.registerInjectable("tasker", BukkitTasker.newPool(platform));
                platform.registerInjectable("pluginManager", platform.getServer().getPluginManager());
                platform.registerInjectable("defaultConfigurerProvider", (ConfigurerProvider) YamlBukkitConfigurer::new);
                platform.registerInjectable("defaultConfigurerSerdes", new Class[]{SerdesCommons.class, SerdesBukkit.class});
                platform.registerInjectable("defaultPlaceholdersFactory", new SimplePlaceholdersFactory());
                platform.registerInjectable("i18nLocaleProvider", new PlayerLocaleProvider());
            });
            plan.add(ExecutionPhase.SETUP, new SilentCommandSetupTask());
            plan.add(ExecutionPhase.SETUP, new CreatorSetupTask(BukkitComponentCreator.class, BukkitCreatorRegistry.class));
            plan.add(ExecutionPhase.POST_SETUP, new BukkitExternalResourceProviderSetupTask());
            plan.add(ExecutionPhase.POST_SETUP, new BeanManifestCreateTask());
            plan.add(ExecutionPhase.POST_SETUP, new BukkitCommandsI18nManifestTask());
            plan.add(ExecutionPhase.POST_SETUP, new BeanManifestExecuteTask());
            plan.add(ExecutionPhase.POST_SETUP, new CommandsI18nSetupTask());
            plan.add(ExecutionPhase.SHUTDOWN, new PersistenceShutdownTask());
        }
    }

    @Planned(ExecutionPhase.STARTUP)
    public void onStartup() {
        this.getLogger().info("Enabled! Version " + this.getDescription().getVersion());
    }

    @Planned(ExecutionPhase.SHUTDOWN)
    public void onShutdown() {
        this.getLogger().info("Disabled! Version " + this.getDescription().getVersion());
    }
}
