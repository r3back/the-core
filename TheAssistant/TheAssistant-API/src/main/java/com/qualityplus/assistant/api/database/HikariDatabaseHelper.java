package com.qualityplus.assistant.api.database;

import com.qualityplus.assistant.api.config.ConfigDatabase;
import com.zaxxer.hikari.HikariConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class HikariDatabaseHelper implements UriGetter{
    private static final String H2_URI = "jdbc:h2:file:./plugins/%s/storage/storage;MODE=MYSQL;DATABASE_TO_LOWER=TRUE";

    protected HikariConfig getMysqlHikari(ConfigDatabase db){
        HikariConfig mysqlHikari = new HikariConfig();

        mysqlHikari.setDriverClassName("com.mysql.cj.jdbc.Driver");

        mysqlHikari.setJdbcUrl("jdbc:mysql://" + db.host + ":" + db.port + "/" + db.database + "?useSSL=" +db.useSsl);

        mysqlHikari.setUsername(db.userName);
        mysqlHikari.setPassword(db.passWord);

        Map<String, String> properties = new HashMap<>();
        properties.putIfAbsent("cachePrepStmts", "true");
        properties.putIfAbsent("prepStmtCacheSize", "250");
        properties.putIfAbsent("prepStmtCacheSqlLimit", "2048");
        properties.putIfAbsent("useServerPrepStmts", "true");
        properties.putIfAbsent("useLocalSessionState", "true");
        properties.putIfAbsent("rewriteBatchedStatements", "true");
        properties.putIfAbsent("cacheResultSetMetadata", "true");
        properties.putIfAbsent("cacheServerConfiguration", "true");
        properties.putIfAbsent("elideSetAutoCommits", "true");
        properties.putIfAbsent("maintainTimeStats", "false");
        properties.putIfAbsent("alwaysSendSetIsolation", "false");
        properties.putIfAbsent("cacheCallableStmts", "true");
        for (Map.Entry<String, String> property : properties.entrySet())
            mysqlHikari.addDataSourceProperty(property.getKey(), property.getValue());
        return mysqlHikari;
    }

    protected HikariConfig getMariaDBHikari(ConfigDatabase db){
        HikariConfig mariadbHikari = new HikariConfig();

        mariadbHikari.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
        mariadbHikari.addDataSourceProperty("serverName", db.host);
        mariadbHikari.addDataSourceProperty("port", db.port);
        mariadbHikari.addDataSourceProperty("databaseName", db.database);
        mariadbHikari.setUsername(db.userName);
        mariadbHikari.setPassword(db.passWord);

        return mariadbHikari;
    }

    protected HikariConfig getH2Hikari(String name){
        HikariConfig jdbcHikari = new HikariConfig();

        jdbcHikari.setJdbcUrl(String.format(H2_URI, name));

        return jdbcHikari;
    }
}
