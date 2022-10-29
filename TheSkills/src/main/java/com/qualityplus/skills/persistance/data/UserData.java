package com.qualityplus.skills.persistance.data;

import com.qualityplus.skills.persistance.data.user.UserSkills;
import eu.okaeri.persistence.document.Document;
import lombok.*;

import java.util.UUID;

@Data @EqualsAndHashCode(callSuper = true)
public final class UserData extends Document {
    private UUID uuid;
    private String name;
    private UserSkills skills = new UserSkills();

}
