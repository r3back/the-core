package com.qualityplus.dragon.api.service;

import com.qualityplus.dragon.base.editmode.GuardianEditMode;

import javax.annotation.Nullable;
import java.util.UUID;

public interface GuardianEditService {
    void setInEditMode(GuardianEditMode editMode);

    @Nullable
    GuardianEditMode getByPlayer(UUID uuid);

    void removeByPlayer(UUID uuid);
}
