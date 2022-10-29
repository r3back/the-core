package com.qualityplus.assistant.util.particle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public enum ParticleColor {
    RED(Color.RED),
    WHITE(Color.WHITE),
    YELLOW(Color.YELLOW),
    BLUE(Color.BLUE),
    CYAN(Color.CYAN),
    MAGENTA(Color.MAGENTA),
    GREEN(Color.GREEN),
    PINK(Color.PINK),
    BLACK(Color.BLACK),
    DARK_GRAY(Color.DARK_GRAY),
    GRAY(Color.GRAY),
    LIGHT_GRAY(Color.LIGHT_GRAY);

    private @Getter final Color color;

}
