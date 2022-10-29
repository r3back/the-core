package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.base.event.PlayerKillEvent;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public final class CombatSkill extends Skill {
    private Map<EntityType, Double> rewards;

    @Builder
    public CombatSkill(String id, boolean enabled, String displayName, List<String> description, StatRewards statRewards, CommandRewards commandRewards,
                       GUIOptions skillGUIOptions, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage,
                       Map<Integer, Double> xpRequirements, int maxLevel, Map<EntityType, Double> rewards) {
        super(id, enabled, displayName, description, maxLevel, statRewards, commandRewards, skillGUIOptions, xpRequirements, skillsInfoInGUI, skillsInfoInMessage);

        this.rewards = rewards;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onKillMob(PlayerKillEvent e){
        Player player = e.getPlayer();

        if(!SkillsPlayerUtil.isInSurvival(player)) return;

        Entity entity = e.getKilled();

        double xp = rewards.getOrDefault(entity.getType(), 0D);

        if(xp <= 0) return;

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);
    }
}
