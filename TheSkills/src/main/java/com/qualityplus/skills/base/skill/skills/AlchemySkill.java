package com.qualityplus.skills.base.skill.skills;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
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

@Getter
@Setter
@NoArgsConstructor
public final class AlchemySkill extends Skill {
    private Map<XMaterial, Double> rewards;

    @Builder
    public AlchemySkill(String id, boolean enabled, String displayName, List<String> description, StatRewards statRewards, CommandRewards commandRewards,
                        GUIOptions skillGUIOptions, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage,
                        Map<Integer, Double> xpRequirements, int maxLevel, Map<XMaterial, Double> rewards) {
        super(id, enabled, displayName, description, maxLevel, statRewards, commandRewards, skillGUIOptions, xpRequirements, skillsInfoInGUI, skillsInfoInMessage);

        this.rewards = rewards;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBrewEvent(BrewEvent event){
        Location loc = event.getBlock().getLocation();

        Player player = (Player) loc.getWorld()
                .getNearbyEntities(loc, 5,5,5)
                .stream()
                .filter(entity -> entity instanceof Player)
                .findFirst().orElse(null);

        if(player == null) return;

        if(!SkillsPlayerUtil.isInSurvival(player)) return;

        Optional<XMaterial> material = Optional.ofNullable(event.getContents().getIngredient()).map(ItemStack::getType).map(XMaterial::matchXMaterial);

        if(!material.isPresent()) return;

        double xp = rewards.getOrDefault(material.get(), 0D);

        if(xp <= 0) return;

        TheSkills.getApi().getSkillsService().addXp(player, true, true, this, xp);

    }
}
