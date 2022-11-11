package com.qualityplus.pets.base.pet.egg;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class PetModelEngine extends OkaeriConfig {
    private String modelId;
    private boolean useModelEngine;
    private String modelEngineAnimation;
}
