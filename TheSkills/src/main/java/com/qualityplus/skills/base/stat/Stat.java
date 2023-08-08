package com.qualityplus.skills.base.stat;

import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.api.registry.ListenerRegistrable;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.registry.Stats;
import com.qualityplus.skills.persistance.data.UserData;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;


import java.util.List;

/**
 * Utility class for stat
 */
@NoArgsConstructor
public abstract class Stat extends CommonObject implements ListenerRegistrable {
    /**
     * Makes a Stat
     *
     * @param id              Id
     * @param enabled         Enabled
     * @param displayName     Display Name
     * @param description     Description
     * @param skillGUIOptions {@link GUIOptions}
     * @param baseAmount      Base Amount
     */
    public Stat(final String id,
                final boolean enabled,
                final String displayName,
                final List<String> description,
                final GUIOptions skillGUIOptions,
                final double baseAmount) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);
    }

    @Override
    public void register() {
        Stats.registerNewStat(this);
    }

    @Override
    public void addExtraListener(final Class<? extends ExtraListener> listener) {
        extraListeners.add(listener);
    }

    /**
     * Adds a player
     *
     * @param player {@link Player}
     * @param id     Id
     * @return {@link Stat}
     */
    public int getStat(final Player player, final String id) {
        return TheSkills.getApi().getSkillsService().getData(player.getUniqueId())
                .map(UserData::getSkills)
                .map(userPerks -> userPerks.getLevel(id))
                .orElse(1);
    }

    /**
     * Adds a player
     *
     * @param player {@link Player}
     * @return {@link Stat}
     */
    public int getStat(final Player player) {
        return getStat(player, id);
    }
}
