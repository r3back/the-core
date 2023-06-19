package com.qualityplus.bank.base.factory;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.bank.api.handler.TrxHandler;
import com.qualityplus.bank.base.config.BankUpgrades;
import com.qualityplus.bank.base.handler.DepositHandler;
import com.qualityplus.bank.base.handler.WithdrawHandler;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Bean;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;

@Component
public final class TrxHandlerFactory {
    @Bean("deposit")
    private TrxHandler configureDeposit(final @Inject BankUpgrades upgrades) {
        return new DepositHandler(upgrades);
    }

    @Bean("withdraw")
    private TrxHandler configureWithdraw() {
        return new WithdrawHandler();
    }
}
