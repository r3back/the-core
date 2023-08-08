package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.perks.common.AbstractRandomBlockDropPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Utility class for leaves master perk
 */
@NoArgsConstructor
public final class LeavesMasterPerk extends AbstractRandomBlockDropPerk {
    /**
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param initialAmount   Initial Amount
     * @param chancePerLevel  Chance Per Level
     * @param itemAndChances  Item And Chances
     */
    @Builder
    public LeavesMasterPerk(final String id,
                            final boolean enabled,
                            final String displayName,
                            final List<String> description,
                            final GUIOptions skillGUIOptions,
                            final double initialAmount,
                            final double chancePerLevel,
                            final Map<XMaterial, Double> itemAndChances) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, itemAndChances);
    }

    /**
     * Adds an handle perk
     *
     * @param e {@link PlayerInteractEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getClickedBlock() == null) {
            return;
        }

        final ItemStack inHand = e.getPlayer().getItemInHand();

        if (BukkitItemUtil.isNull(inHand)) {
            return;
        }

        if (!inHand.getType().equals(XMaterial.SHEARS.parseMaterial())) {
            return;
        }

        final Player p = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        final ItemStack toGive = getItem();

        if (BukkitItemUtil.isNull(toGive)) {
            return;
        }

        final Block block = e.getClickedBlock();

        block.getWorld().dropItem(block.getLocation(), toGive);
    }
}
