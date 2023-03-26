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

@Component
public final class StandServiceImpl implements StandService {
    private final List<StandSession> openedStands = new ArrayList<>();

    @Override
    public void addSession(UUID uuid, Location location) {
        openedStands.add(new StandSessionImpl(location, uuid));
    }

    @Override
    public void removeSession(Location location) {
        getSession(location).ifPresent(openedStands::remove);
    }

    @Override
    public Optional<StandSession> getSession(Location location) {
        return openedStands.stream()
                .filter(loc -> loc.getLocation().equals(location))
                .findFirst();
    }
}
