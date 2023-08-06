package com.qualityplus.skills.base.commands.provider;

import com.qualityplus.assistant.api.commands.LabelProvider;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

/**
 * Utility class for commands skills
 */
@Component
public final class SkillsCommandProvider {
    @Delayed(time = MinecraftTimeEquivalent.SECOND / 20, async = true)
    private void configureProvider(@Inject final Box box) {
        LabelProvider.builder()
                .id("theskills")
                .label("theskills")
                .plugin(box.plugin())
                .useHelpMessage(box.files().messages().getPluginMessages().getUseHelp())
                .unknownCommandMessage(box.files().messages().getPluginMessages().getUnknownCommand())
                .onlyForPlayersMessage(box.files().messages().getPluginMessages().getMustBeAPlayer())
                .noPermissionMessage(box.files().messages().getPluginMessages().getNoPermission())
                .build()
                .register();
    }
}
