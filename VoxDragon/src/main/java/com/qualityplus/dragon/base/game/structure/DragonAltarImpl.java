package com.qualityplus.dragon.base.game.structure;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.dragon.api.game.structure.type.DragonAltar;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

@Getter
@AllArgsConstructor
public final class DragonAltarImpl extends OkaeriConfig implements DragonAltar {
    private final Location location;
    private boolean enderKey;
    @Getter
    private final String worldName;

    public DragonAltarImpl(final Location location, final boolean enderKey) {
        this.location = location;
        this.enderKey = enderKey;
        this.worldName = location.getWorld().getName();
    }

    @Override
    public void removeStructure() {
        getLocation().getBlock().setType(Material.AIR);
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }

    @Override
    public void setEnderKey(boolean enderKey) {
        this.enderKey = enderKey;

        Block block = getLocation().getBlock();

        if (BlockUtils.isNull(block)) return;

        TheAssistantPlugin.getAPI().getNms().setEnderEye(block, enderKey);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), location.getX(), location.getY(), location.getZ());
    }
}
