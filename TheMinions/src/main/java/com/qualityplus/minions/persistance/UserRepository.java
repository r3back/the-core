package com.qualityplus.minions.persistance;

import com.qualityplus.minions.persistance.data.UserData;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.DocumentPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.DocumentRepository;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentCollection;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentIndex;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentPath;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface UserRepository extends DocumentRepository<UUID, UserData> {
    @DocumentPath("name")
    Optional<UserData> findByName(String name);

    @DocumentPath("uuid")
    Optional<UserData> findByUuid(UUID uuid);


    default UserData get(OfflinePlayer player) {
        UserData data = this.findOrCreateByPath(player.getUniqueId());

        data.setUuid(player.getUniqueId());
        data.setName(player.getName());

        return data;
    }
}
