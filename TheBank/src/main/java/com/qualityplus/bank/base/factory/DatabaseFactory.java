package com.qualityplus.bank.base.factory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.qualityplus.assistant.api.database.HikariDatabaseHelper;
import com.qualityplus.assistant.api.database.UriGetter;
import com.qualityplus.bank.base.config.Config;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.config.DatabaseType;
import com.zaxxer.hikari.HikariConfig;
import eu.okaeri.configs.json.simple.JsonSimpleConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.PersistencePath;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.jdbc.H2Persistence;
import eu.okaeri.persistence.jdbc.JdbcPersistence;
import eu.okaeri.persistence.jdbc.MariaDbPersistence;
import eu.okaeri.persistence.mongo.MongoPersistence;
import eu.okaeri.persistence.redis.RedisPersistence;
import eu.okaeri.platform.bukkit.persistence.YamlBukkitPersistence;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public final class DatabaseFactory extends HikariDatabaseHelper {
    @Bean(value = "persistence", preload = true)
    public DocumentPersistence configurePersistence(@Inject("dataFolder") File dataFolder, @Inject Config config) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Class.forName("org.h2.Driver");
            Class.forName("com.mysql");
        } catch (Exception ignored) {}

        PersistencePath basePath = PersistencePath.of("bankDb");

        ConfigDatabase db = config.configDatabase;

        DatabaseType backend = db.type;

        switch (backend) {
            case FLAT:
                return YamlBukkitPersistence.of(new File(dataFolder, "storage"));
            case MARIADB:
                return new DocumentPersistence(new MariaDbPersistence(basePath, getMariaDBHikari(db)), JsonSimpleConfigurer::new, new SerdesBukkit());
            case MYSQL:
                return new DocumentPersistence(new JdbcPersistence(basePath, getMysqlHikari(db)), JsonSimpleConfigurer::new, new SerdesBukkit());
            case H2:
                return new DocumentPersistence(new H2Persistence(basePath, getH2Hikari("TheBank")), JsonSimpleConfigurer::new, new SerdesBukkit());
            case REDIS:
                return new DocumentPersistence(new RedisPersistence(basePath, RedisClient.create(RedisURI.create(getUri(db)))), JsonSimpleConfigurer::new, new SerdesBukkit());
            case MONGODB:
                MongoClientURI clientURI = new MongoClientURI(getUri(db));

                MongoClient mongoClient = new MongoClient(clientURI);

                if (clientURI.getDatabase() == null)
                    throw new IllegalArgumentException("Mongo URI needs to specify the database");

                return new DocumentPersistence(new MongoPersistence(basePath, mongoClient, clientURI.getDatabase()), JsonSimpleConfigurer::new, new SerdesBukkit());
            default:
                throw new RuntimeException("unsupported storage backend: " + backend);
        }
    }
}
