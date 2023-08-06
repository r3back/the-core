package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

/**
 * Utility class for coal polisher master perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class CoalPolisherMasterPerk extends Perk {
    /**
     * Makes Coal Polisher Master Perk
     *
     * @param id                                 Id
     * @param enabled                            Enabled
     * @param displayName                        Display Name
     * @param description                        Description
     * @param skillGUIOptions                    {@link GUIOptions}
     * @param initialAmount                      Initial Amount
     * @param chancePerLevel                     Chance Per Level
     */
    @Builder
    public CoalPolisherMasterPerk(final String id,
                                  final boolean enabled,
                                  final String displayName,
                                  final List<String> description,
                                  final GUIOptions skillGUIOptions,
                                  final double initialAmount,
                                  final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);
    }

    /**
     * Adds an handle perk
     *
     * @param e {@link BlockBreakEvent}
     */
    @EventHandler(ignoreCancelled = true)
    public void handlePerk(final BlockBreakEvent e) {
        final Block block = e.getBlock();

        if (!block.getType().equals(XMaterial.COAL_ORE.parseMaterial())) {
            return;
        }

        final Player p = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= chancePerLevel * getStat(p)) {
            return;
        }
        e.setDropItems(false);

        block.getWorld().dropItem(block.getLocation(), XMaterial.DIAMOND.parseItem());
    }
}
