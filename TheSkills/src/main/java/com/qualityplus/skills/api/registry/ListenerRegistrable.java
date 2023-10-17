package com.qualityplus.skills.api.registry;

import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.base.skill.Skill;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Objects;

/**
 * Makes a listener registrable
 */
public interface ListenerRegistrable extends Listener {
    /**
     * Adds a list class
     *
     * @return List of{@link ExtraListener}
     */
    public List<Class<? extends ExtraListener>> getExtraListeners();

    /**
     * Adds a register listener
     *
     * @return List of {@link Listener}
     */
    public List<Listener> getRegisteredListeners();

    /**
     * Makes a register listener
     *
     * @param box {@link Box}
     */
    public default void registerListeners(Box box) {
        Bukkit.getPluginManager().registerEvents(this, box.plugin());

        getRegisteredListeners().add(this);

        for (Class<? extends ExtraListener> listener : getExtraListeners()) {
            final ExtraListener instance = box.inject().createInstance(listener);

            instance.applySkill((Skill) this);

            Bukkit.getPluginManager().registerEvents(instance, box.plugin());

            getRegisteredListeners().add(instance);
        }
    }

    /**
     * Makes a unregister listeners
     */
    public default void unregisterListeners() {
        getRegisteredListeners().stream().filter(Objects::nonNull).forEach(HandlerList::unregisterAll);

        getRegisteredListeners().clear();
    }

    /**
     * Adds a extra listener
     *
     * @param listener List of {@link ExtraListener}
     */
    public void addExtraListener(Class<? extends ExtraListener> listener);
}
