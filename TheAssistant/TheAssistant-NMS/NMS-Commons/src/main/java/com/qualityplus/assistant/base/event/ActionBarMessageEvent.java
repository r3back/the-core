package com.qualityplus.assistant.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public final class ActionBarMessageEvent extends PlayerHelperEvent {
    private final ActionBarType type;
    private final String message;

    public ActionBarMessageEvent(@NotNull Player who, String message, ActionBarType type) {
        super(who);

        this.message = message;
        this.type = type;
    }

    public enum ActionBarType{
        GAME_INFO,
        ACTION_BAR_TEXT;
    }
}
