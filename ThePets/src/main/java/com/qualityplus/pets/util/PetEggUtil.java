package com.qualityplus.pets.util;

import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.egg.PetEgg;
import com.qualityplus.pets.base.pet.egg.PetEggEntity;
import com.qualityplus.pets.base.pet.entity.PetEntityOptions;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class PetEggUtil {
    private final String PET_ID_KEY = "PET_ID_KEY";
    private final String PET_UNIQUE_ID_KEY = "PET_UUID_KEY";

    public Optional<PetEggEntity> fromItem(ItemStack itemStack){
        if(ItemStackUtils.isNull(itemStack)) return Optional.empty();

        NBTItem nbtItem = new NBTItem(itemStack);

        if(!nbtItem.hasKey(PET_ID_KEY) || !nbtItem.hasKey(PET_UNIQUE_ID_KEY)) return Optional.empty();

        Pet pet = Pets.getByID(nbtItem.getString(PET_ID_KEY));

        if(pet == null) return Optional.empty();

        UUID uuid = UUID.fromString(nbtItem.getString(PET_UNIQUE_ID_KEY));

        return Optional.of(PetEggEntity.builder()
                .uuid(uuid)
                .pet(pet)
                .build());
    }

    public Optional<ItemStack> fromEgg(PetEggEntity petEggEntity){
        PetEgg petEgg = petEggEntity.getPet().getPetEgg();

        ItemStack itemStack = ItemBuilder.of()
                .headData(petEgg.getTexture())
                .displayName(petEgg.getDisplayName())
                .lore(petEgg.getLore())
                .material(petEgg.getMaterial())
                .amount(1)
                .buildStack();

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(PET_ID_KEY, petEgg.getPetId());
        nbtItem.setString(PET_UNIQUE_ID_KEY, Optional.ofNullable(petEggEntity.getUuid().toString()).orElse(UUID.randomUUID().toString()));

        return Optional.ofNullable(nbtItem.getItem());
    }

    public Optional<ItemStack> fromEgg(PetEgg petEgg){

        ItemStack itemStack = ItemBuilder.of()
                .headData(petEgg.getTexture())
                .displayName(petEgg.getDisplayName())
                .lore(petEgg.getLore())
                .material(petEgg.getMaterial())
                .amount(1)
                .buildStack();

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(PET_ID_KEY, petEgg.getPetId());
        nbtItem.setString(PET_UNIQUE_ID_KEY, UUID.randomUUID().toString());

        return Optional.ofNullable(nbtItem.getItem());
    }

}
