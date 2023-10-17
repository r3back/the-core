package com.qualityplus.skills.gui.sub;

import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.gui.SkillsGUI;
import com.qualityplus.skills.gui.main.MainGUI;
import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.skills.util.SkillsItemStackUtil;
import com.qualityplus.skills.util.SkillsPlaceholderUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for skills gui
 */
public final class SkillGUI extends SkillsGUI {
    private final SubGUIConfig config;
    private final Skill skill;

    private static int getMaxPage(final Skill skill, final Box box) {
        return (int) Math.ceil((double) skill.getMaxLevel() / (double) box.files().inventories().getSubGUIConfig().getLevelSlots().size());
    }

    /**
     * Makes a skill gui
     *
     * @param box    {@link Box}
     * @param player {@link Player}
     * @param skill  {@link Skill}
     * @param page   Page
     */
    public SkillGUI(final Box box, final Player player, final Skill skill, final int page) {
        super(box.files().inventories().getSubGUIConfig().getSize(),
              StringUtils.processMulti(box.files().inventories().getSubGUIConfig().getTitle(), Arrays.asList(
                      new Placeholder("category", skill.getDisplayName()),
                      new Placeholder("current_page", page),
                      new Placeholder("max_page", getMaxPage(skill, box) )
              )), box);

        this.hasNext = skill.getMaxLevel() > box.files().inventories().getSubGUIConfig().getLevelSlots().size() * page;
        this.config = box.files().inventories().getSubGUIConfig();
        this.uuid = player.getUniqueId();
        this.skill = skill;
        this.page = page;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);

        setItem(this.config.getCloseGUI());

        final UserData data = box.service().getData(uuid).orElse(new UserData());

        final int level = data.getSkills().getLevel(this.skill.getId());

        final int maxPerPage = this.config.getLevelSlots().size();

        int count = (maxPerPage * page) - maxPerPage;

        inventory.setItem(this.config.getCategoryItem().slot, SkillsItemStackUtil.makeItem(
                this.config.getCategoryItem(),
                SkillsPlaceholderUtil.getSkillsPlaceholders(data, this.skill),
                this.skill.getGuiOptions()));

        for (Integer slot : this.config.getLevelSlots()) {
            count++;

            if (count > this.skill.getMaxLevel()) {
                break;

            }
            final Item item = count == level + 1 ? this.config.getInProgressItem() :
                        count > level ? this.config.getLockedItem() : this.config.getUnlockedItem();

            inventory.setItem(slot, getItem(item, data, this.skill, count));

        }

        if (page > 1) {
            setItem(this.config.getPreviousPage());
        }

        if (hasNext) {
            setItem(this.config.getNextPage());

            setItem(this.config.getGoBack());
        }

        return inventory;
    }

    private ItemStack getItem(final Item item, final UserData data, final Skill skill, final int level) {
        final PlaceholderBuilder builder = SkillsPlaceholderUtil.getAllPlaceholders(data, skill, level);

        final List<String> loreInGui = skill.getCachedGUI(level);

        return ItemStackUtils.makeItem(item, builder
                .with(new Placeholder("skill_info_gui", StringUtils.processMulti(loreInGui, builder.get())))
                .get());
    }

    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.getGoBack())) {
            player.openInventory(new MainGUI(box, player).getInventory());
        } else if (isItem(slot, this.config.getPreviousPage()) && page > 1) {
            player.openInventory(new SkillGUI(box, player, this.skill, page - 1).getInventory());
        } else if (isItem(slot, this.config.getNextPage()) && hasNext) {
            player.openInventory(new SkillGUI(box, player, this.skill, page + 1).getInventory());
        }
    }
}
