package com.qualityplus.assistant.util.sound;

import com.cryptomorin.xseries.XSound;
import com.qualityplus.assistant.api.config.ConfigSound;
import com.qualityplus.assistant.util.console.ConsoleUtils;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public final class SoundUtils {
    public static void playSound(Player player, XSound xsound, float volume, float pitch){
        try {
            player.playSound(player.getLocation(), xsound.parseSound(), volume, pitch);
        }catch (Exception e){
            ConsoleUtils.msg("Invalid Sound! Name: " + getSound(xsound));
        }
    }

    public static void playSound(Player player, ConfigSound configSound){
        if(!configSound.isEnabled()) return;
        playSound(player, configSound.getSound(), configSound.getVolume(), configSound.getPitch());
    }

    public static void playSound(Player player, XSound xsound){
        playSound(player, xsound, 0.2f, 1f);
    }


    public static void playSound(Player player, String xsound, float volume, float pitch){
        playSound(player, byName(xsound), volume, pitch);
    }

    public static void playSound(Player player, String xsound){
        playSound(player, byName(xsound), 0.2f, 1f);
    }


    private static XSound byName(String name){
        try {
            return XSound.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }

    private static String getSound(XSound sound){
        try {
            return sound.toString();
        }catch (Exception e){
            return "[Unrecognized Sound]";
        }
    }
}
