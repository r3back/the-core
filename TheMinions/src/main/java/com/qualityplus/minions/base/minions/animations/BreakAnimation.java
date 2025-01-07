package com.qualityplus.minions.base.minions.animations;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.armorstand.ArmorStandUtil;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.assistant.util.time.HumanTime;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.util.MinionAnimationUtil;
import com.qualityplus.minions.base.minions.entity.animation.FinishAnimation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.time.Duration;

@RequiredArgsConstructor
public final class BreakAnimation extends BukkitRunnable {
    private final FinishAnimation callback;
    private final ArmorStand armorStand;
    private final Block block;

    private int counter = 0;
    private int passedTimes = 0;
    private double damageCounter = 0;

    //Si es true es el que va hacia arriba y si es false es el que vuelve
    private boolean iterateId = true;
    private EulerAngle[] toIterate = MinionAnimationUtil.rightHandFastPickaxeMovementNew;

    private final double maxTimes;
    private final double blockToPerform;

    private BreakAnimation(final FinishAnimation callback, final ArmorStand armorStand, final Block block) {
        this.callback = callback;
        this.armorStand = armorStand;
        this.block = block;

        HumanTime timer = new HumanTime(2, HumanTime.TimeType.SECONDS);
        long millis = Duration.ofSeconds(timer.getSeconds()).toMillis();
        int toDivide = (int) ((int) millis / (20 * timer.getSeconds()));

        maxTimes = (int) (millis / toDivide);
        blockToPerform = 9D / maxTimes;
    }

    public static BukkitRunnable start(final FinishAnimation callBack, final ArmorStand armorStand, final Block block) {
        BukkitRunnable runnable = new BreakAnimation(callBack, armorStand, block);

        runnable.runTaskTimer(TheMinions.getInstance(), 0L, 1L);

        return runnable;
    }


    @Override
    public void run() {

        armorStand.setHeadPose(new EulerAngle(-24.5, 0, 0));

        if (passedTimes >= maxTimes) {
            cancel();

            if (ArmorStandUtil.entityIsValid(armorStand))
                armorStand.setRightArmPose(new EulerAngle(0, 0, 0));

            TheAssistantPlugin.getAPI().getNms().damageBlock(PlayerUtils.all(), block, -1);

            callback.finished();

            return;
        }

        if (counter == toIterate.length - 1) {
            counter = 0;
            iterateId = !iterateId;
            toIterate = iterateId ? MinionAnimationUtil.rightHandFastPickaxeMovementNew : MinionAnimationUtil.rightHandFastPickaxeMovementNew2;
        }

        TheAssistantPlugin.getAPI().getNms().damageBlock(PlayerUtils.all(), block, (int) Math.ceil(damageCounter));

        if (ArmorStandUtil.entityIsValid(armorStand))
            armorStand.setRightArmPose(toIterate[this.counter]);


        this.damageCounter+=blockToPerform;
        this.passedTimes++;
        this.counter++;
    }
}
