package com.qualityplus.minions.base.factory;

import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.minions.api.recipe.provider.RecipeProvider;
import com.qualityplus.minions.base.recipe.provider.EmptyRecipeProviderImpl;
import com.qualityplus.minions.base.recipe.provider.TheCraftingRecipeProvider;
import eu.okaeri.injector.OkaeriInjector;

import eu.okaeri.platform.core.annotation.Bean;
import eu.okaeri.platform.core.annotation.Component;

@Component
public final class RecipeProviderFactory {
    private @Inject("injector") OkaeriInjector injector;

    @Bean
    public RecipeProvider createRecipeProvider(){
        if(TheAssistantPlugin.getAPI().getDependencyResolver().isPlugin("TheCrafting")) {
            return injector.createInstance(TheCraftingRecipeProvider.class);
        }else
            return injector.createInstance(EmptyRecipeProviderImpl.class);
    }
}
