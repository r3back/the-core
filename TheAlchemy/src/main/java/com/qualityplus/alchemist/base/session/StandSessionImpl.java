package com.qualityplus.alchemist.base.session;

import com.qualityplus.alchemist.api.session.StandSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Implementation of {@link StandSession}
 */
@Data
@Builder
@AllArgsConstructor
public final class StandSessionImpl implements StandSession {
    private final Location location;
    private final UUID player;
}
