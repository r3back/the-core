package com.qualityplus.souls.api.service;

import com.qualityplus.souls.persistance.data.SoulsData;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface SoulsService {
    Optional<SoulsData> getData(UUID uuid);

    void addData(SoulsData data);

    void removeData(SoulsData data);
}
