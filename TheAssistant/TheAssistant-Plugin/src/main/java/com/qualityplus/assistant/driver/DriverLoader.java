package com.qualityplus.assistant.driver;

import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.jdbc.H2Persistence;
import eu.okaeri.persistence.jdbc.MariaDbPersistence;
import eu.okaeri.persistence.mongo.MongoPersistence;
import eu.okaeri.persistence.redis.RedisPersistence;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

@Component
public final class DriverLoader {
    @Delayed(time = MinecraftTimeEquivalent.SECOND / 20, async = true)
    public void configureDrivers(@Inject Plugin plugin, @Inject Logger logger) {
        try {
            MariaDbPersistence.class.getName();
            H2Persistence.class.getName();
            MongoPersistence.class.getName();
            RedisPersistence.class.getName();

            logger.info("Successfully loaded Database Drivers");
        } catch (Exception ignored) {
            logger.info("Fail while loading Database Drivers!");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
