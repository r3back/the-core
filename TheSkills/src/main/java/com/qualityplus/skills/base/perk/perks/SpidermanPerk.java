package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for spider perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class SpidermanPerk extends Perk {
    private final Map<Location, Material> oldBlocks = new HashMap<>();
    private boolean canBeUsedWithPlayers;
    private List<String> enabledWorlds;
    private int secondsDuration;

    /**
     *
     * @param id                    Id
     * @param enabled               Enabled
     * @param displayName           Display Name
     * @param description           Description
     * @param skillGUIOptions       {@link GUIOptions}
     * @param baseAmount            Base Amount
     * @param chancePerLevel        Chance Per Level
     * @param canBeUsedWithPlayers  Can Be Used With Players
     * @param enabledWorlds         Enabled Worlds
     * @param secondsDuration       Seconds Durations
     */
    @Builder
    public SpidermanPerk(final String id,
                         final boolean enabled,
                         final String displayName,
                         final List<String> description,
                         final GUIOptions skillGUIOptions,
                         final double baseAmount,
                         final double chancePerLevel,
                         final boolean canBeUsedWithPlayers,
                         final List<String> enabledWorlds,
                         final int secondsDuration) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount, chancePerLevel);

        this.canBeUsedWithPlayers = canBeUsedWithPlayers;
        this.chancePerLevel = chancePerLevel;
        this.enabledWorlds = enabledWorlds;
        this.secondsDuration = secondsDuration;
    }

    /**
     * Adds an entity damage
     *
     * @param e {@link EntityDamagedByPlayerEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(final EntityDamagedByPlayerEvent e) {
        final Player p = e.getPlayer();

        if (!this.enabledWorlds.contains(p.getWorld().getName())) {
            return;
        }

        if (!this.canBeUsedWithPlayers && e.getEntity() instanceof Player) {
            return;
        }

        if (RandomUtil.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p)) {
            return;
        }
        final Block block = e.getEntity().getLocation().getBlock();

        final Location location = block.getLocation();

        final Material before = Optional.of(block.getType()).orElse(Material.AIR);

        this.oldBlocks.put(location, before);

        block.setType(XMaterial.COBWEB.parseMaterial());

        Bukkit.getScheduler().runTaskLater(TheSkills.getApi().getPlugin(), () -> {
            Optional.ofNullable(this.oldBlocks.getOrDefault(location, null)).ifPresent(material -> location.getBlock().setType(material));
            this.oldBlocks.remove(location);
        }, this.secondsDuration * 20L);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        return StringUtils.processMulti(super.getFormattedDescription(level),
                PlaceholderBuilder.create(new Placeholder("duration", this.secondsDuration)).get());
    }
}
