package com.qualityplus.assistant.base.addons.placeholders;

import com.qualityplus.assistant.api.addons.replacer.PlaceholderReplaceEvent;
import com.qualityplus.assistant.api.addons.replacer.PlaceholderReplacer;
import com.qualityplus.assistant.api.addons.registrable.Registrable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import eu.okaeri.injector.annotation.Inject;
import org.jetbrains.annotations.NotNull;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public final class PlaceholderAPIAddon extends PlaceholderExpansion implements PlaceholdersAddon, Registrable {
    private final Map<String, PlaceholderReplacer> placeholdersMap = new HashMap<>();
    private @Inject Plugin plugin;

    @Override
    public @NotNull String getIdentifier() {
        return "thecore";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().stream()
                .findFirst()
                .map(Object::toString)
                .orElse("qualityplus");
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public void registerAddon() {
        super.register();
    }

    @Override
    public String getAddonName() {
        return "PlaceholderAPI";
    }

    @Override
    public void registerPlaceholders(String identifier, PlaceholderReplacer replacer) {
        placeholdersMap.put(identifier, replacer);
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null)
            return "";

        return placeholdersMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(identifier))
                .map(entry -> entry.getValue().onPlaceholderReplace(new PlaceholderReplaceEvent(player)))
                .findFirst()
                .orElse(null);
    }
}
