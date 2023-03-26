package com.qualityplus.anvil.base.requirement;

import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VanillaEnchantRequirement extends OkaeriConfig {
    private Map<Integer, Integer> requiredLevelsToEnchant;
}
