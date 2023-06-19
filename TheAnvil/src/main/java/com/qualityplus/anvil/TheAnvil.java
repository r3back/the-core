package com.qualityplus.anvil;

import com.qualityplus.anvil.api.TheAnvilAPI;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Scan;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import lombok.Getter;

@Scan(deep = true)
public final class TheAnvil extends OkaeriSilentPlugin {
    private static @Inject
    @Getter TheAnvilAPI api;
}
