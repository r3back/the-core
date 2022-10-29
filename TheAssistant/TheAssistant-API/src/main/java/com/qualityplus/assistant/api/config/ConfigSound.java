package com.qualityplus.assistant.api.config;

import com.cryptomorin.xseries.XSound;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ConfigSound extends OkaeriConfig {
    private XSound sound;
    private boolean enabled;
    private float volume;
    private float pitch;
}
