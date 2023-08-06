package com.qualityplus.skills.api.effect;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.skills.api.listener.ExtraListener;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common onbject
 */
@NoArgsConstructor
@Getter
@Setter
public abstract class CommonObject {
    protected String id;
    protected boolean enabled;
    protected String displayName;
    protected List<String> description;
    protected GUIOptions guiOptions;
    protected final List<Listener> registeredListeners = new ArrayList<>();
    protected final List<Class<? extends ExtraListener>> extraListeners = new ArrayList<>();

    protected @Getter @Setter double initialAmount;

    /**
     * Makes a common object
     *
     * @param id                        Id
     * @param enabled                  Enabled
     * @param displayName              Display Name
     * @param description              Description
     * @param skillGUIOptions          {@link GUIOptions}
     * @param initialAmount            Initial Amount
     */
    public CommonObject(final String id,
                        final boolean enabled,
                        final String displayName,
                        final List<String> description,
                        final GUIOptions skillGUIOptions,
                        final double initialAmount) {
        this.id = id;
        this.enabled = enabled;
        this.initialAmount = initialAmount;
        this.displayName = displayName;
        this.description = description;
        this.guiOptions = skillGUIOptions;
    }

    /**
     * Adds a list placeholder
     *
     * @param level Level
     * @return      List of {@link IPlaceholder}
     */
    public List<IPlaceholder> getPlaceholders(final Integer level) {
        return Collections.emptyList();
    }

    /**
     * Adds a description
     *
     * @param level Level
     * @return      Formatted Description
     */
    public List<String> getFormattedDescription(final int level) {
        return this.description;
    }

    /**
     * Adds a register
     */
    public abstract void register();
}
