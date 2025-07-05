package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.base.event.EntityDamagedByPlayerEvent;
import com.qualityplus.assistant.base.event.PlayerKillEvent;
import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.api.util.MathUtil;
import com.qualityplus.assistant.api.util.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.VoxSkills;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public final class StrengthStat extends Stat {
    private double extraPercentageOfDamagePerLevel = 1;

    @Builder
    public StrengthStat(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double baseAmount, double extraPercentageOfDamagePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.extraPercentageOfDamagePerLevel = extraPercentageOfDamagePerLevel;
    }

    @Override
    public List<String> getFormattedDescription(double level) {
        List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman((int)level)),
                      new Placeholder("chance", 0),
                      new Placeholder("damage_percentage", 0)

                ).get();
        return StringUtils.processMulti(description, placeholders);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBrewEvent(final EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        final Player player = (Player) e.getDamager();

        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final double initialDamage = e.getDamage();
        final double level = getStat(player, this.id);

        final double finalValue = initialDamage * (1 + (this.extraPercentageOfDamagePerLevel / 100) * level);

        e.setDamage(finalValue);
    }
}
