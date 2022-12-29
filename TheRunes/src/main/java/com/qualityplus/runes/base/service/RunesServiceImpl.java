package com.qualityplus.runes.base.service;

import com.qualityplus.runes.api.service.RuneTableService;
import com.qualityplus.runes.api.service.RunesService;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;


@Component
public final class RunesServiceImpl implements RunesService {
    private @Inject RuneTableService tableService;

    @Override
    public void createRuneTable(Location location) {
        tableService.createRuneTable(location);
    }
}
