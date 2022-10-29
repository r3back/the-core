package com.qualityplus.pets.gui.main;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.pets.api.box.Box;
import com.qualityplus.pets.gui.PetsGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class PetMainGUI extends PetsGUI {
    private final PetsGUIConfig config;
    private final String name;

    public PetMainGUI(Box box, Player player) {
        super(box.files().inventories().mainGUIConfig, box);

        this.config = box.files().inventories().mainGUIConfig;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(config);

        setItem(config.getCloseGUI());

        /*UserData data = box.service().getSkillsData(uuid).orElse(new UserData());

        for(Skill skill : Skills.values(Skill::isEnabled))
            Optional.ofNullable(skill.getGuiOptions()).ifPresent(option -> inventory.setItem(option.getSlot(), getItem(data, skill, option)));

        setItem(config.getPlayerInfoItem(), Collections.singletonList(new Placeholder("player", name)));*/

        return inventory;
    }

    /*private ItemStack getItem(UserData data, Skill skill, GUIOptions option){
        return SkillsItemStackUtil.makeItem(config.getSkillsItem(), getPlaceholders(data, skill, option), option);
    }

    private List<IPlaceholder> getPlaceholders(UserData data, Skill skill, GUIOptions option){
        return SkillsPlaceholderUtil.getAllPlaceholders(data, skill)
                .with(new Placeholder("skill_info_gui", StringUtils.processMulti(option.getMainMenuLore(), SkillsPlaceholderUtil.getAllPlaceholders(data, skill).get())))
                .get();
    }*/

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();

        /*if(isItem(slot, config.getCloseGUI())) {
            player.closeInventory();
        }else if(isItem(slot, config.getPlayerInfoItem())){
            player.openInventory(new StatsAndPerksGUI(box, player, 1, StatsAndPerksGUI.GUIType.STAT).getInventory());
        }else{
            Optional<Skill> optionalSkill = Skills.values(Skill::isEnabled).stream().filter(s -> s.getGuiOptions().getSlot() == slot).findFirst();

            optionalSkill.ifPresent(s -> player.openInventory(new SkillGUI(box, player, s, 1).getInventory()));
        }*/

    }
}
