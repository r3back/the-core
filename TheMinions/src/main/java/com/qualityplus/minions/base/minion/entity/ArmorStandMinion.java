package com.qualityplus.minions.base.minion.entity;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.random.EasyRandom;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.util.time.RemainingTime;
import com.qualityplus.assistant.util.time.TimeUtils;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.face.MinionFace;
import com.qualityplus.minions.base.minion.layout.LayoutType;
import com.qualityplus.minions.util.FinishAnimation;
import com.qualityplus.minions.util.MovementsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import com.qualityplus.assistant.util.list.ListUtils.ListBuilder;

import java.util.*;

public abstract class ArmorStandMinion extends MinecraftMinion {
    protected static final List<Vector> THREE_METERS = Arrays.asList(
            new Vector(0, 0, 3), new Vector(0, 0, -3), new Vector(1, 0, -3),
            new Vector(2, 0, -3), new Vector(-1, 0, -3), new Vector(-2, 0, -3),
            new Vector(1, 0, 3), new Vector(2, 0, 3), new Vector(-1, 0, 3),
            new Vector(-2, 0, 3), new Vector(3, 0, 0), new Vector(-3, 0, 0),
            new Vector(3, 0, 1), new Vector(3, 0, 2), new Vector(3, 0, 3),
            new Vector(-3, 0, 1), new Vector(-3, 0, 2), new Vector(-3, 0, 3),
            new Vector(3, 0, -1), new Vector(3, 0, -2), new Vector(3, 0, -3),
            new Vector(-3, 0, -1), new Vector(-3, 0, -2), new Vector(-3, 0, -3)
    );

    protected static final List<Vector> TWO_METERS = Arrays.asList(
            new Vector(0, 0, 1), new Vector(0, 0, 2),
            new Vector(0, 0, -1), new Vector(0, 0, -2),

            //Arriba Izquierda
            new Vector(1, 0, -1), new Vector(1, 0, -2),
            new Vector(2, 0, -1), new Vector(2, 0, -2),

            //Abajo Izquierda
            new Vector(-1, 0, -1), new Vector(-1, 0, -2),
            new Vector(-2, 0, -1), new Vector(-2, 0, -2),

            new Vector(1, 0, 0), new Vector(2, 0, 0),
            new Vector(-1, 0, 0), new Vector(-2, 0, 0),

            //Abajo Derecha
            new Vector(1, 0, 1), new Vector(2, 0, 1),
            new Vector(1, 0, 2), new Vector(2, 0, 2),
            //,

            //Abajo Izquierda
            new Vector(-1, 0, 1), new Vector(-2, 0, 1),
            new Vector(-1, 0, 2), new Vector(-2, 0, 2)
    );

    protected long lastActionTime = 0;
    protected ArmorStand armorStand;
    protected Location miningLoc;
    protected boolean isMining = false;

    protected ArmorStandMinion(UUID petUniqueId, UUID owner, Minion pet) {
        super(petUniqueId, owner, pet);
    }

    @Override
    public void spawn(Location location) {
        super.spawn(location);

        Optional.ofNullable(location)
                .ifPresent(this::createArmorStand);
    }

    @Override
    public void deSpawn(DeSpawnReason deSpawnReason) {
        super.deSpawn(deSpawnReason);

        Optional.ofNullable(armorStand)
                .filter(this::entityIsValid)
                .ifPresent(Entity::remove);
    }

    @Override
    public void update() {
        super.update();

        tickMinion();

        Optional.ofNullable(armorStand).ifPresent(stand -> stand.setCustomName(getDisplayName()));
    }

    @Override
    public void tickMinion(){
        if(!entityIsValid(armorStand)) return;

        int level = getLevel();

        Timer timer = minion.getTimer(level);


        if(lastActionTime != 0){
            RemainingTime remainingTime = TimeUtils.getRemainingTime(new Markable(timer.getEffectiveTime(), lastActionTime).remainingTime());

            if(!remainingTime.isZero()){
                Bukkit.getConsoleSender().sendMessage("NO ES ZERO VOLVIENDO!");
                return;
            }
        }


        Location baseLoc = armorStand.getLocation()
                .getBlock()
                .getLocation()
                .clone()
                .subtract(new Vector(0, 1, 0));


        if(isMining){
        }else{
            List<Vector> vectors = minion.getMinionLayout().getType().equals(LayoutType.THREE_X_THREE) ? ListBuilder.of(THREE_METERS).with(TWO_METERS).get() : TWO_METERS;

            Vector random = RandomSelector.getRandom(vectors);

            if(random == null) return;

            Location newLocation = baseLoc.clone().add(random);

            rotate(newLocation);

            isMining = true;

            /*MovementsUtil.performStartingAnimation(newLocation, armorStand, new FinishAnimation() {
                public void finished() {

                    if(BlockUtils.isNull(newLocation.getBlock())){
                        newLocation.getBlock().setType(XMaterial.IRON_ORE.parseMaterial());
                    }else{
                        newLocation.getBlock().breakNaturally();
                    }
                    lastActionTime = System.currentTimeMillis();

                    isMining = false;
                }
            });*/

            getToStartingPosition(new FinishAnimation() {
                public void finished() {

                    if(BlockUtils.isNull(newLocation.getBlock())){
                        newLocation.getBlock().setType(XMaterial.IRON_ORE.parseMaterial());
                    }else{
                        newLocation.getBlock().breakNaturally();
                    }
                    lastActionTime = System.currentTimeMillis();

                    isMining = false;
                }
            });
        }

    }

    private void getToStartingPosition(final FinishAnimation callback) {
        final int animationsToPerform = MovementsUtil.moveRightHandUpSlightly.length;
        (new BukkitRunnable() {
            int counter = 0;

            public void run() {
                if (this.counter >= animationsToPerform) {
                    cancel();
                    callback.finished();
                    return;
                }
                armorStand.setRightArmPose(MovementsUtil.moveRightHandUpSlightly[this.counter]);
                this.counter++;
            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 1L);
    }

    private void rotate(Location newLocation){
        Location loc = armorStand.getLocation().clone();

        Vector dirBetweenLocations = newLocation.toVector().subtract(armorStand.getLocation().toVector());

        loc.setDirection(dirBetweenLocations);

        armorStand.teleport(loc);
    }

    public static BlockFace minionFaceToBlockFace(MinionFace paramMinionFace) {
        return BlockFace.valueOf(paramMinionFace.toString());
    }

    private boolean entityIsValid(ArmorStand armorStand){
        return armorStand != null && !armorStand.isDead();
    }

    private void createArmorStand(Location location){

        armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setSmall(true);
        armorStand.setVisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setHelmet(getItemStack());
        armorStand.getEquipment().setItemInMainHand(XMaterial.DIAMOND_PICKAXE.parseItem());
        armorStand.getEquipment().setChestplate(XMaterial.LEATHER_CHESTPLATE.parseItem());
        armorStand.getEquipment().setLeggings(XMaterial.LEATHER_LEGGINGS.parseItem());
        armorStand.getEquipment().setBoots(XMaterial.LEATHER_BOOTS.parseItem());

        update();
    }

    private ItemStack getItemStack(){
        return ItemBuilder.of()
                .amount(1)
                .displayName("")
                .lore(new ArrayList<>())
                .material(minion.getMinionEntityOptions().getMaterial())
                .headData(minion.getMinionEntityOptions().getTexture())
                .buildStack();
    }
}
