package com.qualityplus.skills.base.perk.perks;

import com.qualityplus.assistant.util.random.RandomUtil;
import com.qualityplus.skills.base.event.EntityFortunePerkEvent;
import com.qualityplus.skills.base.perk.perks.common.AbstractFortunePerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for sea fortune perk
 */
@NoArgsConstructor
public final class SeaFortunePerk extends AbstractFortunePerk {
    /**
     *
     * @param id               Id
     * @param enabled          Enabled
     * @param displayName      Display Name
     * @param description      Description
     * @param skillGUIOptions  {@link GUIOptions}
     * @param initialAmount    Initial Amount
     * @param chancePerLevel   Chance Per Level
     */
    @Builder
    public SeaFortunePerk(final String id,
                          final boolean enabled,
                          final String displayName,
                          final List<String> description,
                          final GUIOptions skillGUIOptions,
                          final double initialAmount,
                          final double chancePerLevel) {
        super(id, enabled, displayName, description, skillGUIOptions, initialAmount, chancePerLevel, new ArrayList<>());
    }

    /**
     * Adds an on break
     *
     * @param e {@link PlayerFishEvent}
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBreak(final PlayerFishEvent e) {
        final Player player = e.getPlayer();

        final PlayerFishEvent.State state = e.getState();

        if (e.getCaught() == null || !(e.getCaught() instanceof Item)) {
            return;
        }

        if (state != PlayerFishEvent.State.CAUGHT_FISH && state != PlayerFishEvent.State.CAUGHT_ENTITY) {
            return;
        }

        if (state == PlayerFishEvent.State.CAUGHT_ENTITY) {
            return;
        }

        final int level = getStat(player);

        final double chance = getChance(level);

        double additional = getMultiplier(level) - 2;

        if (additional <= 0) {
            return;
        }
        if (RandomUtil.randomBetween(0.0, 100.0) < chance) {
            additional++;
        }
        final EntityFortunePerkEvent fortunePerkEvent = new EntityFortunePerkEvent(player, this, e.getCaught(), e.getPlayer().getLocation());

        Bukkit.getPluginManager().callEvent(fortunePerkEvent);

        if (fortunePerkEvent.getToDropEntity().isEmpty() || fortunePerkEvent.isCancelled()) {
            return;
        }
        final int finalAdditional = (int) additional;

        dropVarious(fortunePerkEvent.getToDropLocation(), fortunePerkEvent.getToDropEntity(), finalAdditional);
    }

    private void dropVarious(final Location location, final Entity entity, final int additional) {
        for (int i = 0; i < additional; i++) {
            Optional.ofNullable(location.getWorld()).ifPresent(world -> world.dropItem(location, ((Item) entity).getItemStack()));
        }
    }
}
