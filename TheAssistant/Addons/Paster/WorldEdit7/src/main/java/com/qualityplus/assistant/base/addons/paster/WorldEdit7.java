package com.qualityplus.assistant.base.addons.paster;

import com.qualityplus.assistant.api.addons.PasterAddon;
import com.qualityplus.assistant.api.addons.paster.cuboid.Cuboid;
import com.qualityplus.assistant.api.addons.paster.schematic.Schematic;
import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.assistant.api.dependency.resolver.DependencyResolver;
import com.qualityplus.assistant.base.addons.paster.session.WorldEditSession7;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.scheduler.PlatformScheduler;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class WorldEdit7 implements PasterAddon {
    private @Inject("scheduler") PlatformScheduler scheduler;
    private @Inject DependencyResolver resolver;

    @Override
    public CompletableFuture<PasterSession> pasteSchematic(Location location, Schematic schematic) {
        CompletableFuture<PasterSession> future = new CompletableFuture<>();
        Runnable pasteTask = () -> {
            File file = schematic.getFile();
            ClipboardFormat format = ClipboardFormats.findByFile(file);
            try {
                ClipboardReader reader = format.getReader(Files.newInputStream(file.toPath()));

                Clipboard clipboard = reader.read();

                EditSession editSession = WorldEdit.getInstance()
                        .getEditSessionFactory()
                        .getEditSession(new BukkitWorld(location.getWorld()), -1);

                Operation operation = new ClipboardHolder(clipboard)
                                            .createPaste(editSession)
                                            .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                                            .ignoreAirBlocks(true)
                                            .build();
                Operations.complete(operation);

                future.complete(new WorldEditSession7(editSession, getCuboid(location, clipboard)));

                Optional.ofNullable(editSession).ifPresent(EditSession::close);

                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WorldEditException e) {
                throw new RuntimeException(e);
            }
            //future.complete(new DefaultSession());
        };
        if (isAsync())
            this.scheduler.runAsync(pasteTask);
        else
            scheduler.runSync(pasteTask);

        return future;
    }


    private Cuboid getCuboid(Location baseLoc, Clipboard cc) {
        try {
            org.bukkit.World world = baseLoc.getWorld();
            //BlockVector3 to = session.getPlacementPosition(actor);
            BlockVector3 to = BlockVector3.at(baseLoc.getX(), baseLoc.getY(), baseLoc.getZ());

            Region region = cc.getRegion();
            ClipboardHolder holder = new ClipboardHolder(cc);
            BlockVector3 clipboardOffset = cc.getRegion().getMinimumPoint().subtract(cc.getOrigin());
            Vector3 realTo = to.toVector3().add(holder.getTransform().apply(clipboardOffset.toVector3()));
            Vector3 max = realTo.add(holder.getTransform().apply(region.getMaximumPoint().subtract(region.getMinimumPoint()).toVector3()));
            Location maxLocation = new Location(world, realTo.toBlockPoint().getX(), realTo.toBlockPoint().getY(), realTo.toBlockPoint().getZ());
            Location minLocation = new Location(world, max.toBlockPoint().getX(), max.toBlockPoint().getY(), max.toBlockPoint().getZ());
            return new Cuboid(minLocation, maxLocation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isAsync() {
        return isAsync(resolver);
    }

    @Override
    public String getAddonName() {
        return "World Edit 7";
    }
}
