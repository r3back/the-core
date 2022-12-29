package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

@UtilityClass
public class PlaceAnimation {
    public void run(ArmorStand armorStand, final FinishAnimation callback){
        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        (new BukkitRunnable() {
            int counter = 0;
            int passedTimes = 0;
            boolean iterateId = true;
            EulerAngle[] toIterate = MinionAnimationUtil.rightHandFastPickaxeMovementNew;

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
                    toIterate = iterateId ? MinionAnimationUtil.rightHandFastPickaxeMovementNew : MinionAnimationUtil.rightHandFastPickaxeMovementNew2;
                }

                armorStand.setRightArmPose(toIterate[this.counter]);
                armorStand.setLeftArmPose(toIterate[this.counter]);

                this.passedTimes++;
                this.counter++;

            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 2L);

    }
}
