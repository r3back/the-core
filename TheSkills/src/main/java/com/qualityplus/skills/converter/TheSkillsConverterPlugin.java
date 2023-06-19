package com.qualityplus.skills.converter;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.common.rewards.commands.CommandReward;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.config.common.BaseFile;
import com.qualityplus.skills.base.config.skills.SkillFile;
import com.qualityplus.skills.base.reward.StatReward;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TheSkillsConverterPlugin extends OkaeriSilentPlugin {
    protected void moveSeaFortuneFile(Box box){
        try {
            File backupFolder = new File(this.getDataFolder() + "/stats/backup");

            if(!backupFolder.exists())
                backupFolder.mkdirs();


            Path oldPath = Paths.get(box.plugin().getDataFolder() + "/stats/sea_fortune.yml");
            Path newPath = Paths.get(box.plugin().getDataFolder() + "/stats/backup/sea_fortune.yml");

            Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            getLogger().warning("Failed when moving sea_fortune.yml to backup folder!");
        }

    }

    protected void convertStatsAndPerks(Box box, String folderName, String toConvert){

        boolean isOldVersion = false;

        File folder = new File(box.plugin().getDataFolder() + "/" + folderName);

        if(!folder.exists())
            return;

        List<File> files = Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        isOldVersion = files.stream()
                .map(File::getName)
                .anyMatch(name -> !name.contains("_" + toConvert) && !name.contains("backup"));


        if(isOldVersion)
            getLogger().warning("Old Version has been detected, making " + toConvert + " file conversion:");
        else {
            return;
        }

        for(File oldFile : files.stream()
                .filter(file -> !file.getName().contains("_" + toConvert))
                .collect(Collectors.toList())){

            String oldFileName = oldFile.getName();

            try {

                List<OkaeriConfig> fileList  = toConvert.equals("stat") ? box.statFiles().getAll() : box.perkFiles().getAll();;

                Optional<BaseFile> newFile = fileList.stream()
                        .filter(newF -> newF.getBindFile().getFileName().toString().replace("_" + toConvert, "").equals(oldFileName))
                        .map(newF -> (BaseFile) newF)
                        .findFirst();

                if(!newFile.isPresent()) continue;

                getLogger().warning("Converting " + oldFileName+ " to new version!");

                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(oldFile);

                String sectionName = oldFileName.contains("farming_fortune") ? "farming-fortune-config" : oldFileName.contains("brew_chance") ? "brewer-chance-perk" : oldFileName
                        .replace("config", toConvert)
                        .replace("critic", "crit")
                        .replace(".yml", "")
                        .replace("_", "-")
                        .replace(".yaml", "") + "-" + toConvert;

                ConfigurationSection section = configuration.getConfigurationSection(sectionName);

                if(section == null) continue;

                GUIOptions.GUIOptionsBuilder guiOptions = GUIOptions.builder();

                for(String subKey : section.getKeys(false)){
                    try {
                        String newKey = sectionName + "." + subKey;

                        switch (subKey) {
                            case "id":
                                String id = configuration.getString(newKey);
                                newFile.get().setId(id);
                                break;
                            case "displayName":
                                String displayName = configuration.getString(newKey);
                                newFile.get().setDisplayName(displayName);
                                break;
                            case "description":
                                List<String> description = configuration.getStringList(newKey);
                                newFile.get().setDescription(description);
                                break;
                            case "enabled":
                                boolean enabled = configuration.getBoolean(newKey);
                                newFile.get().setEnabled(enabled);
                                break;
                            case "slot":
                                int slot = configuration.getInt(newKey);
                                guiOptions.slot(slot);
                                break;
                            case "item":
                                String item = configuration.getString(newKey);
                                try {
                                    guiOptions.item(XMaterial.valueOf(item));
                                } catch (Exception e) {
                                    getLogger().warning("Failed to deserialize " + item + " in " + newKey);
                                }
                                break;
                            case "mainMenuLore":
                                List<String> mainMenuLore = configuration.getStringList(newKey);
                                guiOptions.mainMenuLore(mainMenuLore);
                                break;
                            case "page":
                                int page = configuration.getInt(newKey);
                                guiOptions.page(page);
                                break;
                            case "texture":
                                String texture = configuration.getString(newKey);
                                guiOptions.texture(texture);
                                break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                try {
                    newFile.get().setGuiOptions(guiOptions.build());
                }catch (Exception e){
                    getLogger().warning("Failed to deserialize guiOptions in " + oldFileName);
                }


                getLogger().info("Successfully converted " + oldFileName + " -> " + oldFileName.replace(".", "_" + toConvert + "."));

                OkaeriConfig config = (OkaeriConfig) newFile.get();

                config.save();

            }catch (Exception e){
                getLogger().warning("Failed to convert " + oldFileName+ " to new version!");
            }

            try {
                getLogger().info("Moving " + oldFileName + " to backup folder!");

                File backupFolder = new File(this.getDataFolder() + "/" + folderName + "/backup");

                if(!backupFolder.exists())
                    backupFolder.mkdirs();

                Path oldPath = Paths.get(oldFile.getAbsolutePath());
                Path newPath = Paths.get(this.getDataFolder().getAbsolutePath() + "/" + folderName + "/backup/" + oldFileName);

                Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                getLogger().warning("Failed when moving " + oldFileName + "!");

            }
        }
    }

    protected void convertSkills(Box box){

        boolean isOldVersion = false;

        File folder = new File(box.plugin().getDataFolder() + "/skills");

        if(!folder.exists())
            return;

        List<File> files = Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .collect(Collectors.toList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        isOldVersion = files.stream()
                .map(File::getName)
                .anyMatch(name -> !name.contains("_skill") && !name.contains("backup"));


        if(isOldVersion)
            getLogger().warning("Old Version has been detected, making file conversion:");
        else {
            getLogger().info("Everything is ok! Starting TheSkills!");
            return;
        }

        for(File oldFile : files.stream()
                .filter(file -> !file.getName().contains("_skill"))
                .collect(Collectors.toList())){

            String oldFileName = oldFile.getName();

            try {

                Optional<SkillFile> newFile = box.skillFiles().getAll().stream()
                        .filter(newF -> newF.getBindFile().getFileName().toString().replace("_skill", "").equals(oldFileName))
                        .map(newF -> (SkillFile) newF)
                        .findFirst();

                if(!newFile.isPresent()) continue;

                getLogger().warning("Converting " + oldFileName+ " to new version!");

                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(oldFile);

                String sectionName = oldFileName.replace(".yml", "").replace(".yaml", "") + "-skill";

                ConfigurationSection section = configuration.getConfigurationSection(sectionName);

                if(section == null) continue;

                GUIOptions.GUIOptionsBuilder guiOptions = GUIOptions.builder();

                Map<Integer, List<String>> infoInGUI = new HashMap<>();
                Map<Integer, List<String>> infoInMessage = new HashMap<>();
                Map<Integer, List<StatReward>> statRewards = new HashMap<>();

                for(String subKey : section.getKeys(false)){
                    try {
                        String newKey = sectionName + "." + subKey;

                        if(subKey.equals("id")){
                            String id = configuration.getString(newKey);
                            newFile.get().setId(id);
                        }else if(subKey.equals("displayName")){
                            String displayName = configuration.getString(newKey);
                            newFile.get().setDisplayName(displayName);
                        }else if(subKey.equals("description")){
                            List<String> description = configuration.getStringList(newKey);
                            newFile.get().setDescription(description);

                        }else if(subKey.equals("enabled")){
                            boolean enabled = configuration.getBoolean(newKey);
                            newFile.get().setEnabled(enabled);
                        }else if(subKey.equals("xpRequirements")){
                            newFile.get().setXpRequirements(getXpRequirements(configuration, newKey));
                        }else if(subKey.contains("statReward-level")){
                            try {
                                AnswerStat answer = getStatRewards(configuration, newKey);

                                if(answer == null) continue;

                                statRewards.put(answer.getKey(), answer.getValue());
                            }catch (Exception e){
                                getLogger().warning("Failed to deserialize " + newKey + " in " + oldFileName + "!");
                            }
                        }else if(subKey.contains("skillsInfoInMessage-level")){
                            try {
                                Answer answer = getInfoInGUIOrMessage(configuration, newKey);

                                if(answer == null) continue;

                                infoInMessage.put(answer.getKey(), answer.getValue());
                            }catch (Exception e){
                                getLogger().warning("Failed to deserialize " + newKey + " in " + oldFileName + "!");
                            }
                        }else if(subKey.contains("skillsInfoInGUI-level")){
                            try {
                                Answer answer = getInfoInGUIOrMessage(configuration, newKey);

                                if(answer == null) continue;

                                infoInGUI.put(answer.getKey(), answer.getValue());
                            }catch (Exception e){
                                getLogger().warning("Failed to deserialize " + newKey + " in " + oldFileName + "!");
                            }
                        }else if(subKey.equals("slot")){
                            int slot = configuration.getInt(newKey);
                            guiOptions.slot(slot);
                        }else if(subKey.equals("item")){
                            String item = configuration.getString(newKey);
                            try {
                                guiOptions.item(XMaterial.valueOf(item));
                            }catch (Exception e){
                                getLogger().warning("Failed to deserialize " + item + " in " + newKey);
                            }
                        }else if(subKey.equals("mainMenuLore")){
                            List<String> mainMenuLore = configuration.getStringList(newKey);
                            guiOptions.mainMenuLore(mainMenuLore);
                        }else if(subKey.equals("page")){
                            int page = configuration.getInt(newKey);
                            guiOptions.page(page);
                        }else if(subKey.equals("texture")){
                            String texture = configuration.getString(newKey);
                            guiOptions.texture(texture);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                try {
                    newFile.get().setSkillInfoInGUI(infoInGUI);
                }catch (Exception e){
                    getLogger().warning("Failed to deserialize infoInGUI in " + oldFileName);
                }

                try {
                    newFile.get().setSkillInfoInMessage(infoInMessage);
                }catch (Exception e){
                    getLogger().warning("Failed to deserialize infoInMessage in " + oldFileName);
                }

                try {
                    newFile.get().setGuiOptions(guiOptions.build());
                }catch (Exception e){
                    getLogger().warning("Failed to deserialize guiOptions in " + oldFileName);
                }

                try {
                    newFile.get().setStatRewards(statRewards);
                }catch (Exception e){
                    getLogger().warning("Failed to deserialize statRewards in " + oldFileName);
                }

                getLogger().info("Successfully converted " + oldFileName + " -> " + oldFileName.replace(".", "_skill."));

                OkaeriConfig config = (OkaeriConfig) newFile.get();

                config.save();

            }catch (Exception e){
                getLogger().warning("Failed to convert " + oldFileName+ " to new version!");
            }

            try {
                getLogger().info("Moving " + oldFileName + " to backup folder!");

                File backupFolder = new File(this.getDataFolder() + "/skills/backup");

                if(!backupFolder.exists())
                    backupFolder.mkdirs();

                Path oldPath = Paths.get(oldFile.getAbsolutePath());
                Path newPath = Paths.get(this.getDataFolder().getAbsolutePath() + "/skills/backup/" + oldFileName);

                Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                getLogger().warning("Failed when moving " + oldFileName + "!");

            }
        }
    }

    private Map<Integer, Double> getXpRequirements(YamlConfiguration config, String newKey){
        Map<Integer, Double> xpRequirements = new HashMap<>();
        for(int i = 0; i < 60; i++){
            String levelKey = newKey + "." + i;

            try {
                if(config.get(levelKey) == null) continue;

                double amount = config.getDouble(levelKey);

                xpRequirements.put(i, amount);
            }catch (Exception e){
                getLogger().warning("Failed to convert " + levelKey + " to new version!");
            }
        }
        return xpRequirements;
    }

    private Answer getInfoInGUIOrMessage(YamlConfiguration config, String newKey){

        try {
            String[] keySplit = newKey.split("-");

            int level = Integer.parseInt(keySplit[3]);

            List<String> list = config.getStringList(newKey);

            return new Answer(level, list);
        }catch (Exception e){
            getLogger().warning("Failed to convert " + newKey + " to new version!");
            return null;
        }
    }

    private AnswerStat getStatRewards(YamlConfiguration config, String newKey){

        try {
            String[] keySplit = newKey.split("-");

            int level = Integer.parseInt(keySplit[3]);

            List<LinkedHashMap<?, ?>> list = (List<LinkedHashMap<?, ?>>) config.getList(newKey);

            List<StatReward> statRewards = new ArrayList<>();

            if(list != null && list.size() != 0){
                for(LinkedHashMap<?, ?> reward : list){
                    StatReward statReward = new StatReward();

                    for(Object key : reward.keySet()){

                        if(key.equals("stat") && reward.get(key) != null){
                            statReward.setStat((String) reward.get(key));
                        }else if(key.equals("amount")){
                            statReward.setAmount((Integer) reward.get(key));
                        }

                    }
                    statRewards.add(statReward);

                }
            }
            return new AnswerStat(level, statRewards);
        }catch (Exception e){
            e.printStackTrace();
            getLogger().warning("Failed to convert " + newKey + " to new version!");
            return null;
        }
    }

    /*private AnswerCmd getCommandRewards(YamlConfiguration config, String newKey){

        try {
            String[] keySplit = newKey.split("-");

            int level = Integer.parseInt(keySplit[3]);

            List<LinkedHashMap<?, ?>> list = (List<LinkedHashMap<?, ?>>) config.getList(newKey);

            List<AnswerCmd> statRewards = new ArrayList<>();

            if(list != null && list.size() != 0){
                for(LinkedHashMap<?, ?> reward : list){
                    AnswerCmd statReward = new AnswerCmd();

                    for(Object key : reward.keySet()){

                        if(key.equals("stat") && reward.get(key) != null){
                            statReward.setStat((String) reward.get(key));
                        }else if(key.equals("amount")){
                            statReward.setAmount((Integer) reward.get(key));
                        }

                    }
                    statRewards.add(statReward);

                }
            }
            return new AnswerCmd(level, statRewards);
        }catch (Exception e){
            e.printStackTrace();
            getLogger().warning("Failed to convert " + newKey + " to new version!");
            return null;
        }
    }*/

    @Data
    @AllArgsConstructor
    public static class Answer{
        private int key;
        private List<String> value;
    }

    @Data
    @AllArgsConstructor
    public static class AnswerStat{
        private int key;
        private List<StatReward> value;
    }

    @Data
    @AllArgsConstructor
    public static class AnswerCmd{
        private int key;
        private List<CommandReward> value;
    }
}
