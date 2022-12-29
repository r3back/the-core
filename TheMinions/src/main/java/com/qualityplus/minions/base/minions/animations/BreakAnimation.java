package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.assistant.util.time.Timer;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.time.Duration;

@UtilityClass
public class BreakAnimation {
    public void run(ArmorStand armorStand, final Block block, final FinishAnimation callback){
        Timer timer = new Timer(2, Timer.TimeType.SECONDS);

        long millis = Duration.ofSeconds(timer.getSeconds()).toMillis();

        int toDivide = (int) ((int) millis / (20 * timer.getSeconds()));

        int maxTimes = (int) (millis / toDivide);

        final double blockToPerform = 9D / maxTimes;

        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        (new BukkitRunnable() {
            int counter = 0;
            int passedTimes = 0;
            double damageCounter = 0;

            //Si es true es el que va hacia arriba y si es false es el que vuelve
            boolean iterateId = true;
            EulerAngle[] toIterate = MinionAnimationUtil.rightHandFastPickaxeMovementNew;

            public void run() {
                if(passedTimes >= maxTimes){
                    cancel();
                    armorStand.setRightArmPose(new EulerAngle(0, 0, 0));
                    TheAssistantPlugin.getAPI().getNms().damageBlock(PlayerUtils.all(), block, -1);
                    callback.finished();
                    return;
                }

                if(counter == toIterate.length - 1){
                    counter = 0;
                    iterateId = !iterateId;
                    toIterate = iterateId ? MinionAnimationUtil.rightHandFastPickaxeMovementNew : MinionAnimationUtil.rightHandFastPickaxeMovementNew2;
                }

                TheAssistantPlugin.getAPI().getNms().damageBlock(PlayerUtils.all(), block, (int) Math.ceil(damageCounter));

                armorStand.setRightArmPose(toIterate[this.counter]);

                this.damageCounter+=blockToPerform;

                this.passedTimes++;

                this.counter++;

            }
        }).runTaskTimer(TheMinions.getInstance(), 0L, 1L);
    }
}
