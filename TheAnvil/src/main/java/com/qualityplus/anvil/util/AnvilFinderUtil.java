package com.qualityplus.anvil.util;

import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.enchanting.TheEnchanting;
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment;
import com.qualityplus.enchanting.base.session.EnchantmentSessionImpl;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import com.qualityplus.anvil.api.session.AnvilSession.SessionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.DoubleStream;

@UtilityClass
public class AnvilFinderUtil {
    public int getItemLevel(ItemStack itemStack, Enchantment enchantment){
        if(itemStack == null || enchantment == null) return 0;

        ItemMeta meta = itemStack.getItemMeta();

        return Optional.ofNullable(meta).map(m -> m.getEnchantLevel(enchantment)).orElse(0);
    }

    public SessionResult getAnswer(AnvilSession session){
        boolean toUpgradeIsNull = ItemStackUtils.isNull(session.getItemToUpgrade());
        boolean toSacrificeIsNull = ItemStackUtils.isNull(session.getItemToSacrifice());

        if(toSacrificeIsNull && toUpgradeIsNull) return SessionResult.NOTHING_SET;

        if(!toUpgradeIsNull && toSacrificeIsNull) return SessionResult.ONLY_ITEM_TO_UPGRADE;

        if(toUpgradeIsNull) return SessionResult.ONLY_ITEM_TO_SACRIFICE;

        Material toUpgrade = session.getItemToUpgrade().getType();
        Material toSacrifice = session.getItemToSacrifice().getType();

        if(!toUpgrade.equals(toSacrifice) && !(session.getItemToSacrifice().getItemMeta() instanceof EnchantmentStorageMeta)) return SessionResult.ERROR_CANNOT_COMBINE_THOSE_ITEMS;

        if(TheEnchanting.getApi().getEnchantments(session.getItemToSacrifice())
                .keySet()
                .stream()
                .anyMatch(enchantment -> TheEnchanting.getApi().getIncompatibility(session.getItemToUpgrade(), enchantment).isPresent()))
            return SessionResult.ERROR_CONFLICT_ENCHANTMENTS;

        return SessionResult.BOTH_ITEMS_SET;

    }

    public double getMoneyCost(AnvilSession session){
        return getNewEnchantments(session)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().getRequiredMoneyToEnchant(entry.getValue()))
                .reduce(0D, Double::sum);
    }

    public double getLevelsCost(AnvilSession session){
        return getNewEnchantments(session)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().getRequiredLevelToEnchant(entry.getValue()))
                .reduce(0D, Double::sum);
    }

    public boolean dontHavePermission(AnvilSession session, Player player){
        return getNewEnchantments(session)
                .entrySet()
                .stream()
                .anyMatch(entry -> !entry.getKey().canEnchant(player, entry.getValue()));
    }

    public ItemStack getFinalItem(AnvilSession session){
        ItemStack newItem = session.getItemToUpgrade().clone();

        for(Map.Entry<ICoreEnchantment, Integer> entry : getNewEnchantments(session).entrySet())
            newItem = TheEnchanting.getApi().addEnchantment(newItem, new EnchantmentSessionImpl(entry.getKey(), entry.getValue()));

        return newItem;
    }

    public Map<ICoreEnchantment, Integer> getNewEnchantments(AnvilSession session){
        Map<ICoreEnchantment, Integer> toUpgradeEnchants = TheEnchanting.getApi().getEnchantments(session.getItemToUpgrade());
        Map<ICoreEnchantment, Integer> toSacrificeEnchants = TheEnchanting.getApi().getEnchantments(session.getItemToSacrifice());

        Map<ICoreEnchantment, Integer> newEnchants = new HashMap<>();

        for(ICoreEnchantment enchantment : toSacrificeEnchants.keySet()){
            if(!toUpgradeEnchants.containsKey(enchantment)){
                newEnchants.put(enchantment, toSacrificeEnchants.get(enchantment));
            }else{
                int sacrificeLevel = toSacrificeEnchants.get(enchantment);
                int upgradeLevel = toUpgradeEnchants.get(enchantment);

                if(upgradeLevel + sacrificeLevel > enchantment.getMaxLevel()) continue;

                newEnchants.put(enchantment, upgradeLevel + sacrificeLevel);
            }
        }

        return newEnchants;
    }
}
