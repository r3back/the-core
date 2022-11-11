package com.qualityplus.skills.base.config.common;

import com.qualityplus.skills.base.skill.gui.GUIOptions;

import java.util.List;

public interface BaseFile {
    public String getId();

    public void setId(String id);

    public boolean isEnabled();

    public void setEnabled(boolean enabled);

    public String getDisplayName();

    public void setDisplayName(String displayName);

    public List<String> getDescription();

    public void setDescription(List<String> description);

    public int getMaxLevel();

    public void setMaxLevel(int maxLevel);

    public GUIOptions getGuiOptions();

    public void setGuiOptions(GUIOptions guiOptions);
}
