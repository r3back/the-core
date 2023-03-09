package com.qualityplus.minions.base.minions.entity.type;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.base.minions.animations.BreakAnimation;
import com.qualityplus.minions.base.minions.animations.PlaceAnimation;
import com.qualityplus.minions.base.minions.entity.ArmorStandMinion;
import com.qualityplus.minions.base.minions.entity.mob.MinionMobEntity;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.mob.MinionMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public final class MobKillerMinion extends ArmorStandMinion<MinionMobEntity> {

    private MobKillerMinion(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded) {
        super(minionUniqueId, owner, minion, loaded);
    }

    public static MobKillerMinion create(UUID minionUniqueId, UUID owner, Minion minion, boolean loaded){
        return new MobKillerMinion(minionUniqueId, owner, minion, loaded);
    }

    @Override
    protected void checkEntityAfterRotate(MinionMobEntity entity) {
        if (!state.isLoaded()) {
            addItemsToMinionInventory();
            return;
        }

        armorStand.manipulateEntity(armorStand -> handleAnimationCallBack(armorStand, entity));
    }

    @Override
    public void doIfItsNull(MinionMobEntity entity){
        try {
            MinionMob minionMob = minion.getMinionLayout().getMinionMob();

            Location location = entity.getLocation();

            if(location != null){
                if(minionMob.isFromMythicMobs()){
                    TheAssistantPlugin.getAPI().getAddons().getMythicMobs().spawn(minionMob.getId(), location, minionMob.getMythicMobsLevel());
                }else{
                    if(minionMob.getEntityType() == null) return;

                    location.getWorld().spawnEntity(location, minionMob.getEntityType());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        teleportBack();
    }

    @Override
    public void doIfItsNotNull(MinionMobEntity entity){
        try {
            LivingEntity living = (LivingEntity) entity.getEntity();

            living.setHealth(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        //BlockUtils.setBlock(block, XMaterial.AIR);

        addItemsToMinionInventory();

        teleportBack();
    }

    private void handleAnimationCallBack(ArmorStand armorStand, MinionMobEntity mobEntity){
        Entity entity = mobEntity.getEntity();

        if(entity == null || entity.isDead()){

            /*this.breakingAnimation = */PlaceAnimation.start(() -> doIfItsNull(mobEntity), armorStand);
        }else {

            /*this.breakingAnimation = */PlaceAnimation.start(() -> doIfItsNotNull(mobEntity), armorStand);
        }

    }


}
