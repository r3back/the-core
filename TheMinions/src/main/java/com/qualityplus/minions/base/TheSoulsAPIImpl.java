package com.qualityplus.minions.base;

import com.qualityplus.minions.api.TheSoulsAPI;
import com.qualityplus.minions.api.service.MinionsService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
@Getter
public final class TheSoulsAPIImpl implements TheSoulsAPI {
    private @Inject MinionsService minionsService;
}
