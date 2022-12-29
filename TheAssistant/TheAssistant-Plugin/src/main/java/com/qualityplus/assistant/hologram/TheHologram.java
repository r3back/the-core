package com.qualityplus.assistant.hologram;

import com.google.common.collect.Lists;
import com.qualityplus.assistant.util.StringUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TheHologram {
    private List<ArmorStand> armorStands;
    private Location location;
    @Getter
    private List<String> txt;

    private TheHologram(List<String> txt, Location location){
        this.txt = txt;
        this.location = location;
        this.armorStands = createArmorStands();
    }

    public static TheHologram create(List<String> txt, Location location){
        return new TheHologram(txt, location);
    }

    private List<ArmorStand> createArmorStands(){
        List<ArmorStand> armorStands = new ArrayList<>();

        double amount = 0.25;

        double initial = txt.size() * amount;

        Location initialLocation = location.clone().add(0, initial, 0);

        int size = 0;

        for(String line : Lists.reverse(txt)){
            double newY = initial - (amount * size);

            ArmorStand armorStand = location.getWorld().spawn(initialLocation.clone().subtract(new Vector(0, newY, 0)), ArmorStand.class);

            armorStand.setArms(true);
            armorStand.setSmall(true);
            armorStand.setVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);
            armorStand.setBasePlate(false);
            armorStand.setCollidable(false);
            armorStand.setCustomNameVisible(true);

            String colored = StringUtils.color(line);

            armorStand.setCustomName(colored);

            armorStands.add(armorStand);

            size++;
        }

        return armorStands;
    }

    public void move(Location location){
        remove();

        this.location = location;

        this.armorStands = createArmorStands();
    }

    public TheHologram rename(List<String> txt){
        remove();

        this.txt = txt;
        this.armorStands = createArmorStands();

        return this;
    }

    public void remove(){
        this.armorStands.stream()
                .filter(Objects::nonNull)
                .forEach(ArmorStand::remove);

        this.armorStands.clear();
    }
}
