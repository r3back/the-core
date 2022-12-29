package com.qualityplus.assistant.okaeri.serdes;

import com.qualityplus.assistant.okaeri.serdes.itemstack.CraftItemStackSerializer;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.SerdesRegistry;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.LocationSerializer;
import eu.okaeri.configs.yaml.bukkit.serdes.serializer.PotionEffectSerializer;
import eu.okaeri.configs.yaml.bukkit.serdes.transformer.StringEnchantmentTransformer;
import eu.okaeri.configs.yaml.bukkit.serdes.transformer.StringPotionEffectTypeTransformer;
import eu.okaeri.configs.yaml.bukkit.serdes.transformer.StringWorldTransformer;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public final class SerdesAssistantBukkit implements OkaeriSerdesPack {
    @Override
    public void register(@NonNull SerdesRegistry registry) {
        if (registry == null) {
            throw new NullPointerException("registry is marked non-null but is null");
        } else {
            registry.register(new CraftItemStackSerializer());
            registry.register(new LocationSerializer());
            registry.register(new PotionEffectSerializer());
            registry.register(new StringEnchantmentTransformer());
            registry.register(new StringPotionEffectTypeTransformer());
            registry.register(new StringWorldTransformer());
        }
    }
}
