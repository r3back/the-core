package com.qualityplus.skills.api.effect;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.*;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    protected final List<Class<? extends Listener>> extraListeners = new ArrayList<>();
    protected @Getter @Setter double initialAmount;

    public CommonObject(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double initialAmount) {
        this.id = id;
        this.enabled = enabled;
        this.initialAmount = initialAmount;
        this.displayName = displayName;
        this.description = description;
        this.guiOptions = skillGUIOptions;
    }

    public List<IPlaceholder> getPlaceholders(Integer level){
        return Collections.emptyList();
    }

    public List<String> getFormattedDescription(int level){
        return description;
    }

    public abstract void register();
}
