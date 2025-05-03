package com.qualityplus.auction.base.service;

import com.qualityplus.auction.api.service.UserService;
import com.qualityplus.auction.persistence.data.User;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for service user
 */
@Component
public final class UserServiceImpl implements UserService {
    private final Map<UUID, User> userMap = new HashMap<>();

    @Override
    public Optional<User> getUser(final UUID uuid) {
        return Optional.ofNullable(this.userMap.getOrDefault(uuid, null));
    }

    @Override
    public void addUser(final User user) {
        this.userMap.put(user.getUuid(), user);
    }

    @Override
    public void removeUser(final User user) {
        this.userMap.remove(user.getUuid());
    }
}
