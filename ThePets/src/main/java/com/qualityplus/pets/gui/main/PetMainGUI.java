package com.qualityplus.pets.gui.main;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.actionbar.ActionBarUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.api.pet.Pets;
import com.qualityplus.pets.api.pet.entity.PetEntity;
import com.qualityplus.pets.base.pet.Pet;
import com.qualityplus.pets.base.pet.tracker.PetEntityTracker;
import com.qualityplus.pets.gui.PetsGUI;
import com.qualityplus.pets.persistance.data.InventoryPet;
import com.qualityplus.pets.persistance.data.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class PetMainGUI extends PetsGUI {
    private final Map<Integer, InventoryPet> petsMap = new HashMap<>();
    private final PetsGUIConfig config;
    private final String name;

    public PetMainGUI(Box box, Player player) {
        super(box.files().inventories().mainGUIConfig, box);

        this.config = box.files().inventories().mainGUIConfig;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }


    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);
        setItem(config.getCloseGUI());

        Optional<UserData> data = box.service().getData(uuid);

        boolean petsAreHidden = data.map(userData -> userData.getUserSettings().isPetsAreHidden()).orElse(false);
        boolean isConvertItemMode = data.map(userData -> userData.getUserSettings().isConvertPetToItemMode()).orElse(false);

        List<InventoryPet> pets = data.map(userData -> userData.getInventoryData().getPets()).orElse(Collections.emptyList());

        int count = 0;

        String selectedPet = data.map(UserData::getPetName).orElse(null);

        String petName = selectedPet == null ? box.files().messages().petMessages.nonSelectedPetInGUI : box.files().messages().petMessages.selectedPetInGUI.replace("%pet_egg_item_displayname%", selectedPet);

        /**
         * Change exception for list size checker
         */
        for(Integer slot : config.getSlots()){
            try {
                InventoryPet pet = pets.get(count);

                inventory.setItem(slot, ItemStackUtils.makeItem(config.getPetItem(), getPlaceholders(pet)));

                petsMap.put(slot, pet);

                count++;
            }catch (Exception ignored){
                break;
            }
        }

        List<IPlaceholder> selectedPetPl = new Placeholder("pet_selected_pet", petName).alone();

        Item petsAreHiddenItem = petsAreHidden ? config.getHidePetsEnabled() : config.getHidePetsDisabled();

        setItem(petsAreHiddenItem, selectedPetPl);

        Item convertItemModeItem = isConvertItemMode ? config.getConvertPetToItemEnabled() : config.getConvertPetToItemDisabled();

        setItem(convertItemModeItem, selectedPetPl);

        setItem(config.getPetMenuInfoItem(), selectedPetPl);

        return inventory;
    }

    private List<IPlaceholder> getPlaceholders(InventoryPet inventoryPet){
        Optional<Pet> pet = Optional.ofNullable(Pets.getByID(inventoryPet.getPetId()));
        Optional<PetEntity> entity = PetEntityTracker.getByID(inventoryPet.getUuid());

        double maxXp = entity.map(PetEntity::getMaxXp).orElse(1D);
        double xp = entity.map(PetEntity::getXp).orElse(1D);
        double percentage = ActionBarUtils.getPercentageFromTotal(xp, maxXp);


        return PlaceholderBuilder.create(
                        new Placeholder("pet_egg_egg_displayname", pet.map(p -> p.getPetEgg().getEggDisplayName()).orElse("")),
                        new Placeholder("pet_egg_displayname", pet.map(p -> p.getPetEgg().getDisplayName()).orElse("")),

                        new Placeholder("pet_egg_description", pet.map(p -> p.getPetEgg().getLore()).orElse(Collections.emptyList())),
                        new Placeholder("pet_level_number", entity.map(PetEntity::getLevel).orElse(1)),
                        new Placeholder("pet_level_progress", percentage),
                        new Placeholder("pet_action_bar", ActionBarUtils.getReplacedBar(percentage)),
                        new Placeholder("pet_xp", xp),
                        new Placeholder("pet_max_xp", maxXp)
                ).get();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        if(isItem(slot, config.getCloseGUI())) {
            player.closeInventory();
        }else if(isItem(slot, config.getConvertPetToItemDisabled()) || isItem(slot, config.getConvertPetToItemEnabled())){
            Optional<UserData> data = box.service().getData(uuid);

            data.ifPresent(UserData::switchConvertToItemMode);

            reOpen(player);
        }else if(isItem(slot, config.getHidePetsEnabled()) || isItem(slot, config.getHidePetsDisabled())) {
            Optional<UserData> data = box.service().getData(uuid);

            data.ifPresent(UserData::switchPetsHiddenMode);

            reOpen(player);
        }

    }

    private void reOpen(Player player){
        player.openInventory(new PetMainGUI(box, player).getInventory());
    }
}
