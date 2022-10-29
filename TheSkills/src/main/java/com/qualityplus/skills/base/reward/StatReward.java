package com.qualityplus.skills.base.reward;

import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.skills.TheSkills;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
@Setter
public final class StatReward extends OkaeriConfig implements Reward {
    private String stat;
    private int amount;

    @Override
    public void execute(Player player) {
        TheSkills.getApi()
                .getSkillsService()
                .getSkillsData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addLevel(stat, amount));
    }
}
