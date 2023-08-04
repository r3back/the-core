package com.qualityplus.auction.api.box;

import com.qualityplus.auction.api.config.ConfigFiles;
import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.api.service.UserService;
import com.qualityplus.auction.base.config.*;
import com.qualityplus.auction.persistence.UserRepository;
import com.qualityplus.auction.persistence.data.User;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.UUID;

public interface Box {
    ConfigFiles<Config, Inventories, Messages, Commands, Categories> files();
    AuctionService auctionService();
    UserRepository repository();
    UserService service();
    Plugin plugin();

    Optional<User> getCacheOrDatabase(UUID uuid);
}
