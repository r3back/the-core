package com.qualityplus.minions.base.handler;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.minions.TheMinions;
import com.qualityplus.minions.api.handler.ArmorStandHandler;
import com.qualityplus.minions.api.handler.LayoutHandler;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.base.minions.entity.getter.DataGetter;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.layout.LayoutType;
import com.qualityplus.minions.base.minions.minion.layout.MinionLayout;
import com.qualityplus.minions.util.MinionAnimationUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public final class LayoutHandlerImpl implements LayoutHandler, DataGetter {
    private final MinionEntity minionEntity;
    private final Minion minion;

    @Override
    public Boolean hasInvalidLayout(ArmorStandHandler handler) {
        return false;
    }

    @Override
    public UUID getMinionUniqueId() {
        return minionEntity.getMinionUniqueId();
    }
}