package com.qualityplus.auction.api.service;

import com.qualityplus.auction.persistence.data.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for users
 */
public interface UserService {
    /**
     * Adds an uuid
     *
     * @param uuid {@link UUID}
     * @return Optional of {@link User}
     */
    public Optional<User> getUser(UUID uuid);

    /**
     * Adds user
     *
     * @param user {@link User}
     */
    public void addUser(User user);

    /**
     * Remove User
     *
     * @param user {@link User}
     */
    public void removeUser(User user);
}
