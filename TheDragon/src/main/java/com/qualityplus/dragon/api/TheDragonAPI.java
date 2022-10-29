package com.qualityplus.dragon.api;

import com.qualityplus.dragon.api.service.*;
import org.bukkit.plugin.Plugin;

public interface TheDragonAPI {
    GuardianEditService getGuardianEditService();
    StructureService getStructureService();
    GuardianService getGuardianService();
    DragonService getDragonService();
    GameService getGameService();
    UserService getUserService();
    Plugin getPlugin();
}
