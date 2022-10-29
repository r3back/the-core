package com.qualityplus.runes.base.session;

import com.qualityplus.runes.api.session.RuneInstance;
import com.qualityplus.runes.base.rune.Rune;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class RuneInstanceImpl implements RuneInstance {
    private final Rune rune;
    private final int level;
}
