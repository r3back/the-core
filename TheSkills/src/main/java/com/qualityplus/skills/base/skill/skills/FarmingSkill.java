package com.qualityplus.skills.base.skill.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
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
public final class FarmingSkill extends Skill {
    private Map<XMaterial, Double> rewards;

    @Builder
    public FarmingSkill(String id, boolean enabled, String displayName, List<String> description, StatRewards statRewards, CommandRewards commandRewards,
                        GUIOptions skillGUIOptions, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage,
                        Map<Integer, Double> xpRequirements, int maxLevel, Map<XMaterial, Double> rewards) {
        super(id, enabled, displayName, description, maxLevel, statRewards, commandRewards, skillGUIOptions, xpRequirements, skillsInfoInGUI, skillsInfoInMessage);

        this.rewards = rewards;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMine(BlockBreakEvent event){
        double xp = getBlockBreakEventXp(event, rewards);

        if(xp > 0) TheSkills.getApi().getSkillsService().addXp(event.getPlayer(), true, true, this, xp);
    }
}
