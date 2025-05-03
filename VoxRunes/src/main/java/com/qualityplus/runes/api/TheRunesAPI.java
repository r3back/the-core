package com.qualityplus.runes.api;

import com.qualityplus.runes.api.box.Box;
import com.qualityplus.runes.api.provider.LevelProvider;

public interface TheRunesAPI {
    LevelProvider getLevelProvider();

    Box getBox();
}
