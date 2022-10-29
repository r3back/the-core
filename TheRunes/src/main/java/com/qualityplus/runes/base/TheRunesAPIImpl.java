package com.qualityplus.runes.base;

import com.qualityplus.runes.api.TheRunesAPI;
import com.qualityplus.runes.api.box.Box;
import com.qualityplus.runes.api.provider.LevelProvider;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class TheRunesAPIImpl implements TheRunesAPI {
    private @Inject @Getter LevelProvider levelProvider;
    private @Inject @Getter Box box;
}
