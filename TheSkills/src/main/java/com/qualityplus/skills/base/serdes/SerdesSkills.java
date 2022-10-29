package com.qualityplus.skills.base.serdes;

import com.qualityplus.assistant.api.common.rewards.Reward;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.api.common.rewards.commands.CommandRewards;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.reward.StatRewards;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public final class SerdesSkills implements ObjectSerializer<Skill> {
    private static final SerdesGUIOptions SERDES_GUI_OPTIONS = new SerdesGUIOptions();

    @Override
    public boolean supports(@NonNull Class<? super Skill> type) {
        return Skill.class.isAssignableFrom(type);
    }

    private void serializeStatRewards(@NonNull Skill skill, @NonNull SerializationData data){
        skill.getStatRewards().getRewards().forEach((key1, value) -> data.addCollection("statReward-level-" + key1, value, StatReward.class));
    }

    private void serializeCommandRewards(@NonNull Skill skill, @NonNull SerializationData data){
        skill.getCommandRewards().getRewards().forEach((key1, value) -> data.addCollection("skill.commandReward." + key1, value, CommandReward.class));
    }

    private void serializeMapList(@NonNull Skill skill, @NonNull SerializationData data, String key, Skill.MessageType type){
        Map<Integer, List<String>> toCheck = type.equals(Skill.MessageType.GUI) ? skill.getSkillsInfoInGUI() : skill.getSkillsInfoInMessage();

        toCheck.forEach((key1, value) -> data.addCollection(key + "-level-" + key1, value, String.class));
    }

    @Override
    public void serialize(@NonNull Skill object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("id", object.getId());
        data.add("displayName", object.getDisplayName());
        data.add("description", object.getDescription());
        data.add("enabled", object.isEnabled());
        data.add("maxLevel", object.getMaxLevel());

        data.addAsMap("xpRequirements", object.getXpRequirements(), Integer.class, Double.class);

        serializeStatRewards(object, data);

        serializeCommandRewards(object, data);

        serializeMapList(object, data, "skillsInfoInGUI", Skill.MessageType.GUI);

        serializeMapList(object, data, "skillsInfoInMessage", Skill.MessageType.LEVEL_UP);

        SERDES_GUI_OPTIONS.serialize(object.getGuiOptions(), data, generics);
    }

    @Override
    public Skill deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(Skill skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setDisplayName(data.get("displayName", String.class));
        skill.setDescription(data.getAsList("description", String.class));
        skill.setEnabled(data.get("enabled", Boolean.class));
        skill.setId(data.get("id", String.class));
        skill.setMaxLevel(data.get("maxLevel", Integer.class));

        skill.setXpRequirements(data.getAsMap("xpRequirements", Integer.class, Double.class));

        skill.setCommandRewards(getCommandRewards(data));
        skill.setStatRewards(getStatRewards(data));

        skill.setSkillsInfoInGUI(getInformation(data, "skillsInfoInGUI"));
        skill.setSkillsInfoInMessage(getInformation(data, "skillsInfoInMessage"));

        skill.setGuiOptions(SERDES_GUI_OPTIONS.deserialize(data, generics));

    }

    private StatRewards getStatRewards(@NonNull DeserializationData data){
        Map<Integer, List<Reward>> rewards = new HashMap<>();

        for(String key : data.asMap().keySet().stream()
                .filter(key -> key.contains("statReward"))
                .collect(Collectors.toList())){

            String number = key.replace("statReward-level-", "");

            Integer mapKey = Integer.valueOf(key.replace("statReward-level-", ""));

            List<Reward> list = data.getAsList(key, StatReward.class)
                    .stream()
                    .map(reward -> (Reward) reward)
                    .collect(Collectors.toList());

            rewards.put(mapKey, list);
        }

        return new StatRewards(rewards);
    }

    private CommandRewards getCommandRewards(@NonNull DeserializationData data){
        Map<Integer, List<Reward>> rewards = new HashMap<>();

        for(String key : data.asMap().keySet().stream()
                .filter(key -> key.contains(".commandReward"))
                .collect(Collectors.toList())){

            Integer mapKey = Integer.valueOf(key.replace("skill.commandReward.", ""));

            List<Reward> list = data.getAsList(key, CommandReward.class)
                    .stream()
                    .map(reward -> (Reward) reward)
                    .collect(Collectors.toList());

            rewards.put(mapKey, list);
        }

        return new CommandRewards(rewards);
    }

    private Map<Integer, List<String>> getInformation(@NonNull DeserializationData data, String infoKey){
        Map<Integer, List<String>> info = new HashMap<>();

        for(String key : data.asMap().keySet().stream()
                .filter(key -> key.contains(infoKey))
                .collect(Collectors.toList())){

            Integer mapKey = Integer.valueOf(key.replace(infoKey + "-level-", ""));

            List<String> list = data.getAsList(key, String.class);

            info.put(mapKey, list);
        }

        return info;
    }


}
