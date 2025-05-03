package com.qualityplus.dragon.api;

import com.qualityplus.dragon.api.service.*;
import org.bukkit.plugin.Plugin;

public interface VoxDragonAPI {
    GamePlayerCheckService getGamePlayerCheckService();
    GuardianEditService getGuardianEditService();
    StructureService getStructureService();
    GuardianService getGuardianService();
    BossBarService getBossBarService();
    DragonService getDragonService();
    GameService getGameService();
    UserService getUserService();
    Plugin getPlugin();
}
