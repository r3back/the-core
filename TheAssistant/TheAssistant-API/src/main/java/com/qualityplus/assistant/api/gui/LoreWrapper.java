package com.qualityplus.assistant.api.gui;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class LoreWrapper extends OkaeriConfig {
    public int wrapLength;
    public String wrapStart;
}
