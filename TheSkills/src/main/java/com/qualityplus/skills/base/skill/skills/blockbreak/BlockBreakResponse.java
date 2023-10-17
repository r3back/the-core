package com.qualityplus.skills.base.skill.skills.blockbreak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Utility class for block break response
 */
@Data
@Builder
@AllArgsConstructor
public final class BlockBreakResponse {
    private final double xp;
}
