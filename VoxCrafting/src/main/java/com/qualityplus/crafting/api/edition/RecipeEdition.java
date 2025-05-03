package com.qualityplus.crafting.api.edition;

import java.util.UUID;

public interface RecipeEdition {
    void setEditMode(UUID uuid, EditionObject type);

    void removeEditMode(UUID uuid);

    public enum EditionType{
        DISPLAY_NAME,
        CATEGORY,
        SLOT,
        PAGE,
        PERMISSION
    }
}
