package com.qualityplus.assistant.inventory;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.qualityplus.assistant.api.event.SignCompletedEvent;
import com.qualityplus.assistant.api.handler.SignCompleteHandler;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SignGUI {
    private final SignCompleteHandler action;
    private PacketAdapter packetListener;
    private final List<String> lines;
    private LeaveListener listener;
    private final Plugin plugin;
    private final UUID uuid;
    private Sign sign;

    @Builder
    public SignGUI(SignCompleteHandler action, List<String> withLines, UUID uuid, Plugin plugin) {
        this.lines = withLines;
        this.plugin = plugin;
        this.action = action;
        this.uuid = uuid;
    }

    public void open() {
        Player player = Bukkit.getPlayer(uuid);

        if(player == null) return;

        this.listener = new LeaveListener();

        int x_start = player.getLocation().getBlockX();

        int y_start = 255;

        int z_start = player.getLocation().getBlockZ();

        Material material = Material.getMaterial("WALL_SIGN");
        if (material == null)
            material = Material.OAK_WALL_SIGN;
        while (!player.getWorld().getBlockAt(x_start, y_start, z_start).getType().equals(Material.AIR) &&
                !player.getWorld().getBlockAt(x_start, y_start, z_start).getType().equals(material)) {
            y_start--;
            if (y_start == 1)
                return;
        }
        player.getWorld().getBlockAt(x_start, y_start, z_start).setType(material);

        this.sign = (Sign)player.getWorld().getBlockAt(x_start, y_start, z_start).getState();

        int i = 0;
        for(String line : lines){
            this.sign.setLine(i, line);
            i++;
        }

        this.sign.update(false, false);


        PacketContainer openSign = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);

        BlockPosition position = new BlockPosition(x_start, y_start, z_start);

        openSign.getBlockPositionModifier().write(0, position);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, openSign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 3L);

        Bukkit.getPluginManager().registerEvents(this.listener, plugin);
        registerSignUpdateListener();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private class LeaveListener implements Listener {
        @EventHandler
        public void onLeave(PlayerQuitEvent e) {
            if (e.getPlayer().getUniqueId().equals(SignGUI.this.uuid)) {
                ProtocolLibrary.getProtocolManager().removePacketListener(SignGUI.this.packetListener);
                HandlerList.unregisterAll(this);
                SignGUI.this.sign.getBlock().setType(Material.AIR);
            }
        }
    }

    private void registerSignUpdateListener() {
        final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        this.packetListener = new PacketAdapter(plugin, PacketType.Play.Client.UPDATE_SIGN) {
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPlayer().getUniqueId().equals(SignGUI.this.uuid)) {
                    List<String> lines = Stream.of(0,1,2,3).map(line -> getLine(event, line)).collect(Collectors.toList());

                    Bukkit.getScheduler().runTask(plugin, () -> {
                        manager.removePacketListener(this);

                        HandlerList.unregisterAll(SignGUI.this.listener);

                        SignGUI.this.sign.getBlock().setType(Material.AIR);

                        SignGUI.this.action.onSignClose(new SignCompletedEvent(event.getPlayer(), lines));
                    });
                }
            }
        };
        manager.addPacketListener(this.packetListener);
    }

    private String getLine(PacketEvent event, int line){
        return Bukkit.getVersion().contains("1.8") ?
                ((WrappedChatComponent[])event.getPacket().getChatComponentArrays().read(0))[line].getJson().replaceAll("\"", "") :
                ((String[])event.getPacket().getStringArrays().read(0))[line];
    }
}