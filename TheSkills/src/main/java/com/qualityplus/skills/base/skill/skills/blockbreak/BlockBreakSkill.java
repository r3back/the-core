package com.qualityplus.skills.base.skill.skills.blockbreak;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.api.service.SkillsService;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.Map;

@Getter
public abstract class BlockBreakSkill extends Skill {
    private final Map<XMaterial, Double> minionXpRewards;
    private final Map<XMaterial, Double> rewards;

    public BlockBreakSkill(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount, int maxLevel, Map<Integer, Double> xpRequirements, Map<Integer, List<String>> skillInfoInGUI, Map<Integer, List<StatReward>> statRewards, Map<Integer, List<String>> skillInfoInMessage, Map<Integer, List<CommandReward>> commandRewards, Map<XMaterial, Double> rewards, Map<XMaterial, Double> minionXpRewards) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, maxLevel, xpRequirements, skillInfoInGUI, statRewards, skillInfoInMessage, commandRewards);
        this.minionXpRewards = minionXpRewards;
        this.rewards = rewards;
    }

    protected void onBreak(BlockBreakEvent e){
        final Player player = e.getPlayer();

        if(!SkillsPlayerUtil.isInSurvival(player)) return;

        XMaterial xmaterial = XMaterial.matchXMaterial(e.getBlock().getType());

        final SkillsService service = TheSkills.getApi().getSkillsService();

        getBlockBreakEventXp(xmaterial, rewards)
                .map(BlockBreakResponse::getXp)
                .ifPresent(xp -> service.addXp(player, true, true, this, xp));
    }
}
