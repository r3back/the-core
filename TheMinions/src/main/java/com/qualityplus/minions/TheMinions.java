package com.qualityplus.minions;

import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.minions.api.TheMinionsAPI;
import com.qualityplus.minions.api.minion.MinionEntity;
import com.qualityplus.minions.api.service.MinionsService;
import com.qualityplus.minions.base.gui.main.MainMinionGUI;
import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.entity.factory.MinionEntityFactory;
import com.qualityplus.minions.base.minions.Minions;
import com.qualityplus.minions.base.minions.entity.tracker.MinionEntityTracker;
import com.qualityplus.minions.persistance.MinionsRepository;
import com.qualityplus.minions.persistance.data.MinionData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.document.Document;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Scan(deep = true)
public final class TheMinions extends OkaeriSilentPlugin {
    private static @Inject @Getter TheMinionsAPI api;
    private static TheMinions INSTANCE;

    public static TheMinions getInstance() {
        return INSTANCE;
    }

    @Planned(ExecutionPhase.POST_STARTUP)
    private void whenStart() {
        INSTANCE = this;

        Bukkit.getScheduler().runTaskTimer(this, () -> Bukkit.getOnlinePlayers().forEach(player ->
                Optional.ofNullable(player.getOpenInventory().getTopInventory().getHolder())
                .filter(inventoryHolder -> inventoryHolder instanceof MainMinionGUI)
                .ifPresent(inventoryHolder -> ((MainMinionGUI) inventoryHolder).addItems())
        ),0, 1);
    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(@Inject Logger logger, @Inject MinionsService minionsService) {
        AtomicInteger countDown = new AtomicInteger(0);

        for (MinionEntity minionEntity : MinionEntityTracker.values()) {
            minionEntity.deSpawn(MinionEntity.DeSpawnReason.SERVER_TURNED_OFF);

            minionsService.getData(minionEntity.getMinionUniqueId()).ifPresent(Document::save);

            countDown.getAndIncrement();
        }

        logger.info(String.format("Plugin has saved %s minions to database!", countDown.get()));
    }



    @Delayed(time = 5)
    private void whenStart(@Inject Logger logger, @Inject MinionsRepository repository, @Inject MinionsService service) {
        Collection<MinionData> allData = repository.findAll();

        logger.info(String.format("Plugin has loaded %s minions from database!", allData.size()));

        allData.forEach(data -> {
            if(data.getLocation() != null) {

                Minion minion = Minions.getByID(data.getMinionId());

                if(minion == null){
                    logger.warning("Failed to load minion " + data.getMinionId());
                    return;
                }

                service.addData(data);

                MinionEntity entity = MinionEntityFactory.create(data.getUuid(), data.getOwner(), minion);

                entity.spawn(data.getLocation());
            }

        });
    }
}
