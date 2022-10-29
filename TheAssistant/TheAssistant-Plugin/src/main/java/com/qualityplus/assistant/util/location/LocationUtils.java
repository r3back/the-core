package com.qualityplus.assistant.util.location;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Optional;

@UtilityClass
public class LocationUtils {
    private static final String LOCATION_FORMAT = "X: %x%, Y: %y%, Z: %z%, World: %world%";
    private static final String NULL_LOCATION_FORMAT = "&c✘";


    public String toString(Location location){
        if(location == null) return NULL_LOCATION_FORMAT;

        //Don't change this to prevent getName taken from WorldInfo
        String world = Optional.ofNullable(location.getWorld()).map(world1 -> world1.getName()).orElse(NULL_LOCATION_FORMAT);

        return StringUtils.processMulti(LOCATION_FORMAT, PlaceholderBuilder
                .create(new Placeholder("world", world),
                        new Placeholder("x", location.getX()),
                        new Placeholder("y", location.getY()),
                        new Placeholder("z", location.getZ()))
                .get());
    }

    public Location fromString(String from){
        String[] separated = from.split(",");

        double[] coords = new double[3];

        String world = null;

        int count = 0;

        for(String value : separated){
            if(value.contains(" World: "))
                world = value.replaceAll(" World: ", "");
            else
                coords[count] = MathUtils.extractNumbers(value);
            count++;
        }

        return Optional.ofNullable(world).map(w -> new Location(Bukkit.getWorld(w), coords[0], coords[1], coords[2])).orElse(null);
    }
}
