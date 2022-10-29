package com.qualityplus.bank;

import com.qualityplus.bank.api.TheBankAPI;
import com.qualityplus.assistant.okaeri.OkaeriSilentPlugin;
import com.qualityplus.bank.api.box.Box;
import com.qualityplus.bank.persistence.data.BankData;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Scan;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

@Scan(deep = true)
public final class TheBank extends OkaeriSilentPlugin {
    private static @Inject @Getter TheBankAPI api;

    @Planned(ExecutionPhase.PRE_SHUTDOWN)
    private void whenStop(Box box){
        Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getUniqueId)
                .map(box.service()::getBankData)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(BankData::save);
    }
}
