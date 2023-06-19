package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import java.util.List;

public interface SkillFiles <A, CA, C, D, E, F, FH, FG, M, R, T, DU> extends ConfigReloader {
    A alchemy();
    CA carpentry();
    C combat();
    D discoverer();
    DU dungeoneering();
    E enchanting();
    F farming();
    FH fishing();
    FG foraging();
    M mining();
    R runeCrafting();
    T taming();

    List<OkaeriConfig> getAll();
}
