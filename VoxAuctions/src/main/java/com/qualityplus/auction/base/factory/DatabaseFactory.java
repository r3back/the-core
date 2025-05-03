package com.qualityplus.auction.base.factory;
import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.qualityplus.assistant.api.config.DatabaseType;
import com.qualityplus.assistant.api.database.HikariConfiguration;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.base.config.Config;
import com.qualityplus.assistant.lib.eu.okaeri.configs.json.simple.JsonSimpleConfigurer;
import com.qualityplus.assistant.lib.eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.PersistencePath;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.DocumentPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.jdbc.H2Persistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.jdbc.JdbcPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.jdbc.MariaDbPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.redis.RedisPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.persistence.YamlBukkitPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Bean;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import java.io.File;

/**
 * Utility class for database factory
 */
@Component
public final class DatabaseFactory extends HikariConfiguration {
    /**
     * Make an document persistence
     *
     * @param dataFolder {@link File}
     * @param config     {@link Config}
     * @return           {@link DocumentPersistence}
     */
    @Bean(value = "persistence", preload = true)
    public DocumentPersistence configurePersistence(final @Inject("dataFolder") File dataFolder, final @Inject Config config) {
        try {
            Class.forName("com.qualityplus.assistant.lib.org.mariadb.jdbc.Driver");
            Class.forName("com.qualityplus.assistant.lib.org.h2.Driver");
            Class.forName("com.qualityplus.assistant.lib.com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ignored) { }

        final PersistencePath basePath = PersistencePath.of("auctionDb");

        final ConfigDatabase db = config.getConfigDatabase();

        final DatabaseType backend = db.getType();

        switch (backend) {
            case FLAT:
                return YamlBukkitPersistence.of(new File(dataFolder, "storage"));
            case MARIADB:
                return new DocumentPersistence(new MariaDbPersistence(basePath, getMariaDBHikari(db)), JsonSimpleConfigurer::new, new SerdesBukkit());
            case MYSQL:
                return new DocumentPersistence(new JdbcPersistence(basePath, getMysqlHikari(db)), JsonSimpleConfigurer::new, new SerdesBukkit());
            case H2:
                return new DocumentPersistence(new H2Persistence(basePath, getH2Hikari("VoxAuctions")), JsonSimpleConfigurer::new, new SerdesBukkit());
            case REDIS:
                return new DocumentPersistence(new RedisPersistence(basePath, RedisClient.
                        create(RedisURI.create(parseDatabaseUrl(db)))), JsonSimpleConfigurer::new, new SerdesBukkit());
            /*case MONGODB:
                MongoClientURI clientURI = new MongoClientURI(getUri(db));

                MongoClient mongoClient = new MongoClient(clientURI);

                if (clientURI.getDatabase() == null)
                    throw new IllegalArgumentException("Mongo URI needs to specify the database");

                return new DocumentPersistence(new MongoPersistence(basePath, mongoClient,
                clientURI.getDatabase()), JsonSimpleConfigurer::new, new SerdesBukkit());
            */default:
                throw new RuntimeException("unsupported storage backend: " + backend);
        }
    }
}
