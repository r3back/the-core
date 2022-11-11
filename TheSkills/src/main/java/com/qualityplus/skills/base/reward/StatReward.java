package com.qualityplus.skills.base.reward;

import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.skills.TheSkills;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;
import org.bukkit.entity.Player;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class StatReward extends OkaeriConfig implements Reward {
    private String stat;
    private int amount;

    @Override
    public void execute(Player player) {
        TheSkills.getApi()
                .getSkillsService()
                .getData(player.getUniqueId())
                .ifPresent(userData -> userData.getSkills().addLevel(stat, amount));
    }
}
