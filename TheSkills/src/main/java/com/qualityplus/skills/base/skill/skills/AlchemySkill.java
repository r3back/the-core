package com.qualityplus.skills.base.skill.skills;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
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
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for alchemy skill
 */
@Getter
@Setter
@NoArgsConstructor
public final class AlchemySkill extends Skill {
    private Map<XMaterial, Double> rewards;

    /**
     * Make a alchemy skills
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
     * @param rewards                  Rewards
     */
    @Builder
    public AlchemySkill(final String id,
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
                        final Map<XMaterial, Double> rewards) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewards = rewards;
    }

    /**
     * Makes an on brew event
     *
     * @param event {@link BrewEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBrewEvent(final BrewEvent event) {
        final Location loc = event.getBlock().getLocation();

        final Player player = (Player) loc.getWorld()
                .getNearbyEntities(loc, 5, 5, 5)
                .stream()
                .filter(entity -> entity instanceof Player)
                .findFirst().orElse(null);

        if (player == null) {
            return;
        }

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final Optional<XMaterial> material = Optional.ofNullable(event.getContents().getIngredient()).map(ItemStack::getType).map(XMaterial::matchXMaterial);

        if (!material.isPresent()) {
            return;
        }

        final double xp = this.rewards.getOrDefault(material.get(), 0D);

        if (xp <= 0) {
            return;
        }

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);

    }
}
