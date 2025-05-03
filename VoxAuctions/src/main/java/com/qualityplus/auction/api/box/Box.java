package com.qualityplus.auction.api.box;

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
import org.bukkit.plugin.Plugin;


import java.util.Optional;
import java.util.UUID;

/**
 * Utility for box
 */
public interface Box {
    /**
     * Makes a config files
     *
     * @return {@link ConfigFiles}
     */
    public ConfigFiles<Config, Inventories, Messages, Commands, Categories> files();

    /**
     * Makes auctions services
     *
     * @return {@link AuctionService}
     */
    public AuctionService auctionService();

    /**
     * Makes a user repositories
     *
     * @return {@link UserRepository}
     */
    public UserRepository repository();

    /**
     * Makes a user services
     *
     * @return {@link UserService}
     */
    public UserService service();

    /**
     * Makes a plugin
     *
     * @return {@link Plugin}
     */
    public Plugin plugin();

    /**
     * Makes user database
     *
     * @param uuid {@link UUID}
     * @return     {@link User}
     */
    public Optional<User> getCacheOrDatabase(UUID uuid);
}
