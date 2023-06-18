package com.qualityplus.anvil.base.requirement

import eu.okaeri.configs.OkaeriConfig
import lombok.*

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class VanillaEnchantRequirement : OkaeriConfig() {
    private val requiredLevelsToEnchant: Map<Int, Int>? = null
}