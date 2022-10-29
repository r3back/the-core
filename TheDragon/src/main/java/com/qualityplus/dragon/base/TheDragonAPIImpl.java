package com.qualityplus.dragon.base;

import com.qualityplus.dragon.api.TheDragonAPI;
import com.qualityplus.dragon.api.service.*;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

@Component
public final class TheDragonAPIImpl implements TheDragonAPI {
    private @Inject @Getter GuardianEditService guardianEditService;
    private @Inject @Getter StructureService structureService;
    private @Inject @Getter GuardianService guardianService;
    private @Inject @Getter DragonService dragonService;
    private @Inject @Getter GameService gameService;
    private @Inject @Getter UserService userService;
    private @Inject @Getter Plugin plugin;
}
