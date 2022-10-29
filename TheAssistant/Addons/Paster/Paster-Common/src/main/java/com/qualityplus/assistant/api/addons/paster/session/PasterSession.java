package com.qualityplus.assistant.api.addons.paster.session;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.List;

public interface PasterSession {
    void undo();

    void setAllBlocks(Material material);

    List<Block> getAllBlocks();

    boolean isInside(Location location);
}