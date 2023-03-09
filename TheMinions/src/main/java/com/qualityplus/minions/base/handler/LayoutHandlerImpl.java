package com.qualityplus.minions.base.handler;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.armorstand.ArmorStandUtil;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.api.handler.LayoutHandler;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.entity.getter.LayoutGetter;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.layout.LayoutType;
import com.qualityplus.minions.base.minions.minion.layout.MinionLayout;
import com.qualityplus.minions.util.MinionAnimationUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public final class LayoutHandlerImpl implements LayoutHandler, LayoutGetter {
    private final MinionEntity minionEntity;
    private final Minion minion;

    @Override
    public Boolean hasInvalidLayout(ArmorStandHandler handler) {
        MinionLayout minionLayout = minion.getMinionLayout();

        List<XMaterial> noException = minionLayout.getAllMaterialsCauseExceptionExcept();
        List<XMaterial> exceptions = minionLayout.getMaterialThatCauseException();

        List<Vector> vectors = getMinionLayout(minion).equals(LayoutType.THREE_X_THREE) ? MinionAnimationUtil.getThree() : MinionAnimationUtil.getSecond();

        if(!minionEntity.getState().isLoaded()) return false;

        Location location = Optional.ofNullable(handler)
                .map(ArmorStandHandler::getLocation)
                .filter(Objects::nonNull)
                .map(e -> e.subtract(new Vector(0, 1, 0)))
                .orElse(null);

        if(location == null) return false;

        for (Vector vector : vectors) {
            Location newLocation = location.clone().add(vector);

            if(!minionEntity.getState().isLoaded()) return false;
            //if(!newLocation.getChunk().isLoaded()) return false;

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
        return minionEntity.getMinionUniqueId();
    }
}