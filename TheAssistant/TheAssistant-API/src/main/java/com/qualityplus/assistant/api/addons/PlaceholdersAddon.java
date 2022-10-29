package com.qualityplus.assistant.api.addons;

import com.qualityplus.assistant.api.addons.replacer.PlaceholderReplacer;
import com.qualityplus.assistant.api.dependency.DependencyPlugin;

public interface PlaceholdersAddon extends DependencyPlugin {
    void registerPlaceholders(String identifier, PlaceholderReplacer replacer);
}
