package com.qualityplus.auction.api.service;

import com.qualityplus.auction.persistence.data.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUser(UUID uuid);

    void addUser(User user);

    void removeUser(User user);
}
