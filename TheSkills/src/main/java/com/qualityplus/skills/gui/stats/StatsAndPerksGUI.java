package com.qualityplus.skills.gui.stats;

import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.player.PlayerUtils;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.registry.Perks;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.gui.SkillsGUI;
import com.qualityplus.skills.gui.main.MainGUI;
import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.skills.util.SkillsItemStackUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * Utility class for stats and perks gui
 */
public final class StatsAndPerksGUI extends SkillsGUI {
    private final StatsAndPerksGUIConfig config;
    private final GUIType type;
    private final UUID uuid;

    /**
     * Makes a Stats ans perks gui
     *
     * @param box    {@link Box}
     * @param player {@link Player}
     * @param page   Page
     * @param type   {@link GUIType}
     */
    public StatsAndPerksGUI(final Box box, final Player player, final int page, final GUIType type) {
        super(box.files().inventories().getStatsAndPerksGUI(), box);

        this.hasNext = Stats.values(CommonObject::isEnabled).stream().anyMatch(stat -> stat.getGuiOptions().getPage() > page);
        this.config = box.files().inventories().getStatsAndPerksGUI();
        this.uuid = player.getUniqueId();
        this.type = type;
        this.page = page;
    }

    @Override
    public @NotNull Inventory getInventory() {
        fillInventory(this.config);

        setItem(this.config.getCloseGUI());
        setItem(this.config.goBack);

        if (page > 1) {
            setItem(this.config.previousPage);
        }
        if (hasNext) {
            setItem(this.config.nextPage);
        }
        final UserData data = box.service().getData(this.uuid).orElse(new UserData());

        if (this.type.equals(GUIType.STAT)) {
            setItem(this.config.statInfoItem, Collections.singletonList(new Placeholder("player", PlayerUtils.getPlayerName(this.uuid))));

            for (Stat stat : Stats.values(CommonObject::isEnabled)) {
                final GUIOptions options = stat.getGuiOptions();

                if (options.getPage() != this.page) {
                    continue;
                }

                inventory.setItem(options.getSlot(), SkillsItemStackUtil.makeItem(this.config.statItem, Arrays.asList(
                        new Placeholder("stat_displayname", stat.getDisplayName()),
                        new Placeholder("stat_description", stat.getFormattedDescription(data.getSkills().getLevel(stat.getId())))
                ), options));
            }
        } else {
            setItem(this.config.perkInfoItem, Collections.singletonList(new Placeholder("player", PlayerUtils.getPlayerName(this.uuid))));

            for (Perk perk : Perks.values(CommonObject::isEnabled)) {
                final GUIOptions options = perk.getGuiOptions();

                if (options.getPage() != this.page) {
                    continue;
                }
                inventory.setItem(options.getSlot(), SkillsItemStackUtil.makeItem(this.config.perkItem, Arrays.asList(
                        new Placeholder("perk_displayname", perk.getDisplayName()),
                        new Placeholder("perk_description", perk.getFormattedDescription(data.getSkills().getLevel(perk.getId())))
                ), options));
            }

        }


        setItem(this.config.switchMode,
                Collections.singletonList(new Placeholder("current_mode",
                        this.type.equals(GUIType.STAT) ? box
                                .files().messages().getSkillsMessages()
                                .getStatMode() : box.files().messages()
                                .getSkillsMessages().getPerkMode())));

        return inventory;
    }


    @Override
    public void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        final int slot = event.getSlot();

        if (isItem(slot, this.config.getCloseGUI())) {
            player.closeInventory();
        } else if (isItem(slot, this.config.goBack)) {
            player.openInventory(new MainGUI(box, player).getInventory());
        } else if (isItem(slot, this.config.previousPage) && page > 1) {
            player.openInventory(new StatsAndPerksGUI(box, player, page - 1, this.type).getInventory());
        } else if (isItem(slot, this.config.nextPage) && hasNext) {
            player.openInventory(new StatsAndPerksGUI(box, player, page + 1, this.type).getInventory());
        } else if (isItem(slot, this.config.nextPage) && hasNext) {
            player.openInventory(new StatsAndPerksGUI(box, player, page + 1, this.type).getInventory());
        } else if (isItem(slot, this.config.switchMode)) {
            player.openInventory(new StatsAndPerksGUI(box, player, 1, this.type.equals(GUIType.STAT) ? GUIType.PERK : GUIType.STAT).getInventory());
        }
    }

    /**
     * Adds a GUI Type
     */
    public enum GUIType {
        PERK,
        STAT
    }
}
