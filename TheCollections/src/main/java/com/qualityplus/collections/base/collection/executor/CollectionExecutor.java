package com.qualityplus.collections.base.collection.executor;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public final class CollectionExecutor extends OkaeriConfig {
    private final ExecutorType executorType;
    private final XMaterial material;
    private final String itemStack;

    @Exclude
    private ItemStack item;

    private CollectionExecutor(ExecutorType executorType, XMaterial material, String itemStack) {
        this.executorType = executorType;
        this.itemStack = itemStack;
        this.material = material;
    }

    public ItemStack getItem(){
        if(item == null) item = ItemStackUtils.deserialize(itemStack);

        return item;
    }

    public static CollectionExecutor ofMaterial(XMaterial material){
        return new CollectionExecutor(ExecutorType.BY_PICK_UP_MATERIAL, material, null);
    }

    public static CollectionExecutor ofItemStack(ItemStack itemStack){
        return new CollectionExecutor(ExecutorType.BY_PICK_UP_ITEM_STACK, null, ItemStackUtils.serialize(itemStack));
    }

}
