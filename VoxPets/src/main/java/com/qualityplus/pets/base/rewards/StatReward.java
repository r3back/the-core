package com.qualityplus.pets.base.rewards;

import com.qualityplus.pets.VoxPets;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class StatReward extends OkaeriConfig {
    private String stat;
    private int amount;

    public void addStat(Player player) {
        VoxPets.getApi().getDependencyHandler().getSkills().addStat(player, stat, amount);
    }

    public void removeStat(Player player) {
        VoxPets.getApi().getDependencyHandler().getSkills().removeStat(player, stat, amount);
    }
}
