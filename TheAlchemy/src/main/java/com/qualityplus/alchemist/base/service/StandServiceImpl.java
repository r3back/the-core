package com.qualityplus.alchemist.base.service;

import com.qualityplus.alchemist.api.service.StandService;
import com.qualityplus.alchemist.api.session.StandSession;
import com.qualityplus.alchemist.base.session.StandSessionImpl;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link StandService}
 */
@Component
public final class StandServiceImpl implements StandService {
    private final List<StandSession> openedStands = new ArrayList<>();

    @Override
    public void addSession(final UUID uuid, final Location location) {
        this.openedStands.add(new StandSessionImpl(location, uuid));
    }

    @Override
    public void removeSession(final Location location) {
        getSession(location).ifPresent(this.openedStands::remove);
    }

    @Override
    public Optional<StandSession> getSession(final Location location) {
        return this.openedStands.stream()
                .filter(loc -> loc.getLocation().equals(location))
                .findFirst();
    }
}
