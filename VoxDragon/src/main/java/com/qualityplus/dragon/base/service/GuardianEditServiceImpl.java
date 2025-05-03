package com.qualityplus.dragon.base.service;

import com.qualityplus.dragon.api.service.GuardianEditService;
import com.qualityplus.dragon.base.editmode.GuardianEditMode;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the Editor Mode Data
 */
@Component
public final class GuardianEditServiceImpl implements GuardianEditService {
    private final Map<UUID, GuardianEditMode> editSettings = new HashMap<>();

    @Override
    public void setInEditMode(GuardianEditMode editMode) {
        editSettings.put(editMode.getUuid(), editMode);
    }

    @Override
    public GuardianEditMode getByPlayer(UUID uuid) {
        return editSettings.getOrDefault(uuid, null);
    }

    @Override
    public void removeByPlayer(UUID uuid) {
        editSettings.remove(uuid);
    }


    public @AllArgsConstructor enum EditType{
        HEALTH("health"),
        MOB("mob type"),
        DISPLAYNAME("displayname");

        private @Getter final String name;
    }
}
