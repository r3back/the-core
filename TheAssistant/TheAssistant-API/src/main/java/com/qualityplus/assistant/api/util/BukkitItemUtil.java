package com.qualityplus.assistant.api.util;

import com.cryptomorin.xseries.XMaterial;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

@UtilityClass
public class BukkitItemUtil {
    public void dropItem(Player player, ItemStack itemStack){
        dropItem(player.getLocation(), itemStack);
    }

    public void dropItem(Location location, ItemStack itemStack){
        if(location == null) return;

        Optional.ofNullable(location.getWorld()).ifPresent(w -> w.dropItem(location, itemStack));
    }

    public ItemStack cloneOrNull(ItemStack itemStack){
        return Optional.ofNullable(itemStack).map(ItemStack::clone).orElse(null);
    }

    public static String serialize(ItemStack itemStack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(itemStack);
            bukkitObjectOutputStream.flush();

            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    public static ItemStack deserialize(String string) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(string));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            return (ItemStack) bukkitObjectInputStream.readObject();
        } catch (Exception exception) {
            return XMaterial.AIR.parseItem();
        }
    }

    public static ItemStack getItemWithout(@Nullable ItemStack itemStack, int amountToRemove) {
        if(itemStack == null || itemStack.getAmount() - amountToRemove <= 0) return null;

        itemStack.setAmount(itemStack.getAmount() - amountToRemove);

        return itemStack;
    }

    public static ItemStack getItemWithAdd(ItemStack itemStack, int amountToAdd) {
        itemStack.setAmount(Math.min(itemStack.getMaxStackSize(), itemStack.getAmount() + amountToAdd));

        return itemStack;
    }

    public static ItemStack getItemWith(ItemStack itemStack, int finalAmount) {
        itemStack.setAmount(Math.min(itemStack.getMaxStackSize(), finalAmount));

        return itemStack;
    }


    public static boolean isNull(ItemStack itemStack){
        return (itemStack == null || itemStack.getType() == Material.AIR);
    }

    public static boolean isNotNull(ItemStack itemStack){
        return !isNull(itemStack);
    }

    public static ItemStack addCustomModelData(ItemStack itemStack, Integer customModelData){
        if(customModelData == null || XMaterial.getVersion() < 13) return itemStack;

        try {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setCustomModelData(customModelData);

            itemStack.setItemMeta(meta);

        }catch (Exception ignored){
        }

        return itemStack;
    }

    public static void setLore(ItemStack itemStack, List<String> lore){
        ItemMeta meta = itemStack.getItemMeta();

        Optional.ofNullable(meta).ifPresent(m -> m.setLore(lore));

        Optional.ofNullable(meta).ifPresent(itemStack::setItemMeta);

    }


    public static String getName(ItemStack itemStack){

        String displayName = Optional.ofNullable(itemStack)
                .map(ItemStack::getItemMeta)
                .map(ItemMeta::getDisplayName)
                .filter(name -> name != null && !name.isEmpty())
                .orElse(null);

        return Optional.ofNullable(displayName)
                .orElse(getMaterialName(itemStack));
    }

    public static String getMaterialName(ItemStack itemStack){
        if(isNull(itemStack)) return "";

        return WordUtils.capitalize(Optional.ofNullable(itemStack)
                .map(ItemStack::getType)
                .map(Material::toString)
                .orElse("")
                .replace("_", " ")
                .toLowerCase());
    }

    public static List<String> getItemLore(ItemStack item){
        if(isNull(item)) return Collections.emptyList();

        return Optional.ofNullable(item)
                .map(ItemStack::getItemMeta)
                .filter(Objects::nonNull)
                .map(ItemMeta::getLore)
                .filter(Objects::nonNull)
                .orElse(new ArrayList<>());
    }

    public ItemStack[] fromList(List<ItemStack> itemStacks){
        ItemStack[] toReturnItems = new ItemStack[itemStacks.size()];
        itemStacks.toArray(toReturnItems);

        return toReturnItems;
    }
}
