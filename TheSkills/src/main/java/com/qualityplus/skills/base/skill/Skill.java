package com.qualityplus.skills.base.skill;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.registry.ListenerRegistrable;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatRewards;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Skill extends CommonObject implements ListenerRegistrable {
    private int maxLevel;
    private StatRewards statRewards;
    private CommandRewards commandRewards;
    private Map<Integer, Double> xpRequirements;
    private Map<Integer, List<String>> skillsInfoInGUI;
    private Map<Integer, List<String>> skillsInfoInMessage;

    public Skill(String id, boolean enabled, String displayName, List<String> description, int maxLevel, StatRewards statRewards, CommandRewards commandRewards, GUIOptions skillGUIOptions,
                 Map<Integer, Double> xpRequirements, Map<Integer, List<String>> skillsInfoInGUI, Map<Integer, List<String>> skillsInfoInMessage) {
        this.id = id;
        this.enabled = enabled;
        this.displayName = displayName;
        this.description = description;
        this.maxLevel = maxLevel;
        this.statRewards = statRewards;
        this.commandRewards = commandRewards;
        this.guiOptions = skillGUIOptions;
        this.xpRequirements = xpRequirements;
        this.skillsInfoInGUI = skillsInfoInGUI;
        this.skillsInfoInMessage = skillsInfoInMessage;
    }

    @Override
    public void register(){
        Skills.registerNewSkill(this);
    }

    @Override
    public void addExtraListener(Class<? extends Listener> listener){
        extraListeners.add(listener);
    }

    public List<String> getSkillCacheMessages(int level, MessageType type) {
        List<String> lore = new ArrayList<>();

        Map<Integer, List<String>> toWorkWith = type.equals(MessageType.GUI) ? skillsInfoInGUI : skillsInfoInMessage;

        if (toWorkWith.containsKey(level)) {
            lore.addAll(toWorkWith.get(level));
        } else {
            int highestLevel = 1;

            for (Integer startLevel : toWorkWith.keySet()) {
                if (startLevel > level)
                    break;

                if (startLevel > highestLevel)
                    highestLevel = startLevel;
            }

            lore.addAll(toWorkWith.getOrDefault(highestLevel, new ArrayList<>()));
        }

        return lore;
    }


    public List<Reward> getRewards(int level, RewardType type) {
        Map<Integer, List<Reward>> toWorkWith = type.equals(RewardType.STAT) ? statRewards.getRewards() : commandRewards.getRewards();

        if (toWorkWith.containsKey(level)) {
            return toWorkWith.get(level);
        } else {
            int highestLevel = 1;

            for (Integer startLevel : toWorkWith.keySet()) {
                if (startLevel > level)
                    break;

                if (startLevel > highestLevel)
                    highestLevel = startLevel;
            }

            return toWorkWith.getOrDefault(highestLevel, new ArrayList<>());
        }
    }

    public double getLevelRequirement(int level){
        return xpRequirements.getOrDefault(level, 1D);
    }

    public enum MessageType{
        LEVEL_UP,
        GUI
    }

    public enum RewardType{
        COMMAND,
        STAT
    }

    protected double getBlockBreakEventXp(BlockBreakEvent e, Map<XMaterial, Double> rewards){
        Player player = e.getPlayer();

        if(!SkillsPlayerUtil.isInSurvival(player)) return 0D;

        XMaterial material = XMaterial.matchXMaterial(e.getBlock().getType());

        return rewards.getOrDefault(material, 0D);
    }

}
