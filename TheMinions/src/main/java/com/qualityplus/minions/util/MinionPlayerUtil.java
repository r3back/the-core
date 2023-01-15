package com.qualityplus.minions.util;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class MinionPlayerUtil {
    public static int getMinionsAmount(Player player){
        int minions = 1;

        for(int i = 1; i<15; i+=1)
            if(player.hasPermission("theminions.place_amount." + i)) minions = Math.max(minions, i);

        return minions;
    }
}
