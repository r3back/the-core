package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.assistant.util.armorstand.ArmorStandUtil;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

@RequiredArgsConstructor
public final class PlaceAnimation extends BukkitRunnable{
    private final FinishAnimation callback;
    private final ArmorStand armorStand;

    private EulerAngle[] toIterate = MinionAnimationUtil.rightHandFastPickaxeMovementNew;
    private boolean iterateId = true;
    private int passedTimes = 0;
    private int counter = 0;

    public static BukkitRunnable start(FinishAnimation callBack, ArmorStand armorStand) {
        BukkitRunnable runnable = new PlaceAnimation(callBack, armorStand);

        runnable.runTaskTimer(TheMinions.getInstance(), 0L, 2L);

        return runnable;
    }

    @Override
    public void run() {
        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        if (passedTimes >= 11) {
            cancel();
            if (ArmorStandUtil.entityIsValid(armorStand)) {
                armorStand.setRightArmPose(new EulerAngle(0, 0, 0));
                armorStand.setLeftArmPose(new EulerAngle(0, 0, 0));
            }
            callback.finished();
            return;
        }

        if (counter == toIterate.length - 1) {
            counter = 0;
            iterateId = !iterateId;
            toIterate = iterateId ? MinionAnimationUtil.rightHandFastPickaxeMovementNew : MinionAnimationUtil.rightHandFastPickaxeMovementNew2;
        }

        if (ArmorStandUtil.entityIsValid(armorStand)) {
            armorStand.setRightArmPose(toIterate[this.counter]);
            armorStand.setLeftArmPose(toIterate[this.counter]);
        }

        this.passedTimes++;
        this.counter++;

    }
}
