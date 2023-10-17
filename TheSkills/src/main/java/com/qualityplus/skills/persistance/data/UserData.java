package com.qualityplus.skills.persistance.data;

import com.qualityplus.skills.persistance.data.user.UserSkills;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Utility for user data
 */
@Data @EqualsAndHashCode(callSuper = true)
public final class UserData extends Document {
    private UUID uuid;
    private String name;
    private UserSkills skills = new UserSkills();

    /**
     * Adds a reset data
     */
    public void resetData() {
        this.skills.getXp().clear();
        this.skills.getLevel().clear();

        this.skills.fillIfEmpty();
    }
}
