package com.qualityplus.assistant.util.itemstack;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.gui.LoreWrapper;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import org.apache.commons.lang.WordUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;
import java.util.stream.Collectors;

public final class ItemStackUtils {
    private static final LoreWrapper LORE_WRAPPER = new LoreWrapper(60, "&7");

    private ItemStackUtils(){
    }


    public static ItemStack makeItem(ItemStack item, int amount, String name, List<String> lore) {
        if (item == null)
            return null;
        item.setAmount(amount);
        ItemMeta m = item.getItemMeta();
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        if(lore != null && lore.size() > 0)
            m.setLore(StringUtils.color(lore));
        m.setDisplayName(StringUtils.color(name == null ? " " : name));
        item.setItemMeta(m);
        return item;
    }

    public static ItemStack makeItem(XMaterial material, int amount, String name, List<String> lore) {
        return makeItem(material.parseItem(), amount, name, lore);
    }

    public static ItemStack makeItem(Item item) {
        return makeItem(item, LORE_WRAPPER);
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders) {
        return makeItem(item, placeholders, LORE_WRAPPER);
    }

    public static ItemStack makeItem(Item item, ItemStack itemStack) {
        return makeItem(item, Collections.emptyList(), LORE_WRAPPER, itemStack);
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, ItemStack itemStack) {
        return makeItem(item, placeholders, LORE_WRAPPER, itemStack);
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, ItemStack itemStack, boolean useItemStackAmount) {
        return makeItem(item, placeholders, LORE_WRAPPER, itemStack, useItemStackAmount);
    }

    public static ItemStack makeItem(Item item, LoreWrapper lineWrapper) {
        try {
            ItemStack itemstack = makeItem(item.material, item.amount, item.displayName, item.lore);

            return getFinalItem(item, itemstack, Collections.emptyList(), lineWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return makeItem(XMaterial.STONE, item.amount, item.displayName, item.lore);
        }
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, LoreWrapper lineWrapper) {
        try {
            ItemStack itemstack = makeItem(item.material, item.amount, StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));

            return getFinalItem(item, itemstack, placeholders, lineWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return makeItem(XMaterial.STONE, item.amount, StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));
        }
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, LoreWrapper lineWrapper, ItemStack itemStack) {
        try {
            ItemStack itemstack = makeItem(itemStack.clone(), itemStack.getAmount(), StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));

            return getFinalItem(item, itemstack, placeholders, lineWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return makeItem(XMaterial.STONE, item.amount, StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));
        }
    }

    public static ItemStack makeItem(Item item, List<IPlaceholder> placeholders, LoreWrapper lineWrapper, ItemStack itemStack, boolean useItemStackAmount) {
        try {
            int amount = useItemStackAmount ? itemStack.getAmount() : item.amount;

            ItemStack itemstack = makeItem(itemStack.clone(), amount, StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));

            return getFinalItem(item, itemstack, placeholders, lineWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return makeItem(XMaterial.STONE, item.amount, StringUtils.processMulti(item.displayName, placeholders), StringUtils.processMulti(item.lore, placeholders));
        }
    }

    public static ItemStack getFinalItem(Item item, ItemStack itemstack, List<IPlaceholder> placeholders){
        return getFinalItem(item, itemstack, placeholders, LORE_WRAPPER);
    }



    public static ItemStack getFinalItem(Item item, ItemStack itemstack, List<IPlaceholder> placeholders, LoreWrapper lineWrapper){

        parseWrappedLore(itemstack, lineWrapper);

        parseEnchantment(itemstack, item);

        if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
            SkullMeta m = (SkullMeta) itemstack.getItemMeta();
            m.setOwner(StringUtils.processMulti(item.headOwner, placeholders));
            itemstack.setItemMeta(m);
        }

        if (item.material == XMaterial.PLAYER_HEAD && item.headData != null)
            return parseTexture(itemstack, item);

        return BukkitItemUtil.addCustomModelData(itemstack, item.customModelData);

    }

    private static ItemStack parseTexture(ItemStack itemStack, Item item){
        try {
            NBTItem nbtItem = new NBTItem(itemStack);
            NBTCompound skull = nbtItem.addCompound("SkullOwner");
            skull.setString("Name", "tr7zw");
            skull.setString("Id", UUID.randomUUID().toString());
            NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
            texture.setString("Value", item.headData);
            return nbtItem.getItem();
        }catch (Exception e){
            e.printStackTrace();
            return itemStack;
        }

    }

    private static void parseEnchantment(ItemStack itemStack, Item item){
        if(!item.enchanted) return;

        ItemMeta meta = itemStack.getItemMeta();

        meta.addEnchant(Enchantment.KNOCKBACK, 1, false);

        itemStack.setItemMeta(meta);

    }

    public static void parseWrappedLore(ItemStack itemStack, LoreWrapper lineWrapper){
        List<String> lore = BukkitItemUtil.getItemLore(itemStack);

        List<String> wrappedLore = new ArrayList<>();

        for(String line : lore){
            String indent = StringUtils.repeat(" ", line.length() - line.trim().length());

            List<String> wrapped = Arrays.stream(WordUtils.wrap(
                    line.trim(),
                    lineWrapper.getWrapLength(),
                    "\n" + lineWrapper.wrapStart, false
            ).split("\n")).collect(Collectors.toList());


            wrapped.replaceAll(x -> indent + x);

            wrapped.forEach(loreLine -> wrappedLore.add(StringUtils.color(loreLine)));
        }

        BukkitItemUtil.setLore(itemStack, wrappedLore);
    }




}
