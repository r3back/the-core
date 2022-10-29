package com.qualityplus.assistant.api.config;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ConfigMessage extends OkaeriConfig {
    private String messages;
    private boolean enabled;
}
