package com.qualityplus.skills.api.provider;

import com.qualityplus.skills.api.listener.ExtraListener;

import java.util.Optional;

/**
 * Makes a minions provider
 */
public interface MinionsProvider {
    /**
     * Adds a class
     *
     * @return {@link ExtraListener}
     */
    public Optional<Class<? extends ExtraListener>> getExtraListener();
}
