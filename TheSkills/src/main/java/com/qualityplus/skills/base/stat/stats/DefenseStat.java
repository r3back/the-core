package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.api.util.MathUtil;
import com.qualityplus.assistant.util.number.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.persistance.data.UserData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

/**
 * Extra Defense for players
 */
@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class DefenseStat extends Stat {
    private double damageReductionPercentagePerLevel;

    /**
     * Makes a defense stats
     *
     * @param id                                 Id
     * @param enabled                            Enabled
     * @param displayName                        Display Name
     * @param description                        Description
     * @param skillGUIOptions                    {@link GUIOptions}
     * @param baseAmount                         Base Amount
     * @param damageReductionPercentagePerLevel  Damage Reduction Percentage Per Level
     */
    @Builder
    public DefenseStat(final String id,
                       final boolean enabled,
                       final String displayName,
                       final List<String> description,
                       final GUIOptions skillGUIOptions,
                       final double baseAmount,
                       final double damageReductionPercentagePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.damageReductionPercentagePerLevel = damageReductionPercentagePerLevel;
    }

    /**
     * Adds an entity damage
     *
     * @param event {@link EntityDamageEvent}
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void handle(final EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        final Player player = (Player) event.getEntity();

        double multiplier = this.damageReductionPercentagePerLevel * TheSkills.getApi().getSkillsService().getData(player.getUniqueId())
                .map(UserData::getSkills)
                .map(userPerks -> userPerks.getLevel(id))
                .orElse(1);

        multiplier /= 100;
        multiplier += 1;

        event.setDamage(event.getDamage() / multiplier);
    }

    @Override
    public List<String> getFormattedDescription(final int level) {
        final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman(level)),
                      new Placeholder("percentage", MathUtil.round(this.damageReductionPercentagePerLevel * (double) level))
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
