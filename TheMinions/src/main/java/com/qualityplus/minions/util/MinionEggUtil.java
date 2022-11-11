package com.qualityplus.minions.util;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.event.MinionCreateEvent;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minion.registry.Minions;
import com.qualityplus.minions.persistance.data.MinionData;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class MinionEggUtil {
    private final String PET_ID_KEY = "PET_ID_KEY";
    private final String PET_UNIQUE_ID_KEY = "PET_UUID_KEY";

    public Optional<MinionData> dataFromEgg(ItemStack itemStack){
        if(ItemStackUtils.isNull(itemStack)) return Optional.empty();

        NBTItem nbtItem = new NBTItem(itemStack);

        if(!nbtItem.hasKey(PET_ID_KEY) || !nbtItem.hasKey(PET_UNIQUE_ID_KEY)) return Optional.empty();

        Minion pet = Minions.getByID(nbtItem.getString(PET_ID_KEY));

        if(pet == null) return Optional.empty();

        UUID uuid = UUID.fromString(nbtItem.getString(PET_UNIQUE_ID_KEY));

        return TheMinions.getApi().getMinionsService().getData(uuid);
    }

    public Optional<ItemStack> createNewEgg(Item item, Minion pet){

        UUID petUuid = UUID.randomUUID();

        MinionData petData = new MinionData(petUuid, 0, 0D, pet.getId());

        MinionCreateEvent petCreateEvent = new MinionCreateEvent(petData);

        Bukkit.getServer().getPluginManager().callEvent(petCreateEvent);

        return getItemStack(item, pet, petData);

    }

    private Optional<ItemStack> getItemStack(Item item, Minion pet, MinionData petData){
        MinionEgg petEgg = pet.getMinionEgg();

        List<IPlaceholder> placeholderList = /*PetPlaceholderUtil.getPetPlaceholders(pet)
                .with(PetPlaceholderUtil.getPetPlaceholders(petData))
                .get();*/Collections.emptyList();

        ItemStack itemStack = ItemBuilder.of()
                .headData(petEgg.getTexture())
                .displayName(StringUtils.processMulti(item.displayName, placeholderList))
                .lore(StringUtils.processMulti(item.lore, placeholderList))
                .material(petEgg.getMaterial())
                .amount(1)
                .buildStack();

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(PET_ID_KEY, petEgg.getPetId());
        nbtItem.setString(PET_UNIQUE_ID_KEY, petData.getUuid().toString());

        return Optional.ofNullable(nbtItem.getItem());
    }

    private Optional<ItemStack> getItemStack(Item item, Minion pet, UUID petUuid){
        MinionEgg petEgg = pet.getMinionEgg();

        List<IPlaceholder> placeholderList = /*PetPlaceholderUtil.getPetPlaceholders(pet)
                .with(PetPlaceholderUtil.getPetPlaceholders(petUuid))
                .get()*/Collections.emptyList();

        ItemStack itemStack = ItemBuilder.of()
                .headData(petEgg.getTexture())
                .displayName(StringUtils.processMulti(item.displayName, placeholderList))
                .lore(StringUtils.processMulti(item.lore, placeholderList))
                .material(petEgg.getMaterial())
                .amount(1)
                .buildStack();

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(PET_ID_KEY, petEgg.getPetId());
        nbtItem.setString(PET_UNIQUE_ID_KEY, petUuid.toString());

        return Optional.ofNullable(nbtItem.getItem());
    }

    public Optional<ItemStack> createFromExistent(Item item, UUID petUuid){
        Optional<MinionData> petData = TheMinions.getApi().getMinionsService().getData(petUuid);

        if(!petData.isPresent()) return Optional.empty();

        Optional<Minion> pet = Optional.ofNullable(Minions.getByID(petData.get().getMinionId()));

        if(!pet.isPresent()) return Optional.empty();

        return getItemStack(item, pet.get(), petUuid);
    }





}
