package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.base.event.PlayerKillEvent;
import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

/**
 * Utility class for orb master perk
 */
@Getter
@Setter
@NoArgsConstructor
public final class OrbMasterPerk extends Perk {
    /**
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param initialAmount   Initial Amount
     * @param chancePerLevel  Chance Per Level
     */
    @Builder
    public OrbMasterPerk(final String id,
                         final boolean enabled,
                         final String displayName,
                         final List<String> description,
                         final GUIOptions skillGUIOptions,
                         final double initialAmount,
                         final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel);
    }

    /**
     * Adds a player chance event
     *
     * @param e {@link PlayerKillEvent}
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerChanceExpEvent(final PlayerKillEvent e) {
        if (!(e.getKilled() instanceof ExperienceOrb)) {
            return;
        }

        if (e.getKilled().hasMetadata("fortuneXpOrb")) {
            return;
        }

        final ExperienceOrb experienceOrb = (ExperienceOrb) e.getKilled();

        final Player player = e.getPlayer();

        if (RandomUtil.randomBetween(0.0, 100.0) >= chancePerLevel * getStat(player)) {
            return;
        }
        final ExperienceOrb orb = player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);

        orb.setMetadata("fortuneXpOrb", new FixedMetadataValue(TheSkills.getApi().getPlugin(), "fortuneXpOrb"));

        orb.setExperience(experienceOrb.getExperience());

    }
}
