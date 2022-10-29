package com.qualityplus.dragon.base.handler;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.util.location.LocationUtils;
import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.handler.ProjectileHandler;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ProjectileHandlerImpl implements ProjectileHandler {
    @Override
    public void shoot(ProjectileType projectile, double damage, double amount, DragonGame dragonGame){
        List<Player> players = TheDragon.getApi().getUserService().getUsers().stream()
                .map(EventPlayer::getPlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if(players.isEmpty() || amount < 1) return;
        int perPlayer = (int) (amount / players.size());
        Bukkit.getScheduler().runTask(TheDragon.getApi().getPlugin(), () ->{
            for(Player pl : players){
                if(pl == null) continue;
                for(int i = 0; i<perPlayer; i++)
                    shoot(pl, projectile, damage, i, dragonGame);
            }
        });
    }


    private void shoot(Player player, ProjectileType projectile, double damage, int i, DragonGame dragonGame) {
        Bukkit.getScheduler().runTaskLater(TheDragon.getApi().getPlugin(), () -> {
            if (dragonGame.isActive() && player != null && player.isOnline()) {
                EnderDragon enderDragon = TheDragon.getApi().getDragonService().getActiveEnderDragon();
                if(enderDragon == null) return;

                Location dragonLoc = TheAssistantPlugin.getAPI()
                        .getNms()
                        .getDragonPart(enderDragon, NMS.DragonPart.BODY)
                        .clone()
                        .subtract(0,3,0);

                Location second_location = player.getLocation().clone();

                Vector from = new Vector(dragonLoc.getX(), dragonLoc.getY(), dragonLoc.getZ());
                Vector to = new Vector(second_location.getX(), second_location.getY(), second_location.getZ());
                Vector vector = to.subtract(from).normalize();

                Fireball projectile1;
                if(projectile.equals(ProjectileType.DRAGONBALL))
                    projectile1 = dragonLoc.getWorld().spawn(dragonLoc, DragonFireball.class);
                else
                    projectile1 = dragonLoc.getWorld().spawn(dragonLoc, Fireball.class);
                projectile1.setMetadata("DragonFireball", new FixedMetadataValue(TheDragon.getApi().getPlugin(), damage));
                projectile1.setDirection(vector);
            }
        },20L * i);
    }
}
