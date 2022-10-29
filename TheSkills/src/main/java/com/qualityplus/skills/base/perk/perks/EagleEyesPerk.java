package com.qualityplus.skills.base.perk.perks;

import com.cryptomorin.xseries.XPotion;
import com.qualityplus.assistant.base.event.PlayerDamagedByEntityEvent;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.skills.base.perk.perks.common.AbstractPotionPerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public final class EagleEyesPerk extends AbstractPotionPerk {
    @Builder
    public EagleEyesPerk(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount, double chancePerLevel, int secondsDurationPerLevel,
                         int baseSecondsDuration, int level) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, secondsDurationPerLevel, baseSecondsDuration, level);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handlePerk(PlayerDamagedByEntityEvent e) {
        Player p = e.getPlayer();

        if (MathUtils.randomBetween(0.0, 100.0) >= getChancePerLevel() * getStat(p))
            return;

        Optional.of(XPotion.NIGHT_VISION)
                .map(potion -> potion.buildPotionEffect(getDurationTicks(getStat(p)), getLevel()))
                .ifPresent(p::addPotionEffect);
    }
}
