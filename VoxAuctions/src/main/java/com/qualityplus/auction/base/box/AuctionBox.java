package com.qualityplus.auction.base.box;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.api.config.ConfigFiles;
import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.api.service.UserService;
import com.qualityplus.auction.base.config.Categories;
import com.qualityplus.auction.base.config.Commands;
import com.qualityplus.auction.base.config.Config;
import com.qualityplus.auction.base.config.Inventories;
import com.qualityplus.auction.base.config.Messages;
import com.qualityplus.auction.persistence.UserRepository;
import com.qualityplus.auction.persistence.data.User;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for auction box
 */
@Component
public final class AuctionBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, Categories> files;
    private @Inject AuctionService auctionService;
    private @Inject UserRepository repository;
    private @Inject UserService service;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands, Categories> files() {
        return this.files;
    }

    @Override
    public AuctionService auctionService() {
        return this.auctionService;
    }

    @Override
    public UserRepository repository() {
        return this.repository;
    }

    @Override
    public UserService service() {
        return this.service;
    }

    @Override
    public Plugin plugin() {
        return this.plugin;
    }

    @Override
    public Optional<User> getCacheOrDatabase(final UUID owner) {
        return Optional.ofNullable(this.service.getUser(owner).orElse(this.repository.get(owner)));
    }
}
