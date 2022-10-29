package com.qualityplus.assistant.api.database;

import com.qualityplus.assistant.api.config.ConfigDatabase;

import java.util.Optional;

public interface UriGetter {
    default String getUri(ConfigDatabase configDatabase){
        return configDatabase.type.getUri()
                .replaceAll("user", Optional.ofNullable(configDatabase.userName).orElse(""))
                .replaceAll("host", Optional.ofNullable(configDatabase.host).orElse(""))
                .replaceAll("port", Optional.ofNullable(String.valueOf(configDatabase.port)).orElse(""))
                .replaceAll("password", Optional.ofNullable(configDatabase.passWord).orElse(""))
                .replaceAll("database", Optional.ofNullable(configDatabase.database).orElse(""));
    }
}
