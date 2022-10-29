package com.qualityplus.alchemist.base.gui.brewing;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.base.event.AlchemistBrewEvent;
import com.qualityplus.alchemist.base.recipes.BrewingRecipe;
import com.qualityplus.alchemist.base.stand.StandEffects;
import com.qualityplus.alchemist.util.AlchemistFinderUtil;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.util.time.RemainingTime;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class AlchemistStandManager {
    private final Map<UUID, Integer> tasksMap = new HashMap<>();

    private final Inventory inventory;

    private final Location location;

    private final Box box;

    private Markable time;

    private int effect;

    public AlchemistStandManager(Inventory inventory, Location location, Box box) {
        this.inventory = inventory;
        this.location = location;
        this.box = box;
        this.time = new Markable(0,0);
        this.effect = 0;
    }

    public void check(Player player){
        Bukkit.getScheduler().runTaskLater(box.plugin(), () -> {
            BrewingRecipe recipe = AlchemistFinderUtil.getAlchemyRecipe(inventory, box.files().inventories().standGUIConfig, player);

            if(recipe == null){
                if(time.remainingTime() > 0)
                    clearAll();
            }else{
                if(time.remainingTime() <= 0){
                    manageTime(recipe);
                    startBrewing();
                    checkIfEnd(player, recipe);
                }
            }
            setItemsInBrewingStand();
        }, 0L);
    }

    public void setItemsInInventory(){
        BrewingStand brewingStand = getBrewingStand();

        if(brewingStand == null) return;

        Map<Integer, Integer> relationSlots = getSimilarSlots();

        relationSlots.forEach((key, value) -> inventory.setItem(value, brewingStand.getInventory().getItem(key)));

    }

    private void setItemsInBrewingStand(){
        BrewingStand brewingStand = getBrewingStand();

        if(brewingStand == null) return;

        getSimilarSlots().forEach((key, value) -> brewingStand.getInventory().setItem(key, inventory.getItem(value)));
    }

    private Map<Integer, Integer> getSimilarSlots(){
        AlchemistStandGUIConfig config = box.files().inventories().standGUIConfig;

        return new HashMap<Integer, Integer>() {{
            int i = 0;
            for(Integer slot : config.getInputSlots()){
                put(i, slot);
                i++;
            }
            put(i, config.getFuelSlot());
        }};
    }

    private void startBrewing(){
        UUID uuid = UUID.randomUUID();

        StandEffects standEffects = box.files().inventories().standGUIConfig.getStandEffects();

        this.tasksMap.put(uuid, Bukkit.getScheduler().runTaskTimer(box.plugin(), () -> {
            if(time.remainingTime() > 0){
                standEffects
                        .getEffectList()
                        .get(effect)
                        .forEach(ef -> inventory.setItem(ef.getSlot(), ItemStackUtils.makeItem(ef.getItem(), Collections.singletonList(new Placeholder("remaining_time", remainingTime())))));

                effect = standEffects.getEffectList().containsKey(effect + 1) ? effect + 1 : 0;
            }else
                cancelTask(uuid);

        }, 0L, 3L).getTaskId());
    }

    private void checkIfEnd(Player player, BrewingRecipe recipe){
        UUID uuid = UUID.randomUUID();

        this.tasksMap.put(uuid, Bukkit.getScheduler().runTaskTimer(box.plugin(), () -> {
            if(time.remainingTime() < 0){
                finish(player, recipe);
                clearAll();
                cancelTask(uuid);
            }
        }, 0L, 0L).getTaskId());
    }


    private String remainingTime(){
        RemainingTime toParse = time.getRemainingTime();

        return toParse.getSeconds() + "." + toParse.getMillis();
    }

    private void manageTime(BrewingRecipe recipe){
        this.time = new Markable(recipe.getTimer().getEffectiveTime(), System.currentTimeMillis());
    }

    private void clearAll(){
        this.tasksMap.keySet().forEach(this::cancelTask);

        this.time = new Markable(0,0);
        this.effect = 0;

        InventoryUtils.fillInventory(inventory, box.files().inventories().standGUIConfig.getBackground());
    }

    private void finish(Player player, BrewingRecipe recipe){
        AlchemistBrewEvent event = new AlchemistBrewEvent(player, recipe);

        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled())
            return;

        box.files().inventories().standGUIConfig.getInputSlots()
                .stream()
                .filter(slot -> !ItemStackUtils.isNull(inventory.getItem(slot)))
                .forEach(i -> {
                    inventory.setItem(i, null);

                    Bukkit.getScheduler().runTask(box.plugin(), () -> inventory.setItem(i, recipe.getOutPut()));
                });

        int slot = box.files().inventories().standGUIConfig.getFuelSlot();

        inventory.setItem(slot, getItemToPut(recipe, inventory, slot));

    }

    private ItemStack getItemToPut(BrewingRecipe recipe, Inventory inventory, int slot){
        ItemStack itemStack = inventory.getItem(slot);

        if(ItemStackUtils.isNull(itemStack)) return null;

        int newAmount = itemStack.getAmount() - Optional.ofNullable(recipe.getFuel()).map(ItemStack::getAmount).orElse(1);

        if(newAmount <= 0) return null;

        itemStack.setAmount(newAmount);

        return itemStack;
    }

    private void cancelTask(UUID uuid){
        Optional.ofNullable(tasksMap.getOrDefault(uuid, null)).ifPresent(Bukkit.getScheduler()::cancelTask);
    }

    public BrewingStand getBrewingStand(){
        if(location == null) return null;
        Block bl = location.getBlock();
        if(!bl.getType().equals(Material.BREWING_STAND)) return null;
        return (BrewingStand) bl.getState();
    }
}
