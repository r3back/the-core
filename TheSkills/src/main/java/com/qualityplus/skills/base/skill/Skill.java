package com.qualityplus.skills.base.skill;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.api.registry.ListenerRegistrable;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.base.skill.skills.blockbreak.BlockBreakResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for skill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Skill extends CommonObject implements ListenerRegistrable {
    private int maxLevel;
    private Map<Integer, Double> xpRequirements;
    private Map<Integer, List<String>> skillInfoInGUI;
    private Map<Integer, List<StatReward>> statRewards;
    private Map<Integer, List<String>> skillInfoInMessage;
    private Map<Integer, List<CommandReward>> commandRewards;

    /**
     * Makes a skill
     *
     * @param id                        Id
     * @param enabled                  Enabled
     * @param displayName              Display Name
     * @param description              Description
     * @param skillGUIOptions          {@link GUIOptions}
     * @param initialAmount            Initial Amount
     * @param maxLevel                 Max Level
     * @param xpRequirements           Xp Requirements
     * @param skillInfoInGUI           Skill Info In GUI
     * @param statRewards              Stat Rewards
     * @param skillInfoInMessage       Skill Info In Message
     * @param commandRewards           Command Rewards
     */
    public Skill(final String id,
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
                 final Map<Integer, List<CommandReward>> commandRewards) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount);
        this.maxLevel = maxLevel;
        this.xpRequirements = xpRequirements;
        this.skillInfoInGUI = skillInfoInGUI;
        this.statRewards = statRewards;
        this.skillInfoInMessage = skillInfoInMessage;
        this.commandRewards = commandRewards;
    }

    @Override
    public void register() {
        Skills.registerNewSkill(this);
    }

    @Override
    public void addExtraListener(final Class<? extends ExtraListener> listener) {
        extraListeners.add(listener);
    }

    private <T> T getMap(final Map<Integer, T> map, final int level) {
        if (map.containsKey(level)) {
            return map.get(level);
        } else {
            int highestLevel = 1;

            for (Integer startLevel : map.keySet()) {
                if (startLevel > level) {
                    break;
                }
                if (startLevel > highestLevel) {
                    highestLevel = startLevel;
                }
            }

            return map.getOrDefault(highestLevel, null);
        }
    }

    /**
     * Adds a cached message
     *
     * @param level Level
     * @return      Cached message
     */
    public List<String> getCachedMessage(final int level) {
        return getMap(this.skillInfoInMessage, level);
    }

    /**
     * Adds a cached GUI
     *
     * @param level Level
     * @return      Cached GUI
     */
    public List<String> getCachedGUI(final int level) {
        return getMap(this.skillInfoInGUI, level);

    }

    /**
     * Adds a command reward
     *
     * @param level Level
     * @return      {@link CommandReward}
     */
    public List<CommandReward> getCommandRewards(final int level) {
        return Optional.ofNullable(getMap(this.commandRewards, level)).orElse(Collections.emptyList());
    }

    /**
     * Adds a stat rewards
     *
     * @param level Level
     * @return      {@link StatReward}
     */
    public List<StatReward> getStatRewards(final int level) {
        return Optional.ofNullable(getMap(this.statRewards, level)).orElse(Collections.emptyList());
    }

    /**
     * Adds an level requirement
     *
     * @param level Level
     * @return      Level Requirement
     */
    public double getLevelRequirement(final int level) {
        return getMap(this.xpRequirements, level);
    }

    /**
     * Makes a block break response
     *
     * @param material Material
     * @param rewards  Rewards
     * @return         {@link BlockBreakResponse}
     */
    public Optional<BlockBreakResponse> getBlockBreakEventXp(final XMaterial material, final Map<XMaterial, Double> rewards) {
        final double xp = rewards.getOrDefault(material, 0D);

        if (xp <= 0) {
            return Optional.empty();
        }

        return Optional.of(BlockBreakResponse.builder()
                        .xp(xp)
                        .build());
    }


}
