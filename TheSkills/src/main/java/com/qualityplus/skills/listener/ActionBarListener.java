package com.qualityplus.skills.listener;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent;
import com.qualityplus.assistant.base.event.ActionBarMessageEvent.ActionBarType;
import com.qualityplus.assistant.base.nms.AbstractNMS;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Utility class for action bar
 */
@Component
public final class ActionBarListener implements Listener {
    /**
     * Adds an on action bar
     *
     * @param event {@link ActionBarMessageEvent}
     */
    @EventHandler
    public void onActionBar(final ActionBarMessageEvent event) {
        final ActionBarType type = event.getType();

        //if (type != ActionBarType.GAME_INFO) return;

        ((AbstractNMS) TheAssistantPlugin.getAPI().getNms()).blacklist(event.getPlayer().getUniqueId());
    }
}
