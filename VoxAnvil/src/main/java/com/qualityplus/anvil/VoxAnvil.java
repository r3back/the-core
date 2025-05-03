package com.qualityplus.anvil;

import com.qualityplus.anvil.api.VoxAnvilAPI;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import lombok.Getter;

@Scan(deep = true)
public final class VoxAnvil extends OkaeriSilentPlugin {
    private static @Inject @Getter VoxAnvilAPI api;
}
