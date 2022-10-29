package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.dependency.DependencyPlugin;
import org.bukkit.entity.Entity;

public interface NPCAddon extends DependencyPlugin {
    boolean isNPC(Entity entity);
}
