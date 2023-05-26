package com.qualityplus.skills.base.skill.skills.blockbreak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class BlockBreakResponse {
    private final double xp;
}