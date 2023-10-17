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

/**
 * Utility class for block break skills
 */
@Getter
public abstract class BlockBreakSkill extends Skill {
    private final Map<XMaterial, Double> minionXpRewards;
    private final Map<XMaterial, Double> rewards;

    /**
     * Makes a block break skills
     *
     * @param id                 Id
     * @param enabled            Enabled
     * @param displayName        Display Name
     * @param description        Description
     * @param skillGUIOptions    {@link GUIOptions}
     * @param initialAmount      Initial Amount
     * @param maxLevel           Max Level
     * @param xpRequirements     Xp Requirements
     * @param skillInfoInGUI     Skills indo In GUI
     * @param statRewards        Stats Rewards
     * @param skillInfoInMessage Skills Info In Message
     * @param commandRewards     Command Rewards
     * @param rewards            Rewards
     * @param minionXpRewards    Minion Xp Rewards
     */
    public BlockBreakSkill(final String id,
                           final boolean enabled,
                           final String displayName,
                           final List<String> description,
                           final GUIOptions skillGUIOptions,
                           final double initialAmount,
                           final int maxLevel,
                           final Map<Integer, Double> xpRequirements,
                           final Map<Integer, List<String>> skillInfoInGUI,
                           final Map<Integer, List<StatReward>> statRewards,
                           final Map<Integer, List<String>> skillInfoInMessage,
                           final Map<Integer, List<CommandReward>> commandRewards,
                           final Map<XMaterial, Double> rewards,
                           final Map<XMaterial, Double> minionXpRewards) {
        super(id, enabled, displayName, description,
                skillGUIOptions, initialAmount,
                maxLevel, xpRequirements,
                skillInfoInGUI, statRewards,
                skillInfoInMessage, commandRewards);
        this.minionXpRewards = minionXpRewards;
        this.rewards = rewards;
    }

    protected void onBreak(final BlockBreakEvent e) {
        final Player player = e.getPlayer();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final XMaterial xmaterial = XMaterial.matchXMaterial(e.getBlock().getType());

        final SkillsService service = TheSkills.getApi().getSkillsService();

        getBlockBreakEventXp(xmaterial, this.rewards)
                .map(BlockBreakResponse::getXp)
                .ifPresent(xp -> service.addXp(player, true, true, this, xp));
    }
}
