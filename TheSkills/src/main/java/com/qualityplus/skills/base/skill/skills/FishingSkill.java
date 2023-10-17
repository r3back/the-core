package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for fishing skills
 */
@Getter
@Setter
@NoArgsConstructor
public final class FishingSkill extends Skill {
    private Map<String, Double> rewards;
    private double rewardsForAllCaught;

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
     * @param rewardsForAllCaught Rewards For All Caught
     */
    @Builder
    public FishingSkill(final String id,
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
                        final Map<String, Double> rewards,
                        final double rewardsForAllCaught) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewards = rewards;
        this.rewardsForAllCaught = rewardsForAllCaught;
    }

    /**
     * Adds a fishing skill
     *
     * @param e {@link PlayerFishEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void fishingSkill(final PlayerFishEvent e) {
        final Player player = e.getPlayer();

        final String caughtName = Optional.ofNullable(e.getCaught()).map(Entity::getName).map(String::toUpperCase).orElse(null);

        final State state = e.getState();

        if (caughtName == null) {
            return;
        }

        if (state != State.CAUGHT_FISH && state != State.CAUGHT_ENTITY) {
            return;
        }

        if (state == State.CAUGHT_ENTITY && !this.rewards.containsKey(caughtName)) {
            return;
        }

        final double xp = this.rewardsForAllCaught + this.rewards.getOrDefault(caughtName, 0d);

        if (xp <= 0) {
            return;
        }

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);

    }
}
