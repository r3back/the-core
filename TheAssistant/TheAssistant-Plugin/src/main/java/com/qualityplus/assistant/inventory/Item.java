package com.qualityplus.assistant.inventory;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Item extends OkaeriConfig {
    public XMaterial material;
    public int amount;
    public String displayName;
    public String headData;
    public String headOwner;
    public UUID headOwnerUUID;
    public List<String> lore;
    public Integer slot;
    public boolean enabled;
    public String command;
    public boolean enchanted = false;

    public Item(Item item){
        this.material = item.material;
        this.amount = item.amount;
        this.displayName = item.displayName;
        this.headData = item.headData;
        this.headOwner = item.headOwner;
        this.headOwnerUUID = item.headOwnerUUID;
        this.lore = item.lore;
        this.slot = item.slot;
        this.enabled = item.enabled;
        this.command = item.command;
        this.enchanted = item.enchanted;
    }
}