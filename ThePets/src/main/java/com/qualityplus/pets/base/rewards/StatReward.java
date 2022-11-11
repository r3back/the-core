package com.qualityplus.pets.base.rewards;

import com.qualityplus.assistant.api.common.rewards.Reward;
import eu.okaeri.configs.OkaeriConfig;
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
        com.qualityplus.skills.TheSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addArmor(stat, amount));
    }

    public void removeStat(Player player) {
        com.qualityplus.skills.TheSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().removeArmor(stat, amount));
    }
}
