package com.qualityplus.pets.listener;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.egg.PetEggEntity;
import com.qualityplus.pets.base.pet.entity.ArmorStandPet;
import com.qualityplus.pets.persistance.data.SpawnedData;
import com.qualityplus.pets.persistance.data.UserData;
import com.qualityplus.pets.util.PetEggUtil;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@Component
public final class PetEggListener implements Listener {
    private @Inject Box box;

    @EventHandler
    public void onJoin(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack inHand = player.getItemInHand();

        Optional<PetEggEntity> petEgg = PetEggUtil.fromItem(inHand);

        if(!petEgg.isPresent())
            return;

        event.setCancelled(true);

        Optional<PetEntity> petEntity = box.service().getSpawnedPetEntity(player.getUniqueId());

        if(petEntity.isPresent()){
            player.sendMessage(StringUtils.color(box.files().messages().petMessages.cantSpawn));
            return;
        }

        player.setItemInHand(ItemStackUtils.getItemWithout(inHand, 1));

        PetEggEntity egg = petEgg.get();

        box.service()
                .getData(player.getUniqueId())
                .map(UserData::getInventoryData)
                .ifPresent(inventoryData -> inventoryData.addInventoryPet(egg.getUuid(), egg.getPet().getId()));

        /*PetEntity pet = ArmorStandPet.create(player, petEgg.get());

        pet.spawn();*/
    }
}