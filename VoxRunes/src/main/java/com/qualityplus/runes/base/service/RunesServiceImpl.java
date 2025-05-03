package com.qualityplus.runes.base.service;

import com.qualityplus.assistant.lib.eu.okaeri.injector.annotation.Inject;
import com.qualityplus.runes.api.service.RuneTableService;
import com.qualityplus.runes.api.service.RunesService;

import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;


@Component
public final class RunesServiceImpl implements RunesService {
    private @Inject RuneTableService tableService;

    @Override
    public void createRuneTable(Location location) {
        tableService.createRuneTable(location);
    }
}
