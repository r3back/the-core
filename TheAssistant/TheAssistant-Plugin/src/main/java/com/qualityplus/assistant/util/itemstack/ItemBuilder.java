package com.qualityplus.assistant.util.itemstack;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemBuilder {
    private final Item item = new Item();

    public static ItemBuilder of(){
        return new ItemBuilder();
    }

    public static ItemBuilder of(XMaterial material, int amount, String title, List<String> lore){
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder
                .material(material)
                .amount(amount)
                .title(title)
                .lore(lore);
        return itemBuilder;
    }

    public static ItemBuilder of(XMaterial material, int slot, int amount, String title, List<String> lore){
        ItemBuilder itemBuilder = of(material, amount, title, lore);
        itemBuilder.slot(slot);
        itemBuilder.enabled(true);
        return itemBuilder;
    }

    public static ItemBuilder of(XMaterial material, int amount){
        ItemBuilder itemBuilder = of();
        itemBuilder.material(material);
        itemBuilder.amount(amount);
        itemBuilder.enabled(true);
        return itemBuilder;
    }

    public static ItemBuilder of(Item item){
        ItemBuilder itemBuilder = of();
        itemBuilder.material(item.material);
        itemBuilder.amount(item.amount);
        itemBuilder.displayName(item.displayName);
        itemBuilder.headData(item.headData);
        itemBuilder.headOwner(item.headOwner);
        itemBuilder.headOwnerUUID(item.headOwnerUUID);
        itemBuilder.lore(item.lore);
        itemBuilder.slot(Optional.ofNullable(item.slot).orElse(0));
        itemBuilder.enabled(item.enabled);
        itemBuilder.command(item.command);
        itemBuilder.enchanted(item.enchanted);
        return itemBuilder;
    }

    public ItemBuilder material(XMaterial material){
        item.material = material;
        return this;
    }

    public ItemBuilder displayName(String displayName){
        item.displayName = displayName;
        return this;
    }

    public ItemBuilder headOwnerUUID(UUID headOwnerUUID){
        item.headOwnerUUID = headOwnerUUID;
        return this;
    }

    public ItemBuilder slot(int slot){
        item.slot = slot;
        return this;
    }

    public ItemBuilder amount(int amount){
        item.amount = amount;
        return this;
    }

    public ItemBuilder lore(List<String> lore){
        item.lore = lore;
        return this;
    }

    public ItemBuilder lore(String... lore){
        item.lore = Arrays.stream(lore).collect(Collectors.toList());
        return this;
    }

    public ItemBuilder title(String title){
        item.displayName = title;
        return this;
    }

    public ItemBuilder command(String command){
        item.command = command;
        return this;
    }

    public ItemBuilder enabled(boolean enabled){
        item.enabled = enabled;
        return this;
    }

    public ItemBuilder headData(String headData){
        item.headData = headData;
        return this;
    }

    public ItemBuilder headOwner(String headOwner){
        item.headOwner = headOwner;
        return this;
    }

    public ItemBuilder enchanted(boolean enchanted){
        item.enchanted = enchanted;
        return this;
    }

    public Item build(){
        return this.item;
    }

    public ItemStack buildStack(){
        return ItemStackUtils.makeItem(build());
    }
}