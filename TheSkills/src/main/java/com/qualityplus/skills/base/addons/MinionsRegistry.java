package com.qualityplus.skills.base.addons;

import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

@Component
public final class MinionsRegistry {
    private @Inject("injector") OkaeriInjector injector;
    //private @Inject DependencyResolver resolver;
    private @Inject Plugin plugin;
    private @Inject Logger logger;

    @Delayed(time = MinecraftTimeEquivalent.SECOND)
    public void configureMinions(){

       /* if(resolver.isPlugin("JetMinions")){
            //Bukkit.getPluginManager().registerEvents();
            sendMinionsPlugin("JetMinions");
        }else if(resolver.isPlugin("UltraMinions")){
            //Bukkit.getPluginManager().registerEvents();
            sendMinionsPlugin("UltraMinions");
        }*/
    }

    private void sendMinionsPlugin(String minionPlugin){
        logger.info("Successfully hooked into " + minionPlugin + "!");
    }
}
