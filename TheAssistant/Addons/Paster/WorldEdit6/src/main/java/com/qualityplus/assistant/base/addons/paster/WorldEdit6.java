package com.qualityplus.assistant.base.addons.paster;

import com.qualityplus.assistant.api.addons.PasterAddon;
import com.qualityplus.assistant.api.addons.paster.cuboid.Cuboid;
import com.qualityplus.assistant.api.addons.paster.schematic.Schematic;
import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.paster.session.WorldEditSession6;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.scheduler.PlatformScheduler;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.CompletableFuture;

public final class WorldEdit6 implements PasterAddon {
    private @Inject("scheduler") PlatformScheduler scheduler;
    private @Inject DependencyResolver resolver;

    @Override
    public CompletableFuture<PasterSession> pasteSchematic(Location location, Schematic schematic) {
        CompletableFuture<PasterSession> future = new CompletableFuture<>();

        Runnable pasteTask = () -> {
            try {
                BukkitWorld world = new BukkitWorld(location.getWorld());
                CuboidClipboard cuboidClipboard = CuboidClipboard.loadSchematic(schematic.getFile());
                Vector vector = new Vector(location.getX(), location.getY(), location.getZ());
                EditSession editSession = new EditSession(world, 999999999);
                cuboidClipboard.paste(editSession, vector, true);
                future.complete(new WorldEditSession6(editSession, getCuboid(location, cuboidClipboard)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        if (isAsync())
            scheduler.runAsync(pasteTask);
        else
            scheduler.runSync(pasteTask);

        return future;
    }

    private Cuboid getCuboid(Location baseLoc, CuboidClipboard cc) {
        int height = cc.getHeight();
        int width = cc.getWidth();
        int length = cc.getLength();
        Location endLoc = baseLoc.clone().add(width, height, length);
        int minX = Math.min(baseLoc.getBlockX(), endLoc.getBlockX());
        int minY = Math.min(baseLoc.getBlockY(), endLoc.getBlockY());
        int minZ = Math.min(baseLoc.getBlockZ(), endLoc.getBlockZ());
        int maxX = Math.max(baseLoc.getBlockX(), endLoc.getBlockX());
        int maxY = Math.max(baseLoc.getBlockY(), endLoc.getBlockY());
        int maxZ = Math.max(baseLoc.getBlockZ(), endLoc.getBlockZ());
        World world = baseLoc.getWorld();
        Vector vector = cc.getOffset();
        Location minLocation = (new Location(world, minX, minY, minZ)).add(vector.getX(), vector.getY(), vector.getZ());
        Location maxLocation = (new Location(world, maxX, maxY, maxZ)).add(vector.getX(), vector.getY(), vector.getZ());
        return new Cuboid(maxLocation, minLocation);
    }

    @Override
    public boolean isAsync() {
        return isAsync(resolver);
    }

    @Override
    public String getAddonName() {
        return "World Edit 6";
    }
}
