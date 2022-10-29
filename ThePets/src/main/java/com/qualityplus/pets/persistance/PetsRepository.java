package com.qualityplus.pets.persistance;

import com.qualityplus.pets.persistance.data.PetsData;
import com.qualityplus.pets.persistance.data.UserData;
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
public interface PetsRepository extends DocumentRepository<UUID, UserData> {
    @DocumentPath("name")
    Optional<PetsData> findByName(String name);

    @DocumentPath("uuid")
    Optional<PetsData> findByUuid(UUID uuid);


    default UserData get(OfflinePlayer player) {
        UserData data = this.findOrCreateByPath(player.getUniqueId());

        data.setUuid(player.getUniqueId());

        return data;
    }
}
