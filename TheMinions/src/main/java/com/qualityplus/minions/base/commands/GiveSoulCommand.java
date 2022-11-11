package com.qualityplus.minions.base.commands;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.registry.Minions;
import com.qualityplus.minions.util.MinionEggUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public final class GiveSoulCommand extends AssistantCommand {
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

            Optional<ItemStack> petEgg = MinionEggUtil.createNewEgg(box.files().config().petEggItem, pet);

            if(!petEgg.isPresent()){
                sender.sendMessage(StringUtils.color(box.files().messages().minionMessages.invalidEgg));
                return false;
            }

            toGive.getInventory().addItem(petEgg.get());

        }else{
            sender.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : args.length == 3 ? Minions.values().stream().map(Minion::getId).collect(Collectors.toList()) : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().giveSoulCommand));
    }

    private ItemStack getItem(){
        NBTItem nbtItem = new NBTItem(ItemStackUtils.makeItem(box.files().config().soulItem));
        nbtItem.setBoolean("soulItem", true);
        return nbtItem.getItem();
    }
}