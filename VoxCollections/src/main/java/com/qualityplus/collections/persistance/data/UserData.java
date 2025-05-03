package com.qualityplus.collections.persistance.data;

import com.qualityplus.collections.persistance.data.user.UserCollections;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data @EqualsAndHashCode(callSuper = true)
public final class UserData extends Document {
    private UUID uuid;
    private String name;
    private UserCollections collections = new UserCollections();
}
