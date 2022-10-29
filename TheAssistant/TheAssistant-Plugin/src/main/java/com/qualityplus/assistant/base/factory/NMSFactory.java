package com.qualityplus.assistant.base.factory;

import com.qualityplus.assistant.api.nms.NMS;
import com.qualityplus.assistant.base.nms.*;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Component
public final class NMSFactory {
    private @Inject("injector") OkaeriInjector injector;
    private @Inject Plugin plugin;
    private @Inject Logger logger;

    @Bean
    public NMS configureNMS() {
        String nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        MinecraftVersion version = MinecraftVersion.byName(nmsVersion);

        String toSend = nmsVersion
                .replace("v", "")
                .replace("_R1", "")
                .replace("_R2", "")
                .replace("_R3", "")
                .replace("_", ".");

        if(version.getNms() == null){
            logger.info("Unsupported Version " + toSend);
            logger.info("Disabling Plugin...");

            Bukkit.getServer().getPluginManager().disablePlugin(plugin);

            return null;
        }else{

            logger.info("Successfully recognized Version " + toSend);

            return injector.createInstance(version.getNms());
        }
    }

    public enum MinecraftVersion {
        v1_8_R1(() -> v1_8_R1.class),
        v1_8_R3(() -> v1_8_R3.class),
        v1_12_R1(() -> v1_12_R1.class),
        v1_13_R1(() -> v1_13_R1.class),
        v1_14_R1(() -> v1_14_R1.class),
        v1_15_R1(() -> v1_15_R1.class),
        V1_16_R1(() -> v1_16_R1.class),
        V1_16_R3(() -> v1_16_R3.class),
        V1_17_R1(() -> v1_17_R1.class),
        V1_18_R1(() -> v1_18_R1.class),
        V1_18_R2(() -> v1_18_R2.class),
        V1_19_R1(() -> v1_19_R1.class);

        private final Supplier<Class<? extends NMS>> nmsSupplier;

        MinecraftVersion(Supplier<Class<? extends NMS>> nmsSupplier) {
            this.nmsSupplier = nmsSupplier;
        }

        public Class<? extends NMS> getNms() {
            return nmsSupplier.get();
        }

        public static MinecraftVersion byName(String version) {

            return Arrays.stream(values()).filter(mcVersion -> mcVersion.name().equalsIgnoreCase(version)).findFirst().orElse(null);
        }
    }

}
