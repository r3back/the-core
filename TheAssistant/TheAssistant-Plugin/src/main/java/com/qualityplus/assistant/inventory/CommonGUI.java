package com.qualityplus.assistant.inventory;

import com.qualityplus.assistant.inventory.background.Background;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public final class CommonGUI extends OkaeriConfig {
    public String title;
    public int size;
    public Background background;
    public Item closeGUI;
}