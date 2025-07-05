package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.api.util.MathUtil;
import com.qualityplus.assistant.api.util.NumberUtil;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.util.SkillsPlayerUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class SpeedStat extends Stat {
    private double extraSpeedPercentagePerLevel;

    @Builder
    public SpeedStat(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double baseAmount, double extraSpeedPercentagePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);

        this.extraSpeedPercentagePerLevel = extraSpeedPercentagePerLevel;
    }

    @Override
    public List<String> getFormattedDescription(double level) {
        List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("level_number", level),
                      new Placeholder("level_roman", NumberUtil.toRoman((int)level)),
                      new Placeholder("extra_speed", level * extraSpeedPercentagePerLevel)

                ).get();
        return StringUtils.processMulti(description, placeholders);
    }

    @EventHandler
    public void onBrewEvent(final PlayerMoveEvent e) {
        final Player player = e.getPlayer();
        if (!SkillsPlayerUtil.isInSurvival(player)) {
            return;
        }

        final double level = getStat(player, this.id);
        final float finalValue = (float) (level * extraSpeedPercentagePerLevel);

        if (finalValue == 0 || finalValue == 1) {
            player.setWalkSpeed(0.2f);
            player.setFlySpeed(0.1f);
            return;
        }


        if (finalValue > 1 || finalValue < -1) {
            Bukkit.getConsoleSender().sendMessage(StringUtils.color("&c&lWARNING: &cInvalid Speed at VoxSkills: " + finalValue));
            player.setWalkSpeed(0.2f);
            player.setFlySpeed(0.1f);
            return;
        }
        player.setWalkSpeed(0.2f + finalValue);
        player.setFlySpeed(0.1f + finalValue);
    }
}
