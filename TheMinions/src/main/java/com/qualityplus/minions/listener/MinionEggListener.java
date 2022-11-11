package com.qualityplus.minions.listener;

import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.minions.api.box.Box;
import com.qualityplus.minions.api.minion.entity.MinionEntity;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.factory.MinionEntityFactory;
import com.qualityplus.minions.base.minion.registry.Minions;
import com.qualityplus.minions.persistance.data.MinionData;
import com.qualityplus.minions.util.MinionEggUtil;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
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

        if(BlockUtils.isNull(block)) return;

        ItemStack inHand = player.getItemInHand();

        Optional<MinionData> data = MinionEggUtil.dataFromEgg(inHand);

        if(!data.isPresent())
            return;

        event.setCancelled(true);

        Minion minion = Minions.getByID(data.get().getMinionId());

        if(minion == null) return;

        player.setItemInHand(ItemStackUtils.getItemWithout(inHand, 1));

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

        //PlaceholderBuilder placeholderList = PetPlaceholderUtil.getPetPlaceholders(data.get().getPetId());

        //String message = StringUtils.processMulti(box.files().messages().petMessages.successfullyAddedToYourPetMenu, placeholderList.get());

        player.sendMessage("Spawned");
    }

    private float toDegree(double angle) {
        return (float) Math.toDegrees(angle);
    }
}