package com.qualityplus.collections.gui.collection;

import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.collections.api.box.Box;
import com.qualityplus.collections.base.collection.category.CollectionCategory;
import com.qualityplus.collections.gui.CollectionsGUI;
import com.qualityplus.collections.gui.category.CategoryGUI;
import com.qualityplus.collections.util.CollectionsItemStackUtil;
import com.qualityplus.collections.util.CollectionsPlaceholderUtil;
import com.qualityplus.collections.base.collection.Collection;
import com.qualityplus.collections.persistance.data.UserData;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CollectionGUI extends CollectionsGUI {
    private final CollectionGUIConfig config;
    private final Collection collection;

    public CollectionGUI(Box box, Player player, Collection collection) {
        super(box.files().inventories().collectionGUIConfig.getSize(),
              StringUtils.processMulti(box.files().inventories().collectionGUIConfig.getTitle(), Collections.singletonList(new Placeholder("collection_displayname", collection.getDisplayName()))), box);

        this.config = box.files().inventories().collectionGUIConfig;
        this.uuid = player.getUniqueId();
        this.collection = collection;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        setItem(config.getCloseGUI());

        UserData data = box.service().getCollectionsData(uuid).orElse(new UserData());

        int level = data.getCollections().getLevel(collection.getId());

        int maxPerPage = config.getLevelSlots().size();

        int count = (maxPerPage * page) - maxPerPage;

        inventory.setItem(config.getCategoryItem().slot, CollectionsItemStackUtil.makeItem(
                config.getCategoryItem(),
                CollectionsPlaceholderUtil.getCollectionsPlaceholders(data, collection),
                collection.getGuiOptions()));

        for (Integer slot : config.getLevelSlots()) {
            count++;

            if(count > collection.getMaxLevel()) break;

            Item item = count == level + 1 ? config.getInProgressItem() :
                        count > level ? config.getLockedItem() : config.getUnlockedItem();

            inventory.setItem(slot, getItem(item, data, collection, count));

        }

        String category = box.files().categories().getById(collection.getCategory()).map(CollectionCategory::getDisplayName).orElse("");

        setItem(config.getGoBack(), Collections.singletonList(new Placeholder("collection_category_displayname", category)));

        return inventory;
    }

    private ItemStack getItem(Item item, UserData data, Collection collection, int level){
        PlaceholderBuilder builder = CollectionsPlaceholderUtil.getCollectionsPlaceholders(data, collection, level);

        List<String> loreInGui = collection.getCollectionsCacheMessage(level, Collection.MessageType.GUI);


        return ItemStackUtils.makeItem(item, builder
                .with(new Placeholder("collection_info_gui", StringUtils.processMulti(loreInGui, builder.get())))
                .get());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())){
            player.closeInventory();
        }else if(isItem(slot, config.getGoBack())){
            box.files().categories().getById(collection.getCategory()).ifPresent(category -> player.openInventory(new CategoryGUI(box, player, category).getInventory()));
        }
    }
}
