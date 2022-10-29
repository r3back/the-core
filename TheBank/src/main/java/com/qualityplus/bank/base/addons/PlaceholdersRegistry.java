package com.qualityplus.bank.base.addons;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.addons.PlaceholdersAddon;
import com.qualityplus.assistant.api.addons.registrable.Registrable;
import com.qualityplus.bank.api.service.BankService;
import com.qualityplus.bank.persistence.data.BankData;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class PlaceholdersRegistry {
    @Delayed(time = MinecraftTimeEquivalent.SECOND * 5)
    public void registerBankPlaceholders(@Inject BankService service){
        PlaceholdersAddon addon = TheAssistantPlugin.getAPI().getAddons().getPlaceholders();

        addon.registerPlaceholders("bank_user_money",
                e -> String.valueOf(service.getBankData(e.getPlayer().getUniqueId()).map(BankData::getMoney).orElse(0D)));

        addon.registerPlaceholders("bank_last_interest",
                e -> String.valueOf(service.getBankData(e.getPlayer().getUniqueId()).map(BankData::getLastInterest).orElse(0D)));

        addon.registerPlaceholders("bank_current_profile_id",
                e -> service.getBankData(e.getPlayer().getUniqueId()).map(BankData::getBankUpgrade).orElse(""));

        if(addon instanceof Registrable) ((Registrable) addon).registerAddon();
    }
}
