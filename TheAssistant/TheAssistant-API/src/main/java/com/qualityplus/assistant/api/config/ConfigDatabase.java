package com.qualityplus.assistant.api.config;


import eu.okaeri.configs.OkaeriConfig;

public final class ConfigDatabase extends OkaeriConfig {
    public String host = "localhost";
    public String database = "database_name";
    public String userName = "root";
    public String passWord = "123456";
    public int port = 8080;
    public boolean useSsl = false;
    public DatabaseType type = DatabaseType.H2;
}
