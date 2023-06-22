package com.qualityplus.dragon.base;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.dragon.api.TheDragonAPI;
import com.qualityplus.dragon.api.service.*;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;

@Getter
@Component
public final class TheDragonAPIImpl implements TheDragonAPI {
    private @Inject GamePlayerCheckService gamePlayerCheckService;
    private @Inject GuardianEditService guardianEditService;
    private @Inject StructureService structureService;
    private @Inject GuardianService guardianService;
    private @Inject BossBarService bossBarService;
    private @Inject DragonService dragonService;
    private @Inject GameService gameService;
    private @Inject UserService userService;
    private @Inject Plugin plugin;
}
