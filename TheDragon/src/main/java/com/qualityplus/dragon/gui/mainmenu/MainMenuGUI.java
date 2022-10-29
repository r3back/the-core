package com.qualityplus.dragon.gui.mainmenu;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.location.LocationUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.dragon.api.box.Box;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.api.game.structure.GameStructure;
import com.qualityplus.dragon.gui.TheDragonGUI;
import com.qualityplus.dragon.gui.altars.DragonAltarsGUI;
import com.qualityplus.dragon.gui.crystals.DragonCrystalsGUI;
import com.qualityplus.dragon.gui.dragons.DragonsGUI;
import com.qualityplus.dragon.gui.guardians.DragonGuardiansGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class MainMenuGUI extends TheDragonGUI {
    private final MainMenuGUIConfig config;

    public MainMenuGUI(Box box) {
        super(box.files().inventories().mainMenuGUIConfig, box);

        this.config = box.files().inventories().mainMenuGUIConfig;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(inventory, config.getBackground());

        inventory.setItem(config.getSchematicItem().slot, ItemStackUtils.makeItem(config.getSchematicItem(), getSchematicPlaceholders()));

        inventory.setItem(config.getAltarItem().slot, ItemStackUtils.makeItem(config.getAltarItem(), getAltarsPlaceholders()));

        inventory.setItem(config.getCrystalItem().slot, ItemStackUtils.makeItem(config.getCrystalItem(), getCrystalsPlaceholders()));

        inventory.setItem(config.getSpawnItem().slot, ItemStackUtils.makeItem(config.getSpawnItem(), getSpawnPlaceholders()));

        inventory.setItem(config.getDragonsItem().slot, ItemStackUtils.makeItem(config.getDragonsItem(), getDragonsPlaceholders()));

        inventory.setItem(config.getGuardiansItem().slot, ItemStackUtils.makeItem(config.getGuardiansItem(), getGuardiansPlaceholders()));

        inventory.setItem(config.getCloseGUI().slot, ItemStackUtils.makeItem(config.getCloseGUI()));

        return inventory;
    }

    private List<IPlaceholder> getGuardiansPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_guardians_amount", box.files().guardians().guardianMap.size())).get();
    }

    private List<IPlaceholder> getDragonsPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_dragons_amount", box.files().dragons().dragonMap.size())).get();
    }


    private List<IPlaceholder> getCrystalsPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_crystals_amount", box.structures().getCrystals().size())).get();
    }


    private List<IPlaceholder> getAltarsPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_altars_amount", box.structures().getAltars().size())).get();
    }

    private List<IPlaceholder> getSchematicPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_schematic_id", box.files().config().eventSettings.schematicSettings.id)).get();
    }

    private List<IPlaceholder> getSpawnPlaceholders(){
        return PlaceholderBuilder.create(new Placeholder("thedragon_spawn_location", LocationUtils.toString(box.structures().getSpawn()
                .map(DragonSpawn::getLocation).
                orElse(null))))
                .get();
    }


    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);

        if(!getTarget(e).equals(ClickTarget.INSIDE)) return;

        Player player = (Player) e.getWhoClicked();

        int slot = e.getSlot();

        if (isItem(slot, config.getCloseGUI())) {
            player.closeInventory();
        }else if(isItem(slot, config.getCrystalItem())){
            player.openInventory(new DragonCrystalsGUI(box, 1).getInventory());
        }else if(isItem(slot, config.getAltarItem())){
            player.openInventory(new DragonAltarsGUI(box, 1).getInventory());
        }else if(isItem(slot, config.getDragonsItem())){
            player.openInventory(new DragonsGUI(box).getInventory());
        }else if(isItem(slot, config.getGuardiansItem())){
            player.openInventory(new DragonGuardiansGUI(box, 1).getInventory());
        }else if(isItem(slot, config.getSpawnItem()))
            box.structures().getSpawn()
                    .filter(Objects::nonNull)
                    .map(GameStructure::getLocation)
                    .filter(Objects::nonNull)
                    .ifPresent(player::teleport);
    }

}
