package com.qualityplus.assistant.api.common.rewards.commands;

import com.qualityplus.assistant.api.common.rewards.Reward;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class CommandReward extends OkaeriConfig implements Reward {
    private CommandExecutor commandExecutor;
    private String command;

    @Override
    public void execute(Player player) {
        String cmd = command.replace("%player%", player.getName());

        if(commandExecutor.equals(CommandExecutor.PLAYER))
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
        else
            player.performCommand(cmd);
    }

    public enum CommandExecutor {
        CONSOLE,
        PLAYER
    }
}
