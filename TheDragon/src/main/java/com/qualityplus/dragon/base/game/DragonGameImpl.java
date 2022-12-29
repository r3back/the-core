package com.qualityplus.dragon.base.game;

import com.qualityplus.assistant.api.addons.paster.session.PasterSession;
import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.TheDragonAPI;
import com.qualityplus.dragon.api.exception.InvalidSpawnException;
import com.qualityplus.dragon.api.exception.check.NoSpawnException;
import com.qualityplus.dragon.api.exception.check.NoStructureException;
import com.qualityplus.dragon.api.game.DragonGame;
import com.qualityplus.dragon.api.game.structure.type.DragonSpawn;
import com.qualityplus.dragon.api.service.StructureService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import eu.okaeri.tasker.core.Tasker;
import lombok.Getter;

import java.util.logging.Logger;

/**
 * Handles the whole Dragon Game
 */
@Getter
@Component
public final class DragonGameImpl implements DragonGame {
    private boolean active = false;
    private @Inject Logger logger;
    private @Inject Tasker tasker;

    /**
     * Start Dragon Game
     */
    @Override
    public void start(){
        if(!active) {
            TheDragonAPI api = TheDragon.getApi();

            active = true;

            try {
                api.getStructureService()
                        .pasteSchematic()
                        .thenAccept(this::runGame);
            }catch (InvalidSpawnException e){
                logger.warning("[TheDragon] Arena Spawn not found, cancelling game!");
                active = false;
            }catch (Exception e){
                e.printStackTrace();
                active = false;
            }
        }
    }

    /**
     * Finish Dragon Game
     */
    @Override
    public void finish() {
        if(active) {
            TheDragonAPI api = TheDragon.getApi();

            active = false;

            api.getDragonService().killDragon()
                    .thenRun(api.getStructureService()::deSpawnCrystals)
                    .thenRun(api.getUserService()::sendFinishMessage)
                    .thenRun(api.getUserService()::stopBossBar)
                    .thenRun(api.getUserService()::resetData)
                    .thenRun(api.getGameService()::stopSwitching);

        }
    }

    @Override
    public boolean canStart() throws NoSpawnException, NoStructureException {
        StructureService service = TheDragon.getApi().getStructureService();

        DragonSpawn spawn = service.getSpawn().get();

        if(spawn == null || spawn.getLocation() == null) throw new NoSpawnException();

        if(!service.schematicExist()) throw new NoStructureException();

        return true;
    }

    private void runGame(PasterSession session){
        TheDragonAPI api = TheDragon.getApi();

        api.getStructureService().clearAltars();
        api.getStructureService().spawnCrystals();
        api.getDragonService().selectDragon();

        api.getGameService()
                .startCountdown()
                .thenRun(() -> api.getGameService().makeBlockExplosion(session)
                .thenRun(this::startFinalPhase));

    }

    private void startFinalPhase(){
        TheDragonAPI api = TheDragon.getApi();

        api.getDragonService().spawnDragon();
        api.getUserService().startBossBar();
        api.getGameService().switchEvents();
    }
}
