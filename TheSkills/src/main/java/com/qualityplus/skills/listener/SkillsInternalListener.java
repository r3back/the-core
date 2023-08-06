package com.qualityplus.skills.listener;

import com.qualityplus.assistant.api.config.ConfigActionBar;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.sound.SoundUtils;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.base.config.Config;
import com.qualityplus.skills.base.event.SkillsLevelUPEvent;
import com.qualityplus.skills.base.event.SkillsXPGainEvent;
import com.qualityplus.skills.base.skill.Skill;
import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.skills.util.SkillsPlaceholderUtil;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for internal skills
 */
@Component
public final class SkillsInternalListener implements Listener {
    private @Inject Box box;

    /**
     * Adds a priority event
     *
     * @param event {@link EventPriority}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onXpGain(final SkillsXPGainEvent event) {
        final Player player = event.getPlayer();

        final UUID uuid = player.getUniqueId();

        final Optional<UserData> data = this.box.service().getData(uuid);

        Bukkit.getScheduler().runTask(this.box.plugin(), () -> {

            final List<IPlaceholder> placeholders = data.map(d -> SkillsPlaceholderUtil.getAllPlaceholders(d, event.getSkill()))
                    .map(PlaceholderBuilder::get)
                    .orElse(Collections.emptyList());

            SoundUtils.playSound(player, this.box.files().config().getGainXPSettings().getSound());

            final ConfigActionBar actionBar = this.box.files().config().getGainXPSettings().getActionBar();

            final String message = StringUtils.processMulti(actionBar.getMessage(), placeholders);

            if (actionBar.isEnabled() && event.isShowXpActionBar()) {
                this.box.actionBarService().sendActionBar(event.getPlayer(), message);
            }
        });
    }

    /**
     * Adds a priority event
     *
     * @param event {@link EventPriority}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onXpGain(final SkillsLevelUPEvent event) {
        final Player player = event.getPlayer();

        final UUID uuid = player.getUniqueId();

        final Optional<UserData> data = this.box.service().getData(uuid);

        final Skill skill = event.getSkill();

        Bukkit.getScheduler().runTask(this.box.plugin(), () -> {
            event.getSkill()
                    .getCommandRewards(event.getNewLevel())
                    .forEach(reward -> reward.execute(player));

            event.getSkill()
                    .getStatRewards(event.getNewLevel())
                    .forEach(reward -> reward.execute(player));

            final PlaceholderBuilder builder = data
                    .map(d -> SkillsPlaceholderUtil.getAllPlaceholders(d, skill))
                    .orElse(PlaceholderBuilder.create());

            final List<IPlaceholder> placeholders = builder
                    .with(new Placeholder("skill_info_message", StringUtils.processMulti(skill.getCachedMessage(event.getNewLevel() + 1), builder.get())))
                    .get();

            final Config.LevelUpSettings settings = this.box.files().config().getLevelUpSettings();

            SoundUtils.playSound(player, settings.getSound());

            final ConfigActionBar actionBar = settings.getActionBar();

            final String message = StringUtils.processMulti(actionBar.getMessage(), placeholders);

            if (settings.getMessage().isEnabled()) {
                StringUtils.processMulti(settings.getMessage().getMessages(), placeholders).forEach(msg -> player.sendMessage(StringUtils.color(msg)));
            }
            if (actionBar.isEnabled()) {
                this.box.actionBarService().sendActionBar(event.getPlayer(), message);
            }

        });
    }
}
