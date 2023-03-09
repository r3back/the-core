package com.qualityplus.assistant.api.addons.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ChunkCheckResponse {
    private boolean areLoaded;
    private boolean canBeLoaded;
}
