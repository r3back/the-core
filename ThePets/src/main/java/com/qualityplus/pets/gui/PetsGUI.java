package com.qualityplus.pets.gui;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.persistance.data.inside.InventoryPet;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class PetsGUI extends GUI {
    protected final Box box;
    protected UUID uuid;

    public PetsGUI(int size, String title, Box box) {
        super(size, title);
        this.box = box;
    }

    public PetsGUI(SimpleGUI simpleGUI, Box box) {
        super(simpleGUI);
        this.box = box;
    }

    public void setItem(Item item) {
        super.setItem(item);
    }

    public void setItem(Item item, List<IPlaceholder> placeholderList) {
        super.setItem(item, placeholderList);
    }

    protected ItemStack getIcon(InventoryPet inventoryPet) {
        Optional<Pet> selectedPet = Optional.ofNullable(Pets.getByID(inventoryPet.getPetId()));

        XMaterial material = selectedPet.map(pet -> pet.getPetEgg().getMaterial()).orElse(XMaterial.PLAYER_HEAD);

        String headData = selectedPet.map(pet -> pet.getPetEgg().getTexture()).orElse(null);

        return ItemBuilder.of(material, 1, "", Collections.emptyList())
                .headData(headData).buildStack();

    }

}
