package com.qualityplus.dragon.api.game.settings;

import org.bukkit.Location;

public interface Settings {
    Location getSpawn();
    int couldownTime();
    String schematic();

    void setSchematic(String schematic);
    void setCouldownTime(int time);
    void setLocation(Location location);
}
