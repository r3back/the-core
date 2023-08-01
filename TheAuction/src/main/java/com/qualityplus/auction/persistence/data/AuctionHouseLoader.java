package com.qualityplus.auction.persistence.data;

import com.qualityplus.assistant.lib.eu.okaeri.commands.bukkit.annotation.Async;
import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.auction.api.service.AuctionService;
import com.qualityplus.auction.persistence.AuctionRepository;
import com.qualityplus.assistant.lib.eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;;

import com.qualityplus.assistant.lib.eu.okaeri.platform.bukkit.annotation.Delayed;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.ExecutionPhase;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.plan.Planned;

import java.util.logging.Logger;

@Component
public final class AuctionHouseLoader {
    @Async
    @Delayed(time = MinecraftTimeEquivalent.SECOND / 20, async = true)
    public void load(@Inject AuctionService service, @Inject AuctionRepository repository, @Inject Logger logger) {
        AuctionHouse auctionHouse = repository.get();

        service.setAuctionHouse(auctionHouse);

        auctionHouse.save();

        logger.info("Loaded " + auctionHouse.getNormalItems().size() + " Auctions!");

    }

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    public void unload(@Inject AuctionService service, @Inject Logger logger) {
        logger.info("Saving Auction House...");

        service.getAuctionHouse().ifPresent(AuctionHouse::save);
    }
}
