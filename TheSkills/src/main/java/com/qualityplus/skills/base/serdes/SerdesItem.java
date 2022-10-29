package com.qualityplus.skills.base.serdes;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.inventory.Item;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.Collections;
import java.util.UUID;

public final class SerdesItem implements ObjectSerializer<Item> {
    @Override
    public boolean supports(@NonNull Class<? super Item> type) {
        return Item.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Item object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("amount", object.amount);
        data.add("material", object.material);
        data.add("displayName", object.displayName);
        data.add("headData", object.headData);
        data.add("headOwner", object.headOwner);
        data.add("headOwnerUUID", object.headOwnerUUID);
        data.add("lore", object.lore);
        data.add("slot", object.slot);
        data.add("enabled", object.enabled);
        data.add("command", object.command);
    }

    @Override
    public Item deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        Item item = new Item();
        item.displayName = data.containsKey("displayName") ? data.get("displayName", String.class) : "";
        item.amount = data.containsKey("amount") ? data.get("amount", Integer.class) : 0;
        item.material = data.containsKey("material") ? data.get("material", XMaterial.class) : XMaterial.STONE;
        item.headData = data.containsKey("headData") ? data.get("headData", String.class) : null;
        item.headOwner = data.containsKey("headOwner") ? data.get("headOwner", String.class) : null;
        item.headOwnerUUID = data.containsKey("headOwnerUUID") ? data.get("headOwnerUUID", UUID.class) : null;
        item.lore = data.containsKey("lore") ? data.getAsList("lore", String.class) : Collections.emptyList();
        item.slot = data.containsKey("slot") ? data.get("slot", Integer.class) : 0;
        item.enabled = data.containsKey("enabled") ? data.get("enabled", Boolean.class) : true;
        item.command = data.containsKey("command") ? data.get("command", String.class) : null;

        return item;
    }
}
