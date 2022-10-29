package com.qualityplus.crafting.api.edition;

import com.qualityplus.crafting.api.edition.RecipeEdition.EditionType;
import com.qualityplus.crafting.base.recipes.CustomRecipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class EditionObject {
    private final CustomRecipe recipe;
    private final EditionType type;
}
