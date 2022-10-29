package com.qualityplus.assistant.api.addons.replacer;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;

@Getter
@Setter
public final class PlaceholderReplaceEvent {
    private OfflinePlayer player = null;

    public PlaceholderReplaceEvent(OfflinePlayer offlinePlayer) {
        this.player = offlinePlayer;
    }
}
