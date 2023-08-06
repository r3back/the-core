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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for carpentry  skill
 */
@Getter
@Setter
@NoArgsConstructor
public final class CarpentrySkill extends Skill {
    private Map<XMaterial, Double> rewards;
    private double xpForAllItems;

    /**
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
     * @param xpForAllItems            Xp For All Items
     */
    @Builder
    public CarpentrySkill(final String id,
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
                          final double xpForAllItems) {
        super(id, enabled, displayName,
                description, skillGUIOptions,
                initialAmount, maxLevel,
                xpRequirements, skillInfoInGUI,
                statRewards, skillInfoInMessage,
                commandRewards);
        this.rewards = rewards;
        this.xpForAllItems = xpForAllItems;
    }

    /**
     * Adds event on craft
     *
     * @param event {@link CraftItemEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCraft(final CraftItemEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final Optional<XMaterial> material = Optional.of(event.getRecipe().getResult()).map(ItemStack::getType).map(XMaterial::matchXMaterial);

        final double xp = this.rewards.getOrDefault(material.get(), 0D) + xpForAllItems;

        if (xp <= 0) {
            return;
        }

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);

    }
}
