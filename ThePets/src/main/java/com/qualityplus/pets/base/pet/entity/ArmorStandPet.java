package com.qualityplus.pets.base.pet.entity;

import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.pets.base.pet.Pet;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class ArmorStandPet extends MinecraftPet {
    private ArmorStand armorStand;

    private ArmorStandPet(UUID petUniqueId, UUID owner, Pet pet) {
        super(petUniqueId, owner, pet);
    }

    public static ArmorStandPet create(UUID petUniqueId, UUID owner, Pet pet){
        return new ArmorStandPet(petUniqueId, owner, pet);
    }

    @Override
    public void spawn() {
        super.spawn();

        Optional.ofNullable(getNextLocation())
                .ifPresent(this::createArmorStand);
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        super.deSpawn(deSpawnReason);

        Optional.ofNullable(armorStand)
                .filter(ArmorStandPet::entityIsValid)
                .ifPresent(Entity::remove);
    }

    @Override
    public void update() {
        super.update();

        Optional.ofNullable(armorStand).ifPresent(stand -> stand.setCustomName(getDisplayName()));
    }

    @Override
    public void prepareToLevelUp() {
        super.removePotions();
        super.removeStats();
    }


    @Override
    public void followOwner() {
        Optional.ofNullable(getNextLocation())
                .filter(location -> entityIsValid(armorStand))
                .ifPresent(armorStand::teleport);
    }


    private static boolean entityIsValid(ArmorStand armorStand){
        return armorStand != null && !armorStand.isDead();
    }

    private void createArmorStand(Location location){

        armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setHelmet(getItemStack());

        update();
    }

    private ItemStack getItemStack(){
        return ItemBuilder.of()
                .amount(1)
                .displayName("")
                .lore(new ArrayList<>())
                .material(pet.getPetEntityOptions().getMaterial())
                .headData(pet.getPetEntityOptions().getTexture())
                .buildStack();
    }
}
