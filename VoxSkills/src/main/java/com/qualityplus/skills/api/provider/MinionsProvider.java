package com.qualityplus.skills.api.provider;

import com.qualityplus.skills.api.listener.ExtraListener;

import java.util.Optional;

public interface MinionsProvider {
    Optional<Class<? extends ExtraListener>> getExtraListener();
}
