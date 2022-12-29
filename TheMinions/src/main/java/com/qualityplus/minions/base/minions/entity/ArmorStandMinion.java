package com.qualityplus.minions.base.minions.entity;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.gui.FakeInventory;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.base.config.Skins;
import com.qualityplus.minions.base.handler.ArmorStandHandlerImpl;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.animations.StartAnimation;
import com.qualityplus.minions.base.minions.entity.status.MinionStatus;
import com.qualityplus.minions.persistance.data.MinionData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public abstract class ArmorStandMinion extends MinecraftMinion implements Listener {

    protected final ArmorStandHandler armorStand;

    protected ArmorStandMinion(UUID minionUniqueId, UUID owner, Minion pet) {
        super(minionUniqueId, owner, pet);

        this.armorStand = new ArmorStandHandlerImpl();
    }

    @Override
    public void spawn(Location location) {
        super.spawn(location);

        Optional.ofNullable(location)
                .ifPresent(this::createArmorStand);

        updateInventory();

        getData().ifPresent(minionData -> minionData.setLocation(location));
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        super.deSpawn(deSpawnReason);

        this.armorStand.removeEntity();

        if(!deSpawnReason.equals(DeSpawnReason.PLAYER_DE_SPAWN_PET)) return;

        getData().ifPresent(data -> data.setLocation(null));
    }

    @Override
    public void tick(){
        if(!armorStand.entityIsValid()) return;

        handlers.getFuelHandler().removeFuel();

        if(timeHasHappened())
            return;

        if(state.isBreaking() || state.isSelling())
            return;

        updateStatus();

        if(!state.getStatus().equals(MinionStatus.IDEAL_LAYOUT)) {
            sellIfItsPossible();
            return;
        }

        state.setBreaking(true);

        rotateToBlock();
    }

    private boolean timeHasHappened(){
        int level = getLevel();

        Timer timer = minion.getTimer(level);

        if(state.getLastActionTime() == 0) return true;

        long time = timer.getEffectiveTime();

        long reduction = getData().map(data -> data.getFuelReductionMillis(time) + data.getUpgradesReductionMillis(time)).orElse(0L);

        Markable markable = new Markable(time - reduction, state.getLastActionTime());

        return markable.isMarked();
    }

    @Override
    public void updateInventory(){
        int level = getLevel();

        int maxStorage = minion.getMaxStorage(level);

        state.setFakeInventory(TheAssistantPlugin.getAPI().getNms().getFakeInventory(null, maxStorage));

        state.getFakeInventory().setItems(getData().map(MinionData::getItemStackList).orElse(new HashMap<>()));

    }

    @Override
    public void updateSkin(){
        Optional<MinionData> data = getData();

        if(!data.isPresent()) return;

        int level = data.get().getLevel();

        if(data.get().getSkinEntity() == null){
            minion.getSkin(level).ifPresent(skin -> skin.apply(armorStand));
        }else{
            String id = data.get().getSkinEntity().getId();

            if(id == null) return;

            Skins.getSkin(id).ifPresent(skin -> skin.apply(armorStand));
        }
    }


    @Override
    public Collection<ItemStack> pickUpAllItems() {
        Optional<MinionData> minionData = TheMinions.getApi().getMinionsService().getData(state.getUuid());

        FakeInventory fakeInventory = state.getFakeInventory();

        List<ItemStack> itemStacks = fakeInventory.getItems()
                .values()
                .stream().filter(Objects::nonNull)
                .map(ItemStack::clone)
                .collect(Collectors.toList());

        fakeInventory.removeItems();

        minionData.ifPresent(minionData1 -> minionData1.setItemStackList(Collections.emptyMap()));

        return itemStacks;
    }


    protected void addItemsToMinionInventory(){
        MinionStorageState storageState = state.getStorageState();

        FakeInventory fakeInventory = state.getFakeInventory();

        //Items added in last check will be removed
        Optional.ofNullable(storageState.getToRemove()).ifPresent(toRemove -> toRemove.forEach((amount, item) -> fakeInventory.removeItems(item, amount)));

        //Items added in last check will be added
        Optional.ofNullable(storageState.getToAdd()).ifPresent(fakeInventory.getInventory()::addItem);

        //Updates items in data
        getData().ifPresent(minionData1 -> minionData1.setItemStackList(fakeInventory.getItems()));
    }

    protected void sellIfItsPossible(){
        handlers.getSellHandler().sellIfItsPossible();
    }

    protected abstract void checkBlockAfterRotate(Block block);

    protected abstract void doIfBlockIfNull(Block block);

    protected abstract void doIfBlockIsNotNull(Block block);

    protected void teleportBack(){
        state.setLastActionTime(System.currentTimeMillis());

        Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> {
            armorStand.manipulateArmorStand(entity -> entity.setHeadPose(new EulerAngle(0, 0, 0)));
            armorStand.teleportToSpawn();
        }, 15);


        Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> state.setBreaking(false), 25);
    }

    private void rotateToBlock() {
        handlers.getAnimationHandler()
                .getBlockToRotate(armorStand.getEntity())
                .thenAccept(this::checkBlockAfterRotate);
    }


    private void createArmorStand(Location location){
        armorStand.createEntity(this, location)
                        .thenAccept(this::handlePostCreation);
    }

    private void handlePostCreation(ArmorStand armorStand){
        updateSkin();

        StartAnimation.run(armorStand, () -> Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> state.setBreaking(false), 20));
    }

    private void updateStatus(){

        MinionStorageState storageState = handlers.getStorageHandler().getMinionStorageState();

        state.setStorageState(storageState);

        boolean hasInvalidLayout = handlers.getLayoutHandler().hasInvalidLayout(armorStand.getEntity());
        boolean hasFullStorage = storageState.isHasFullStorage();

        state.setStatus(hasInvalidLayout ? MinionStatus.INVALID_LAYOUT : hasFullStorage ? MinionStatus.STORAGE_FULL : MinionStatus.IDEAL_LAYOUT);

        armorStand.updateDisplayName(state);
    }

}
