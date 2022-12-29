package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

@UtilityClass
public class StartAnimation {
    public void run(final ArmorStand armorStand, final FinishAnimation callback){
        final int animationsToPerform = MinionAnimationUtil.rightArmUp.length;
        (new BukkitRunnable() {
            int counter = 0;
            int counter2 = MinionAnimationUtil.rightArmUp.length;

            public void run() {
                if (this.counter >= animationsToPerform) {
                    if (this.counter2 <= 0) {
                        cancel();
                        callback.finished();
                        return;
                    }
                    this.counter2--;
                    armorStand.setRightArmPose(MinionAnimationUtil.rightArmUp[counter2]);
                    armorStand.setLeftArmPose(MinionAnimationUtil.leftArmUp[counter2]);
                    armorStand.setHeadPose(MinionAnimationUtil.moveHeadBack[counter2]);
                }else {
                    armorStand.setRightArmPose(MinionAnimationUtil.rightArmUp[counter]);
                    armorStand.setLeftArmPose(MinionAnimationUtil.leftArmUp[counter]);
                    armorStand.setHeadPose(MinionAnimationUtil.moveHeadBack[counter]);
                    counter++;
                }
            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 1L);

    }
}
