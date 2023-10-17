package com.qualityplus.skills.base.perk.perks.common;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.block.BlockUtils;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.event.ItemFortunePerkEvent;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for abstract fortune perk
 */
@NoArgsConstructor
public abstract class AbstractFortuneBlockPerk extends AbstractFortunePerk {
    /**
     *
     * @param id               Id
     * @param enabled          Enabled
     * @param displayName      Display Name
     * @param description      Description
     * @param skillGUIOptions  {@link GUIOptions}
     * @param baseAmount       Base Amount
     * @param chancePerLevel   Chance Per Level
     * @param allowedMaterials Allowed Materials
     */
    public AbstractFortuneBlockPerk(final String id,
                                    final boolean enabled,
                                    final String displayName,
                                    final List<String> description,
                                    final GUIOptions skillGUIOptions,
                                    final double baseAmount,
                                    final double chancePerLevel,
                                    final List<XMaterial> allowedMaterials) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount, chancePerLevel, allowedMaterials);
    }

    protected boolean isAllowedMaterial(final BlockBreakEvent e) {

        final Player player = e.getPlayer();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return false;
        }

        final XMaterial material = XMaterial.matchXMaterial(e.getBlock().getType());

        return getAllowedMaterials().contains(material);
    }

    /**
     * Adds an on break
     *
     * @param event {@link BlockBreakEvent}
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void  onBreak(final BlockBreakEvent event) {
        if (!isAllowedMaterial(event)) {
            return;
        }

        final Player player = event.getPlayer();

        final Block block = event.getBlock();

        if (BlockUtils.isPlacedByPlayer(block)) {
            return;
        }
        final int level = getStat(player);

        final double chance = getChance(level);

        int additional = getMultiplier(level) - 2;

        if (additional <= 0) {
            return;
        }
        if (RandomUtil.randomBetween(0.0, 100.0) < chance) {
            additional++;
        }
        final ItemFortunePerkEvent fortunePerkEvent = new ItemFortunePerkEvent(player, this,
                new ArrayList<>(event.getBlock().getDrops()), event.getBlock().getLocation());

        Bukkit.getPluginManager().callEvent(fortunePerkEvent);

        if (fortunePerkEvent.getToDropItems().isEmpty() || fortunePerkEvent.isCancelled()) {
            return;
        }
        final int finalAdditional = additional;

        fortunePerkEvent.getToDropItems().forEach(item -> block.getWorld().dropItem(block.getLocation(), getModified(item, finalAdditional)));
    }
}
