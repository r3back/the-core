package com.qualityplus.enchanting.listener;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.enchanting.base.event.TheEnchantingEnchantEvent;
import com.willfp.eco.core.Eco;
import com.willfp.eco.core.EcoPlugin;
import com.willfp.ecoskills.EcoSkillsPlugin;
import com.willfp.ecoskills.EcoSkillsPluginKt;
import com.willfp.ecoskills.api.EcoSkillsAPI;
import com.willfp.ecoskills.libreforge.loader.configs.RegistrableCategory;
import com.willfp.ecoskills.skills.Skill;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public final class EcoSkillsInternalListener implements Listener {
    @EventHandler
    public void onPlayerEnchant(final TheEnchantingEnchantEvent event) {
        final Player player = event.getPlayer();

        RegistrableCategory<Skill> skills = EcoSkillsPluginKt.getPlugin().getCategories()
        EcoSkillsAPI.resetSkills();
        EcoSkillsAPI.getSkillXP()
        EcoSkillsAPI.gainSkillXP();
    }
}
