package com.qualityplus.souls.api.edition;

import java.util.UUID;

public interface SoulEdition {
    void setEditMode(UUID uuid, EditionObject type);

    void removeEditMode(UUID uuid);

    public enum EditionType{
        ADD_MESSAGE,
        ADD_COMMAND
    }
}
