package com.qualityplus.auction.base.box;

import com.qualityplus.auction.api.box.Box;
import com.qualityplus.auction.api.config.ConfigFiles;
import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.api.service.UserService;
import com.qualityplus.auction.base.config.*;
import com.qualityplus.auction.persistence.UserRepository;
import com.qualityplus.auction.persistence.data.User;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.UUID;

@Component
public final class AuctionBox implements Box {
    private @Inject ConfigFiles<Config, Inventories, Messages, Commands, Categories> files;
    private @Inject AuctionService auctionService;
    private @Inject UserRepository repository;
    private @Inject UserService service;
    private @Inject Plugin plugin;

    @Override
    public ConfigFiles<Config, Inventories, Messages, Commands, Categories> files() {
        return files;
    }

    @Override
    public AuctionService auctionService() {
        return auctionService;
    }

    @Override
    public UserRepository repository() {
        return repository;
    }

    @Override
    public UserService service() {
        return service;
    }

    @Override
    public Plugin plugin() {
        return plugin;
    }

    @Override
    public Optional<User> getCacheOrDatabase(UUID owner) {
        return Optional.ofNullable(service.getUser(owner).orElse(repository.get(owner)));
    }
}
