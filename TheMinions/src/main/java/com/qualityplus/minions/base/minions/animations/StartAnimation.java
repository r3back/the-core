package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.assistant.util.armorstand.ArmorStandUtil;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public final class StartAnimation extends BukkitRunnable{
    private final FinishAnimation callback;
    private final ArmorStand armorStand;

    private final int animationsToPerform = MinionAnimationUtil.rightArmUp.length;
    private int counter2 = MinionAnimationUtil.rightArmUp.length;
    private int counter = 0;

    public static BukkitRunnable start(FinishAnimation callBack, ArmorStand armorStand){
        BukkitRunnable runnable = new StartAnimation(callBack, armorStand);

        runnable.runTaskTimer(TheMinions.getInstance(), 0L, 1L);

        return runnable;
    }

    @Override
    public void run(){
        if (this.counter >= animationsToPerform) {
            if (this.counter2 <= 0) {
                cancel();
                callback.finished();
                return;
            }

            this.counter2--;
            if(ArmorStandUtil.entityIsValid(armorStand)){
                armorStand.setRightArmPose(MinionAnimationUtil.rightArmUp[counter2]);
                armorStand.setLeftArmPose(MinionAnimationUtil.leftArmUp[counter2]);
                armorStand.setHeadPose(MinionAnimationUtil.moveHeadBack[counter2]);
            }
        }else {
            if (ArmorStandUtil.entityIsValid(armorStand)) {
                armorStand.setRightArmPose(MinionAnimationUtil.rightArmUp[counter]);
                armorStand.setLeftArmPose(MinionAnimationUtil.leftArmUp[counter]);
                armorStand.setHeadPose(MinionAnimationUtil.moveHeadBack[counter]);
            }
            counter++;
        }
    }
}
