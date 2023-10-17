package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.skills.blockbreak.BlockBreakSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;
import java.util.Map;


/**
 * Utility class for foraging skills
 */
@Getter
@Setter
public final class ForagingSkill extends BlockBreakSkill {

    /**
     * Makes a mining skills
     *
     * @param id                 Id
     * @param enabled            Enabled
     * @param displayName        Display Name
     * @param description        Description
     * @param skillGUIOptions    {@link GUIOptions}
     * @param initialAmount      Initial Amount
     * @param maxLevel           Max Level
     * @param xpRequirements     Xp Requirements
     * @param skillInfoInGUI     Skill Info In GUI
     * @param statRewards        Stat Rewards
     * @param skillInfoInMessage Skill info In Message
     * @param commandRewards     Command Rewards
     * @param rewards            Rewards
     * @param minionXpRewards    Minion Xp Rewards
     */
    @Builder
    public ForagingSkill(final String id,
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
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards, rewards,
                minionXpRewards);
    }

    /**
     * Adds an on mine
     *
     * @param e {@link BlockBreakEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMine(final BlockBreakEvent e) {
        onBreak(e);
    }
}
