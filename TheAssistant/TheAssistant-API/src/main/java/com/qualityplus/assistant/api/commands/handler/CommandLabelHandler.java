package com.qualityplus.assistant.api.commands.handler;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.assistant.api.commands.LabelProvider;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class CommandLabelHandler {
    private static final Map<String, LabelProvider> LABEL_REGISTRY = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewLabel(@NotNull final LabelProvider provider) {
        LABEL_REGISTRY.put(provider.getId().toLowerCase(), provider);
    }

    @Nullable
    public static LabelProvider getByID(@NotNull final String id) {
        return LABEL_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static LabelProvider getByKey(@NotNull final NamespacedKey key) {
        return LABEL_REGISTRY.get(key.getKey());
    }

    public static Set<LabelProvider> values() {
        return ImmutableSet.copyOf(LABEL_REGISTRY.values());
    }
}
