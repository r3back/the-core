package com.qualityplus.pets.base.pet.potion;

import com.qualityplus.assistant.api.common.rewards.Reward;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PetPotion extends OkaeriConfig {
    private String potion;
    private int level;

    public void addPotion(Player player) {
        PotionEffectType effectType = PotionEffectType.getByName(potion);
        if (effectType == null)
            return;
        player.addPotionEffect(new PotionEffect(effectType, Integer.MAX_VALUE, level - 1));
    }

    public void removePotion(Player player) {
        PotionEffectType effectType = PotionEffectType.getByName(this.potion);
        if (effectType == null)
            return;
        player.removePotionEffect(effectType);
    }
}
