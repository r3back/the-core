package com.qualityplus.alchemist.base.commands.provider;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.config.Messages;
import com.qualityplus.assistant.api.commands.LabelProvider;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;

/**
 * Handles command provider registration
 */
@Component
public final class AlchemistCommandProvider {
    /**
     * Register command provider
     *
     * @param box {@link Box}
     */
    @Delayed(time = MinecraftTimeEquivalent.SECOND / 20, async = true)
    private void configureProvider(@Inject final Box box) {
        final Messages.PluginMessages pluginMessages = box.getFiles().messages().getPluginMessages();

        LabelProvider.builder()
                .id("thealchemist")
                .label("thealchemist")
                .plugin(box.getPlugin())
                .useHelpMessage(pluginMessages.getUseHelp())
                .unknownCommandMessage(pluginMessages.getUnknownCommand())
                .onlyForPlayersMessage(pluginMessages.getMustBeAPlayer())
                .noPermissionMessage(pluginMessages.getNoPermission())
                .build()
                .register();
    }
}
