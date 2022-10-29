package com.qualityplus.assistant.api.commands;

import com.qualityplus.assistant.api.commands.handler.CommandLabelHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.Plugin;

@AllArgsConstructor
@Getter
@Setter
@Builder
public final class LabelProvider {
    private final String id;
    private final String label;
    private final Plugin plugin;
    private final String useHelpMessage;
    private final String noPermissionMessage;
    private final String onlyForPlayersMessage;
    private final String unknownCommandMessage;

    public void register(){
        CommandLabelHandler.registerNewLabel(this);
    }
}
