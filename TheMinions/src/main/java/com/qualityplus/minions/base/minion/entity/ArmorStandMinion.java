package com.qualityplus.minions.base.minion.entity;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.ImmutableMap;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.assistant.util.faster.FasterMap;
import com.qualityplus.assistant.util.itemstack.ItemBuilder;
import com.qualityplus.assistant.util.location.LocationUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.assistant.util.random.EasyRandom;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.util.time.RemainingTime;
import com.qualityplus.assistant.util.time.TimeUtils;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.assistant.util.time.Timer.TimeType;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.base.minion.Minion;
import com.qualityplus.minions.base.minion.face.MinionFace;
import com.qualityplus.minions.base.minion.layout.LayoutType;
import com.qualityplus.minions.util.BlockSearchAnimation;
import com.qualityplus.minions.util.FinishAnimation;
import com.qualityplus.minions.util.MovementsUtil;
import com.qualityplus.minions.util.VectorSection;
import eu.okaeri.commons.bukkit.item.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import com.qualityplus.assistant.util.list.ListUtils.ListBuilder;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ArmorStandMinion extends MinecraftMinion {

    static {
        ImmutableMap.Builder<Integer, VectorSection> builder = FasterMap.builder(Integer.class, VectorSection.class);

        // - Z = ARRIBA
        // + Z = ABAJO
        // - X = IZQUIERDA
        // + X = DERECHA
        //1
        builder.put(1, new VectorSection(new Vector(0, 0, 1), new Vector(0, 0, 2), new Vector(0, 0, 3)));
        builder.put(2, new VectorSection(new Vector(1, 0, 1), new Vector(1, 0, 2), new Vector(1, 0, 3)));
        builder.put(3, new VectorSection(new Vector(2, 0, 1), new Vector(2, 0, 2), new Vector(2, 0, 3)));
        builder.put(4, new VectorSection(new Vector(3, 0, 1), new Vector(3, 0, 2), new Vector(3, 0, 3)));
        builder.put(5, new VectorSection(new Vector(1, 0, 0), new Vector(2, 0, 0), new Vector(3, 0, 0)));
        builder.put(6, new VectorSection(new Vector(3, 0, -1), new Vector(3, 0, -2), new Vector(3, 0, -3)));
        builder.put(7, new VectorSection(new Vector(2, 0, -1), new Vector(2, 0, -2), new Vector(2, 0, -3)));
        builder.put(8, new VectorSection(new Vector(1, 0, -1), new Vector(1, 0, -2), new Vector(1, 0, -3)));
        builder.put(9, new VectorSection(new Vector(0, 0, -1), new Vector(0, 0, -2), new Vector(0, 0, -3)));
        builder.put(10, new VectorSection(new Vector(-1, 0, -1), new Vector(-1, 0, -2), new Vector(-1, 0, -3)));
        builder.put(11, new VectorSection(new Vector(-2, 0, -1), new Vector(-2, 0, -2), new Vector(-2, 0, -3)));
        builder.put(12, new VectorSection(new Vector(-3, 0, -1), new Vector(-3, 0, -2), new Vector(-3, 0, -3)));
        builder.put(13, new VectorSection(new Vector(-1, 0, 0), new Vector(-2, 0, 0), new Vector(-3, 0, 0)));
        builder.put(14, new VectorSection(new Vector(-3, 0, 1), new Vector(-3, 0, 2), new Vector(-3, 0, 3)));
        builder.put(15, new VectorSection(new Vector(-2, 0, 1), new Vector(-2, 0, 2), new Vector(-2, 0, 3)));
        builder.put(16, new VectorSection(new Vector(-1, 0, 1), new Vector(-1, 0, 2), new Vector(-1, 0, 3)));

        /*
           12 11 10  9  8 7 6
           12 11 10  9  8 7 6
           12 11 10  9  8 7 6
           13 13 13  -  5 5 5
           14 15 16  1  2 3 4
           14 15 16  1  2 3 4
           14 15 16  1  2 3 4
        */
        AXIS_POSITIONS = builder.build();
    }
    private static final List<Integer> ROTATE_AXIS_ID = Arrays.asList(1, 2 , 5 , 8, 9, 10, 13, 16);
    private static final Map<Integer, VectorSection> AXIS_POSITIONS;


    protected long lastActionTime = 0;
    protected ArmorStand armorStand;
    protected Location spawn;
    protected boolean isMining = true;

    protected ArmorStandMinion(UUID petUniqueId, UUID owner, Minion pet) {
        super(petUniqueId, owner, pet);

        this.lastActionTime = System.currentTimeMillis();
    }

    @Override
    public void spawn(Location location) {
        this.spawn = Optional.ofNullable(location).map(Location::clone).orElse(null);

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

        //tickMinion();

        Optional.ofNullable(armorStand).ifPresent(stand -> stand.setCustomName(getDisplayName()));
    }

    private List<Vector> getThree(){
        return AXIS_POSITIONS.values().stream()
                .map(VectorSection::getThirds)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Vector> getSecond(){
        return AXIS_POSITIONS.values().stream()
                .map(VectorSection::getSeconds)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Vector> getFirst(){
        return AXIS_POSITIONS.values().stream()
                .map(VectorSection::getFirsts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void tickMinion(){
        if(!entityIsValid(armorStand)) return;

        int level = getLevel();

        Timer timer = minion.getTimer(level);

        if(lastActionTime != 0){
            RemainingTime remainingTime = TimeUtils.getRemainingTime(new Markable(timer.getEffectiveTime(), lastActionTime).remainingTime());

            if(!remainingTime.isZero()){
                return;
            }
        }


        if(!isMining){
            Bukkit.getConsoleSender().sendMessage("ENTRO");

            isMining = true;

            rotateToBlock(new BlockSearchAnimation() {
                @Override
                public void finished(Block block) {
                    if(BlockUtils.isNull(block)){
                        place(block, new FinishAnimation() {
                            @Override
                            public void finished() {
                                block.setType(XMaterial.IRON_ORE.parseMaterial());

                                teleportBack();
                            }
                        });
                    }else{
                        pickaxe(block, new FinishAnimation() {
                            public void finished() {
                                block.setType(XMaterial.AIR.parseMaterial());

                                teleportBack();
                            }
                        });
                    }
                }
            });
        }
    }

    private void teleportBack(){
        lastActionTime = System.currentTimeMillis();

        Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> armorStand.setHeadPose(new EulerAngle(0, 0, 0)), 15);

        Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> armorStand.teleport(spawn), 15);

        Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> isMining = false, 25);

    }

    private void rotateToBlock(final BlockSearchAnimation callback) {

        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        List<Vector> vectors = minion.getMinionLayout().getType().equals(LayoutType.THREE_X_THREE) ? getThree() : getSecond();

        Vector vector = RandomSelector.getRandom(vectors);

        Location location = armorStand.getLocation().clone()
                .subtract(new Vector(0, 1, 0));

        if(vector == null){
            callback.finished(location.getBlock());
            return;
        }

        Location newLocation = location.clone().add(vector);

        rotateArmorStand(newLocation);

        callback.finished(newLocation.getBlock());
    }


    private void rotateArmorStand(Location newLocation){
        Location location = armorStand.getLocation().clone();

        armorStand.teleport(location.clone().setDirection(newLocation.clone().subtract(location).toVector()));
    }

    private void place(final Block block, final FinishAnimation callback) {
        Timer timer = new Timer(1, TimeType.SECONDS);

        long millis = Duration.ofSeconds(timer.getSeconds()).toMillis();

        int toDivide = (int) ((int) millis / (20 * timer.getSeconds()));

        int maxTimes = (int) (millis / toDivide);

        final double blockToPerform = 9D / maxTimes;

        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        //Bukkit.getConsoleSender().sendMessage("Length: " + animationsToPerform);
        (new BukkitRunnable() {
            int counter = 0;
            int passedTimes = 0;

            //Si es true es el que va hacia arriba y si es false es el que vuelve
            boolean iterateId = true;
            EulerAngle[] toIterate = MovementsUtil.rightHandFastPickaxeMovementNew;

            public void run() {
                if(passedTimes >= 11){
                    cancel();
                    armorStand.setRightArmPose(new EulerAngle(0, 0, 0));
                    armorStand.setLeftArmPose(new EulerAngle(0, 0, 0));
                    callback.finished();
                    return;
                }

                if(counter == toIterate.length - 1){
                    counter = 0;

                    iterateId = !iterateId;

                    toIterate = iterateId ? MovementsUtil.rightHandFastPickaxeMovementNew : MovementsUtil.rightHandFastPickaxeMovementNew2;
                }


                armorStand.setRightArmPose(toIterate[this.counter]);
                armorStand.setLeftArmPose(toIterate[this.counter]);


                this.passedTimes++;

                this.counter++;

            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 2L);
    }

    private void pickaxe(final Block block, final FinishAnimation callback) {
        Timer timer = new Timer(2, TimeType.SECONDS);

        long millis = Duration.ofSeconds(timer.getSeconds()).toMillis();

        int toDivide = (int) ((int) millis / (20 * timer.getSeconds()));

        int maxTimes = (int) (millis / toDivide);

        final double blockToPerform = 9D / maxTimes;

        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        //Bukkit.getConsoleSender().sendMessage("Length: " + animationsToPerform);
        (new BukkitRunnable() {
            int counter = 0;
            int passedTimes = 0;
            double damageCounter = 0;

            //Si es true es el que va hacia arriba y si es false es el que vuelve
            boolean iterateId = true;
            EulerAngle[] toIterate = MovementsUtil.rightHandFastPickaxeMovementNew;

            public void run() {
                if(passedTimes >= maxTimes){
                    cancel();
                    armorStand.setRightArmPose(new EulerAngle(0, 0, 0));
                    TheAssistantPlugin.getAPI().getNms().damageBlock(getPlayer(), block, -1);
                    callback.finished();
                    return;
                }

                if(counter == toIterate.length - 1){
                    counter = 0;

                    iterateId = !iterateId;

                    toIterate = iterateId ? MovementsUtil.rightHandFastPickaxeMovementNew : MovementsUtil.rightHandFastPickaxeMovementNew2;
                }


                TheAssistantPlugin.getAPI().getNms().damageBlock(getPlayer(), block, (int) Math.ceil(damageCounter));

                armorStand.setRightArmPose(toIterate[this.counter]);

                this.damageCounter+=blockToPerform;

                this.passedTimes++;

                this.counter++;

            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 1L);
    }

    private void getToStartingPositionB(final Block block, final FinishAnimation callback) {
        final int animationsToPerform = MovementsUtil.rightHandFastPickaxeMovement.length;
        final double blockToPerform = 9D / animationsToPerform;
        Bukkit.getConsoleSender().sendMessage("Length: " + animationsToPerform);
        (new BukkitRunnable() {
            int counter = 0;
            double damageCounter = 0;

            public void run() {
                if (this.counter >= animationsToPerform) {
                    cancel();
                    TheAssistantPlugin.getAPI().getNms().damageBlock(getPlayer(), block, -1);
                    callback.finished();
                    return;
                }

                TheAssistantPlugin.getAPI().getNms().damageBlock(getPlayer(), block, (int) Math.ceil(damageCounter));

                armorStand.setRightArmPose(MovementsUtil.rightHandFastPickaxeMovement[this.counter]);
                this.damageCounter+=blockToPerform;
                this.counter++;
            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 1L);
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

    private float toDegree(double angle) {
        return (float) Math.toDegrees(angle);
    }





    private boolean entityIsValid(ArmorStand armorStand){
        return armorStand != null && !armorStand.isDead();
    }

    private void createArmorStand(Location location){

        armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setArms(true);
        armorStand.setSmall(true);
        armorStand.setVisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCollidable(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setHelmet(getItemStack());


        armorStand.getEquipment().setItemInMainHand(XMaterial.DIAMOND_PICKAXE.parseItem());
        armorStand.getEquipment().setChestplate(colored(XMaterial.LEATHER_CHESTPLATE));
        armorStand.getEquipment().setLeggings(colored(XMaterial.LEATHER_LEGGINGS));
        armorStand.getEquipment().setBoots(colored(XMaterial.LEATHER_BOOTS));

        update();

        MovementsUtil.performStartingAnimation(armorStand, new FinishAnimation() {
            public void finished() {
                Bukkit.getScheduler().runTaskLater(TheMinions.getInstance(), () -> {
                    ArmorStandMinion.this.isMining = false;
                }, 20);
            }
        });


        Location baseLoc = armorStand.getLocation()
                .getBlock()
                .getLocation()
                .clone()
                .subtract(new Vector(0, 1, 0));


        Bukkit.getConsoleSender().sendMessage("Termino la creacion");
    }

    private ItemStack colored(XMaterial material){
        ItemStack bboots = new ItemStack(material.parseMaterial());
        LeatherArmorMeta meta4 = (LeatherArmorMeta) bboots.getItemMeta();
        meta4.setColor(Color.BLUE);
        bboots.setItemMeta(meta4);
        return bboots;
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
