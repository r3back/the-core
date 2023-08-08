package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Utility class for refurbished perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class RefurbishedPerk extends Perk {
    private List<XMaterial> toolList;

    /**
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param baseAmount      Base Amount
     * @param chancePerLevel  Chance Per Level
     * @param toolList        list of {@link XMaterial}
     */
    @Builder
    public RefurbishedPerk(final String id,
                           final boolean enabled,
                           final String displayName,
                           final List<String> description,
                           final GUIOptions skillGUIOptions,
                           final double baseAmount,
                           final double chancePerLevel,
                           final List<XMaterial> toolList) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount, chancePerLevel);

        this.toolList = toolList;
    }

    /**
     * Adds a handle perk
     *
     * @param e {@link PlayerInteractEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        final ItemStack inHand = p.getItemInHand();

        if (BukkitItemUtil.isNull(inHand)) {
            return;
        }

        if (!this.toolList.contains(XMaterial.matchXMaterial(inHand.getType()))) {
            return;
        }

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        final NMS nms = TheAssistantPlugin.getAPI().getNms();

        final short durability = inHand.getType().getMaxDurability();

        Bukkit.getScheduler().runTask(TheSkills.getApi().getPlugin(), () -> p.setItemInHand(nms.setDurability(inHand, durability)));

    }
}
