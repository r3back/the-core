package com.qualityplus.pets.persistance.data;

import com.qualityplus.assistant.api.common.data.LevellableInteger;
import com.qualityplus.assistant.api.common.data.ProgressableData;
import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public final class PetsData extends Document implements LevellableInteger<UUID>, ProgressableData<UUID> {
    private Map<UUID, Integer> level = new HashMap<>();
    private Map<UUID, Double> xp = new HashMap<>();
}
