package com.qualityplus.skills.persistance;

import com.qualityplus.skills.persistance.data.UserData;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.DocumentPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.DocumentRepository;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentCollection;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentIndex;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentPath;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface SkillsRepository extends DocumentRepository<UUID, UserData> {

    @DocumentPath("name")
    Optional<UserData> findByName(String name);

    @DocumentPath("uuid")
    Optional<UserData> findByUuid(UUID uuid);


    default UserData get(Player player) {
        return get(player.getUniqueId(), player.getName());
    }

    default UserData get(UUID uuid, String name) {

        UserData user = this.findOrCreateByPath(uuid);

        user.setUuid(uuid);

        Optional.ofNullable(name).ifPresent(user::setName);

        user.getSkills().fillIfEmpty();

        return user;
    }
}
