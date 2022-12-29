package com.qualityplus.minions.listener;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.entity.factory.MinionEntityFactory;
import com.qualityplus.minions.base.minions.Minions;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.util.MinionEggUtil;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Optional;

@Component
public final class MinionEggListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onJoin(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Block block = event.getClickedBlock();

        ItemStack inHand = player.getItemInHand();

        Optional<MinionData> data = MinionEggUtil.dataFromEgg(inHand);

        if(!data.isPresent())
            return;

        event.setCancelled(true);

        if(BlockUtils.isNull(block)) return;

        Minion minion = Minions.getByID(data.get().getMinionId());

        if(minion == null) return;

        player.setItemInHand(BukkitItemUtil.getItemWithout(inHand, 1));

        MinionEntity petEntity = MinionEntityFactory.create(data.get().getUuid(), player.getUniqueId(), minion);

        Location location = block.getLocation().clone()
                .add(0.5,1,0.5);

        Vector direction = location.toVector().subtract(player.getEyeLocation().toVector()).normalize();
        double x = direction.getX();
        double y = direction.getY();
        double z = direction.getZ();

        Location changed = location.clone();
        changed.setYaw(180 - toDegree(Math.atan2(x, z)));
        changed.setPitch(90 - toDegree(Math.acos(y)));

        petEntity.spawn(changed);
    }

    private float toDegree(double angle) {
        return (float) Math.toDegrees(angle);
    }
}