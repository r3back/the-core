package com.qualityplus.collections.persistance;

import com.qualityplus.collections.persistance.data.UserData;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import eu.okaeri.persistence.repository.annotation.DocumentIndex;
import eu.okaeri.persistence.repository.annotation.DocumentPath;
import eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface CollectionsRepository extends DocumentRepository<UUID, UserData> {

    @DocumentPath("name")
    Optional<UserData> findByName(String name);

    @DocumentPath("uuid")
    Optional<UserData> findByUuid(UUID uuid);


    default UserData get(OfflinePlayer player) {
        UserData user = this.findOrCreateByPath(player.getUniqueId());

        user.setUuid(player.getUniqueId());

        Optional.ofNullable(player.getName()).ifPresent(user::setName);

        user.getCollections().fillIfEmpty();

        return user;
    }
}
