package com.qualityplus.anvil.base;

import com.qualityplus.anvil.api.TheAnvilAPI;
import com.qualityplus.anvil.api.provider.EnchantmentProvider;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class TheAnvilAPIImpl implements TheAnvilAPI {
    private @Inject @Getter EnchantmentProvider provider;
}
