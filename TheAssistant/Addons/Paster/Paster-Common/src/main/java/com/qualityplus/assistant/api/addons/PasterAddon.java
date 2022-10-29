package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.addons.paster.helper.WEHelper;
import com.qualityplus.assistant.api.addons.paster.schematic.Schematic;
import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.Location;

import java.util.concurrent.CompletableFuture;

public interface PasterAddon extends DependencyPlugin, WEHelper {
    CompletableFuture<PasterSession> pasteSchematic(Location location, Schematic schematic);

    boolean isAsync();
}
