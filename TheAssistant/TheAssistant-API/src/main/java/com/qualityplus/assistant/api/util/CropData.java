package com.qualityplus.assistant.api.util;

import com.cryptomorin.xseries.XMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public enum CropData {
    WHEAT(7),
    CARROTS(7),
    POTATOES(3),
    NULL_CROP( 0);

    private final int maxAge;
}
