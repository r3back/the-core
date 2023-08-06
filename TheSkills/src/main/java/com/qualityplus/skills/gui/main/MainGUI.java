package com.qualityplus.skills.gui.main;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.skill.registry.Skills;
import com.qualityplus.skills.gui.SkillsGUI;
import com.qualityplus.skills.gui.stats.StatsAndPerksGUI;
import com.qualityplus.skills.gui.sub.SkillGUI;
import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.skills.util.SkillsItemStackUtil;
import com.qualityplus.skills.util.SkillsPlaceholderUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for main gui
 */
public final class MainGUI extends SkillsGUI {
    private final MainGUIConfig config;
    private final String name;

    /**
     * Makes a main gui
     *
     * @param box    {@link Box}
     * @param player {@link Player}
     */
    public MainGUI(final Box box, final Player player) {
        super(box.files().inventories().getMainGUIConfig(), box);

        this.config = box.files().inventories().getMainGUIConfig();
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);

        setItem(this.config.getCloseGUI());

        final UserData data = box.service().getData(uuid).orElse(new UserData());

        for (Skill skill : Skills.values(Skill::isEnabled)) {
            Optional.ofNullable(skill.getGuiOptions()).ifPresent(option -> inventory.setItem(option.getSlot(), getItem(data, skill, option)));

            setItem(this.config.getPlayerInfoItem(), Collections.singletonList(new Placeholder("player", this.name)));

            Optional.ofNullable(this.config.getCustomGoBackItem()).ifPresent(this::setItem);
        }

        return inventory;
    }

    private ItemStack getItem(final UserData data, final Skill skill, final GUIOptions option) {
        return SkillsItemStackUtil.makeItem(this.config.getSkillsItem(), getPlaceholders(data, skill, option), option);
    }

    private List<IPlaceholder> getPlaceholders(final UserData data, final Skill skill, final GUIOptions option) {
        return SkillsPlaceholderUtil.getAllPlaceholders(data, skill)
                .with(new Placeholder("skill_info_gui",
                        StringUtils.processMulti(option
                        .getMainMenuLore(), SkillsPlaceholderUtil
                        .getAllPlaceholders(data, skill).get())))
                .get();
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getPlayerInfoItem())) {
            player.openInventory(new StatsAndPerksGUI(box, player, 1, StatsAndPerksGUI.GUIType.STAT).getInventory());
        } else if (isItem(slot, this.config.getCustomGoBackItem())) {
            handleItemCommandClick(player, this.config.getCustomGoBackItem());
        } else {
            final Optional<Skill> optionalSkill = Skills.values(Skill::isEnabled).stream().filter(s -> s.getGuiOptions().getSlot() == slot).findFirst();

            optionalSkill.ifPresent(s -> player.openInventory(new SkillGUI(box, player, s, 1).getInventory()));
        }

    }
}
