package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


/**
 * Utility Class for discoverer skill
 */
@Getter
@Setter
@NoArgsConstructor
public final class DiscovererSkill extends Skill {
    private static final Map<UUID, Double> blocksWalked = new HashMap<>();
    private Map<Integer, Double> rewardsPerBlocksWalked;

    /**
     * Makes a discoverer skil
     *
     * @param id                       Id
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
     * @param rewardsPerBlocksWalked   Rewards Per Blocks Walked
     */
    @Builder
    public DiscovererSkill(final String id,
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
                           final Map<Integer, Double> rewardsPerBlocksWalked) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel, xpRequirements,
                skillInfoInGUI, statRewards,
                skillInfoInMessage, commandRewards);
        this.rewardsPerBlocksWalked = rewardsPerBlocksWalked;
    }

    /**
     * Adds on player move event
     *
     * @param e {@link PlayerMoveEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMoveEvent(final PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        final UUID uuid = player.getUniqueId();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final Location from = e.getFrom();
        final Location to = e.getTo();

        if (to == null) {
            return;
        }

        if (from.getWorld() != to.getWorld()) {
            return;
        }

        final double walked = blocksWalked.getOrDefault(uuid, 0D) + from.distance(to);

        blocksWalked.put(uuid, walked);

        final Optional<Integer> optReward = this.rewardsPerBlocksWalked.keySet().stream().filter(reward -> reward <= walked).findFirst();

        if (!optReward.isPresent()) {
            return;
        }

        final double xp = this.rewardsPerBlocksWalked.getOrDefault(optReward.get(), 0D);

        if (xp <= 0) {
            return;
        }

        blocksWalked.put(uuid, walked - optReward.get());

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);
    }

    /**
     * Adds an on craft
     *
     * @param e {@link PlayerQuitEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCraft(final PlayerQuitEvent e) {
        blocksWalked.remove(e.getPlayer().getUniqueId());
    }

    /**
     * Adds an on craft
     *
     * @param e {@link PlayerKickEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCraft(final PlayerKickEvent e) {
        blocksWalked.remove(e.getPlayer().getUniqueId());
    }
}
