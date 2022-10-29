package com.qualityplus.assistant.base.addons.npc;

import com.qualityplus.assistant.api.addons.NPCAddon;
import org.bukkit.entity.Entity;

public final class CitizensAddon implements NPCAddon {
    @Override
    public String getAddonName() {
        return "Citizens";
    }

    @Override
    public boolean isNPC(Entity entity){
        return entity.hasMetadata("NPC");
    }
}
