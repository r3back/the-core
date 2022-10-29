package com.qualityplus.assistant.base.addons.paster;

import com.qualityplus.assistant.api.addons.paster.schematic.Schematic;
import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.api.addons.PasterAddon;
import org.bukkit.Location;

import java.util.concurrent.CompletableFuture;

public final class DefaultPasterAddon implements PasterAddon {
    @Override
    public String getAddonName() {
        return null;
    }

    @Override
    public CompletableFuture<PasterSession> pasteSchematic(Location location, Schematic schematic) {
        return new CompletableFuture<>();
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
