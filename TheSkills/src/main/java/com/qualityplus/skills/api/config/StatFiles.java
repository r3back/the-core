package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface StatFiles<CC, CD, D, F, I, M, P, S, ST> extends ConfigReloader, AllGetter {
    CC critChance();
    CD critDamage();
    D defense();
    F ferocity();
    I intelligence();
    M magicFind();
    P petLuck();
    S speed();
    ST strength();
}
