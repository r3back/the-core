package com.qualityplus.minions.base.handler;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.api.handler.LayoutHandler;
import com.qualityplus.minions.base.minions.entity.getter.LayoutGetter;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.layout.LayoutType;
import com.qualityplus.minions.base.minions.minion.layout.MinionLayout;
import com.qualityplus.minions.util.MinionAnimationUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public final class LayoutHandlerImpl implements LayoutHandler, LayoutGetter {
    private final UUID minionUniqueId;
    private final Minion minion;

    @Override
    public Boolean hasInvalidLayout(ArmorStand armorStand) {
        MinionLayout minionLayout = minion.getMinionLayout();

        List<XMaterial> noException = minionLayout.getAllMaterialsCauseExceptionExcept();
        List<XMaterial> exceptions = minionLayout.getMaterialThatCauseException();

        List<Vector> vectors = getMinionLayout(minion).equals(LayoutType.THREE_X_THREE) ? MinionAnimationUtil.getThree() : MinionAnimationUtil.getSecond();

        Location location = armorStand.getLocation().clone()
                .subtract(new Vector(0, 1, 0));

        for (Vector vector : vectors) {
            Location newLocation = location.clone().add(vector);

            Block block = newLocation.getBlock();

            if (BlockUtils.isNull(block)) continue;

            XMaterial material = XMaterial.matchXMaterial(block.getType());

            if (exceptions.contains(material)) {
                return true;
            }

            if (noException.contains(material))
                continue;

            return true;
        }

        return false;
    }

    @Override
    public UUID getMinionUniqueId() {
        return minionUniqueId;
    }
}