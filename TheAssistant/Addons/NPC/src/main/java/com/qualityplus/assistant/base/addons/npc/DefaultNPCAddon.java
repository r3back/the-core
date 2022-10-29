package com.qualityplus.assistant.base.addons.npc;

import com.qualityplus.assistant.api.addons.NPCAddon;
import org.bukkit.entity.Entity;

public final class DefaultNPCAddon implements NPCAddon {
    @Override
    public String getAddonName() {
        return null;
    }

    @Override
    public boolean isNPC(Entity entity) {
        return false;
    }
}
