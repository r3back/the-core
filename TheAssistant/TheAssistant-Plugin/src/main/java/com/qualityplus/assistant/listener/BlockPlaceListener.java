package com.qualityplus.assistant.listener;

import com.qualityplus.assistant.TheAssistantPlugin;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

@Component
public final class BlockPlaceListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e){
        e.getBlock().setMetadata("theAssistantPlayerBlock", new FixedMetadataValue(TheAssistantPlugin.getAPI().getPlugin(), "theAssistantPlayerBlock"));
    }
}
