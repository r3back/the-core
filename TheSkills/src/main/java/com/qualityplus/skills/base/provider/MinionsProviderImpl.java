package com.qualityplus.skills.base.provider;

import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.api.provider.MinionsProvider;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

import java.util.Optional;

@Component
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class MinionsProviderImpl implements MinionsProvider {
    private @Inject("minionListener") Optional<Class<? extends ExtraListener>> injected;

    @Override
    public Optional<Class<? extends ExtraListener>> getExtraListener() {
        return injected;
    }
}
