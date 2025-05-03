package com.qualityplus.pets.base.skills;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.pets.api.skills.DependencyHandler;
import com.qualityplus.pets.api.skills.SkillDependency;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.Getter;

@Component
public final class DependencyHandlerImpl implements DependencyHandler {
    private @Inject
    @Getter SkillDependency skills;
}
