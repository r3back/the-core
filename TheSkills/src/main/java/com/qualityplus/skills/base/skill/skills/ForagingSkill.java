package com.qualityplus.skills.base.skill.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public final class ForagingSkill extends Skill {
    private Map<XMaterial, Double> rewards;

    @Builder
    public ForagingSkill(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount, int maxLevel, Map<Integer, Double> xpRequirements, Map<Integer, List<String>> skillInfoInGUI, Map<Integer, List<StatReward>> statRewards, Map<Integer, List<String>> skillInfoInMessage, Map<Integer, List<CommandReward>> commandRewards, Map<XMaterial, Double> rewards) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, maxLevel, xpRequirements, skillInfoInGUI, statRewards, skillInfoInMessage, commandRewards);
        this.rewards = rewards;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMine(BlockBreakEvent event){
        double xp = getBlockBreakEventXp(event, rewards);

        if(xp > 0) TheSkills.getApi().getSkillsService().addXp(event.getPlayer(), true, true, this, xp);
    }
}
