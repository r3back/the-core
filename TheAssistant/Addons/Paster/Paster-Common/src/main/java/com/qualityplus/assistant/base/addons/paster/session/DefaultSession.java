package com.qualityplus.assistant.base.addons.paster.session;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public final class DefaultSession implements PasterSession {
    @Override
    public void undo() {

    }

    @Override
    public void setAllBlocks(Material material) {

    }

    @Override
    public List<Block> getAllBlocks() {
        return Collections.emptyList();
    }

    @Override
    public boolean isInside(Location location) {
        return false;
    }
}
