package com.qualityplus.skills.base.config.common;

import com.qualityplus.skills.base.skill.gui.GUIOptions;

import java.util.List;

/**
 * Interface for base file
 */
public interface BaseFile {
    /**
     * Adds a id
     *
     * @return Id
     */
    public String getId();

    /**
     * Adds a id
     *
     * @param id Id
     */
    public void setId(String id);

    /**
     * Add enabled
     *
     * @return Is Enabled
     */
    public boolean isEnabled();

    /**
     * Adds a enabled
     *
     * @param enabled Enabled
     */
    public void setEnabled(boolean enabled);

    /**
     * Adds a display name
     *
     * @return Display Name
     */
    public String getDisplayName();

    /**
     * Adds a display name
     *
     * @param displayName Display Name
     */
    public void setDisplayName(String displayName);

    /**
     * Adds a description
     *
     * @return Description
     */
    public List<String> getDescription();

    /**
     * Adds a description
     *
     * @param description Description
     */
    public void setDescription(List<String> description);

    /**
     * Adds a max level
     *
     * @return Max Level
     */
    public int getMaxLevel();

    /**
     * Adds a max level
     *
     * @param maxLevel Max Level
     */
    public void setMaxLevel(int maxLevel);

    /**
     * Adds a GUI
     *
     * @return {@link GUIOptions}
     */
    public GUIOptions getGuiOptions();

    /**
     * Adds a GUI
     *
     * @param guiOptions {@link GUIOptions}
     */
    public void setGuiOptions(GUIOptions guiOptions);
}
