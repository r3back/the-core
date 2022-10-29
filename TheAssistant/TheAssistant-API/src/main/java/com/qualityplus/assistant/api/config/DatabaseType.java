package com.qualityplus.assistant.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DatabaseType {
    MARIADB("jdbc:mysql://%host%:%port%/%database%"),

    MYSQL("jdbc:mysql://%host%:%port%/%database%"),
    H2(""),
    FLAT(""),
    REDIS("redis://%user%:%password%@%host%:%port%/0"),
    MONGODB("mongodb://%user%:%password%@%host%:%port%/db");

    @Getter
    private final String uri;
}
