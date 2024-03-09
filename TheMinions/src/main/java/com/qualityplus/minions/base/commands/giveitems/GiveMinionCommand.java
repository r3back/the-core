package com.qualityplus.minions.base.commands.giveitems;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.Minions;
import com.qualityplus.minions.util.MinionEggUtil;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public final class GiveMinionCommand extends AssistantCommand {
    private @Inject Box box;

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(args.length == 3){
            Player toGive = Bukkit.getPlayer(args[1]);

            Minion pet = Minions.getByID(args[2]);

            if(toGive == null){
                sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.invalidPlayer));
                return false;
            }

            if(pet == null){
                sender.sendMessage(StringUtils.color(box.files().messages().minionMessages.invalidMinion));
                return false;
            }

            Optional<ItemStack> petEgg = MinionEggUtil.createNewEgg(toGive, box.files().config().minionEggItem, pet);

            if(!petEgg.isPresent()){
                sender.sendMessage(StringUtils.color(box.files().messages().minionMessages.invalidEgg));
                return false;
            }

            toGive.getInventory().addItem(petEgg.get());

        }else{
            String message = box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax);

            sender.sendMessage(StringUtils.color(message));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : args.length == 3 ? Minions.values().stream().map(Minion::getId).collect(Collectors.toList()) : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().giveMinionCommand));
    }

}