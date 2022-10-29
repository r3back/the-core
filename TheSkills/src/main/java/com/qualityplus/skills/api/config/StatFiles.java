package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

public interface StatFiles<CC, CD, D, F, I, M, MI, P, S, ST> extends ConfigReloader {
    CC critChance();
    CD critDamage();
    D defense();
    F ferocity();
    I intelligence();
    M magicFind();
    MI mining();
    P petLuck();
    S speed();
    ST strength();
}
