package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.factory.GuardianFactory;
import com.qualityplus.dragon.api.service.GuardianService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public class GuardianServiceImpl implements GuardianService {
    private @Inject @Getter GuardianFactory factory;
}
