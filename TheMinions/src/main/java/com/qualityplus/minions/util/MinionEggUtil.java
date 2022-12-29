package com.qualityplus.minions.util;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.faster.FasterStack;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.event.MinionCreateEvent;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.egg.MinionEgg;
import com.qualityplus.minions.base.minions.Minions;
import com.qualityplus.minions.base.minions.minion.skin.MinionSkin;
import com.qualityplus.minions.persistance.data.MinionData;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class MinionEggUtil {
    private final String PET_ID_KEY = "PET_ID_KEY";
    private final String PET_UNIQUE_ID_KEY = "PET_UUID_KEY";

    public Optional<MinionData> dataFromEgg(ItemStack itemStack){

        if(BukkitItemUtil.isNull(itemStack)) return Optional.empty();

        NBTItem nbtItem = new NBTItem(itemStack);

        if(!nbtItem.hasKey(PET_ID_KEY) || !nbtItem.hasKey(PET_UNIQUE_ID_KEY)) return Optional.empty();

        Minion pet = Minions.getByID(nbtItem.getString(PET_ID_KEY));

        if(pet == null) return Optional.empty();

        UUID uuid = UUID.fromString(nbtItem.getString(PET_UNIQUE_ID_KEY));

        return TheMinions.getApi().getMinionsService().getData(uuid);
    }

    public Optional<ItemStack> createNewEgg(Player player, Item item, Minion pet){

        UUID petUuid = UUID.randomUUID();

        MinionData petData = new MinionData(petUuid, player.getUniqueId(), 1, pet.getId(), 0, new HashMap<>(), null, null, null, null, null, null);

        MinionCreateEvent petCreateEvent = new MinionCreateEvent(petData);

        Bukkit.getServer().getPluginManager().callEvent(petCreateEvent);

        return getItemStack(item, pet, petData);

    }

    public Optional<ItemStack> createFromExistent(Item item, UUID petUuid){
        Optional<MinionData> petData = TheMinions.getApi().getMinionsService().getData(petUuid);

        if(!petData.isPresent()) return Optional.empty();

        Optional<Minion> pet = Optional.ofNullable(Minions.getByID(petData.get().getMinionId()));

        if(!pet.isPresent()) return Optional.empty();

        return getItemStack(item, pet.get(), petUuid);
    }


    private Optional<ItemStack> getItemStack(Item item, Minion pet, MinionData petData){
        return getItemStack(item, pet, petData.getUuid());
    }

    private Optional<ItemStack> getItemStack(Item item, Minion minion, UUID petUuid){
        int level = TheMinions.getApi().getMinionsService()
                .getData(petUuid)
                .map(MinionData::getLevel)
                .orElse(1);

        Optional<MinionSkin> minionSkin = minion.getSkin(level);

        List<IPlaceholder> placeholderList = MinionPlaceholderUtil.getMinionPlaceholders(minion)
                .with(MinionPlaceholderUtil.getMinionPlaceholders(petUuid))
                .get();

        Item item1 = ItemBuilder.of(XMaterial.PLAYER_HEAD, 1)
                .displayName(StringUtils.processMulti(item.displayName, placeholderList))
                .lore(StringUtils.processMulti(item.lore, placeholderList))
                .amount(1)
                .build();

        ItemStack eggIcon = minionSkin
                .map(MinionSkin::getHelmet)
                .orElse(FasterStack.fast(XMaterial.PLAYER_HEAD));

        ItemStack itemStack = ItemStackUtils.makeItem(item1, eggIcon.clone());

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(PET_ID_KEY, minion.getId());
        nbtItem.setString(PET_UNIQUE_ID_KEY, petUuid.toString());

        return Optional.ofNullable(nbtItem.getItem());
    }

}
