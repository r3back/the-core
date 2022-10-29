package com.qualityplus.skills.listener;

import com.qualityplus.assistant.base.event.ArmorEquipEvent;
import com.qualityplus.assistant.util.itemstack.ItemStackUtils;
import com.qualityplus.skills.TheSkills;
import com.qualityplus.skills.api.box.Box;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


@Component
public final class ArmorStatsListener implements Listener {
    private @Inject Box box;

    @EventHandler(ignoreCancelled = true)
    public void onEquip(ArmorEquipEvent e){
        Player player = e.getPlayer();

        if(!ItemStackUtils.isNull(e.getNewArmorPiece()))
            TheSkills.getApi()
                    .getItemStats(e.getNewArmorPiece())
                    .forEach((key, value1) -> box.service().getSkillsData(player.getUniqueId())
                    .ifPresent(data -> data.getSkills().addArmor(key.getId(), value1)));


        if(!ItemStackUtils.isNull(e.getOldArmorPiece()))
            TheSkills.getApi()
                    .getItemStats(e.getOldArmorPiece())
                    .forEach((key, value1) -> box.service().getSkillsData(player.getUniqueId())
                    .ifPresent(data -> data.getSkills().removeArmor(key.getId(), value1)));

    }
}
