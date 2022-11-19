package com.qualityplus.minions.base.commands;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.commands.command.AssistantCommand;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.base.gui.tia.TiaGUI;
import com.qualityplus.minions.util.VectorSection;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public final class TiaCommand extends AssistantCommand {
    private @Inject Box box;
    private Location location;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 2) {
            String arg = args[1];

            if(arg.equals("spawn")){
                location = player.getLocation().clone();
            }else{
                Integer integer = Integer.valueOf(arg);

                VectorSection section = AXIS_POSITIONS.get(integer);

                //section.getAll().stream().map(vector -> location.clone().add(vector)).forEach(location1 -> location1.getBlock().setType(XMaterial.REDSTONE_BLOCK.parseMaterial()));
            }

        }else
            player.sendMessage(StringUtils.color(box.files().messages().pluginMessages.useSyntax.replace("%usage%", syntax)));

        return false;
    }

    static {
        ImmutableMap.Builder<Integer, VectorSection> builder = FasterMap.builder(Integer.class, VectorSection.class);

        // - Z = ARRIBA
        // + Z = ABAJO
        // - X = IZQUIERDA
        // + X = DERECHA
        //1
        builder.put(1, new VectorSection(new Vector(0, 0, 1), new Vector(0, 0, 2), new Vector(0, 0, 3)));
        builder.put(2, new VectorSection(new Vector(1, 0, 1), new Vector(1, 0, 2), new Vector(1, 0, 3)));
        builder.put(3, new VectorSection(new Vector(2, 0, 1), new Vector(2, 0, 2), new Vector(2, 0, 3)));
        builder.put(4, new VectorSection(new Vector(3, 0, 1), new Vector(3, 0, 2), new Vector(3, 0, 3)));
        builder.put(5, new VectorSection(new Vector(1, 0, 0), new Vector(2, 0, 0), new Vector(3, 0, 0)));
        builder.put(6, new VectorSection(new Vector(3, 0, -1), new Vector(3, 0, -2), new Vector(3, 0, -3)));
        builder.put(7, new VectorSection(new Vector(2, 0, -1), new Vector(2, 0, -2), new Vector(2, 0, -3)));
        builder.put(8, new VectorSection(new Vector(1, 0, -1), new Vector(1, 0, -2), new Vector(1, 0, -3)));
        builder.put(9, new VectorSection(new Vector(0, 0, -1), new Vector(0, 0, -2), new Vector(0, 0, -3)));
        builder.put(10, new VectorSection(new Vector(-1, 0, -1), new Vector(-1, 0, -2), new Vector(-1, 0, -3)));
        builder.put(11, new VectorSection(new Vector(-2, 0, -1), new Vector(-2, 0, -2), new Vector(-2, 0, -3)));
        builder.put(12, new VectorSection(new Vector(-3, 0, -1), new Vector(-3, 0, -2), new Vector(-3, 0, -3)));
        builder.put(13, new VectorSection(new Vector(-1, 0, 0), new Vector(-2, 0, 0), new Vector(-3, 0, 0)));
        builder.put(14, new VectorSection(new Vector(-3, 0, 1), new Vector(-3, 0, 2), new Vector(-3, 0, 3)));
        builder.put(15, new VectorSection(new Vector(-2, 0, 1), new Vector(-2, 0, 2), new Vector(-2, 0, 3)));
        builder.put(16, new VectorSection(new Vector(-1, 0, 1), new Vector(-1, 0, 2), new Vector(-1, 0, 3)));

        /*
           12 11 10  9  8 7 6
           12 11 10  9  8 7 6
           12 11 10  9  8 7 6
           13 13 13  -  5 5 5
           14 15 16  1  2 3 4
           14 15 16  1  2 3 4
           14 15 16  1  2 3 4
        */
        AXIS_POSITIONS = builder.build();
    }

    private static final Map<Integer, VectorSection> AXIS_POSITIONS;

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return args.length == 2 ? null : Collections.emptyList();
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void register(@Inject Box box){
        TheAssistantPlugin.getAPI().getCommandProvider().registerCommand(this, e -> e.getCommand().setDetails(box.files().commands().tiaCommand));
    }
}