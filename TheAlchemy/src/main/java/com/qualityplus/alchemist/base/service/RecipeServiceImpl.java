package com.qualityplus.alchemist.base.service;

import com.qualityplus.alchemist.api.service.RecipeService;
import com.qualityplus.alchemist.base.config.RecipesFile;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class RecipeServiceImpl implements RecipeService {
    private @Inject
    RecipesFile recipes;
}
