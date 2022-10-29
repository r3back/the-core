package com.qualityplus.dragon.api.service;

import com.qualityplus.dragon.persistance.data.UserData;

import java.util.Optional;
import java.util.UUID;

public interface UserDBService {
    Optional<UserData> getDragonData(UUID uuid);

    void addData(UserData data);

    void removeData(UserData data);
}
