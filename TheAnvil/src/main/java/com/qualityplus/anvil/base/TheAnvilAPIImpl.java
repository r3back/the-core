package com.qualityplus.anvil.base;

import com.qualityplus.anvil.api.TheAnvilAPI;
import com.qualityplus.anvil.api.provider.EnchantmentProvider;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class TheAnvilAPIImpl implements TheAnvilAPI {
    private @Inject
    @Getter EnchantmentProvider provider;
}
