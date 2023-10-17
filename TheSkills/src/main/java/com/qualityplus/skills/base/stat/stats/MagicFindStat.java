package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.api.util.BukkitItemUtil;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.base.event.PlayerKillEvent;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.event.MagicFindEvent;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for magic find stats
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class MagicFindStat extends Stat {
    private Map<XMaterial, Double> itemAndChances;
    private double chancePerLevel;

    /**
     * Makes a magic find stats
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param baseAmount      Base Amount
     * @param chancePerLevel  Chance Per Level
     * @param itemAndChances  Item And Chances
     */
    @Builder
    public MagicFindStat(final String id,
                         final boolean enabled,
                         final String displayName,
                         final List<String> description,
                         final GUIOptions skillGUIOptions,
                         final double baseAmount,
                         final double chancePerLevel,
                         final Map<XMaterial, Double> itemAndChances) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.chancePerLevel = chancePerLevel;
        this.itemAndChances = itemAndChances;
    }

    /**
     * Make an on kill
     *
     * @param e {@link PlayerKillEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onKill(final PlayerKillEvent e) {
        final Player player = e.getPlayer();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        if (this.itemAndChances == null || this.itemAndChances.size() == 0) {
            return;
        }

        final int level = getStat(player);

        if (RandomUtil.randomBetween(0.0, 100.0) >= this.chancePerLevel * level) {

            return;
        }

        final ItemStack toGive = Optional.ofNullable(RandomUtil.getRandom(this.itemAndChances))
                .map(XMaterial::parseItem)
                .orElse(null);

        if (BukkitItemUtil.isNull(toGive)) {
            return;
        }

        final MagicFindEvent event = new MagicFindEvent(e.getPlayer(), this, toGive, e.getKilled().getLocation());

        if (event.isCancelled()) {
            return;
        }

        Optional.ofNullable(event.getToDropItem()).ifPresent(item -> dropItem(event));
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level)),
                      new Placeholder("chance", this.chancePerLevel * level)
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }

    private void dropItem(final MagicFindEvent event) {
        Optional.ofNullable(event.getToDropLocation().getWorld())
                .ifPresent(world -> world.dropItem(event.getToDropLocation(), event.getToDropItem()));
    }
}
