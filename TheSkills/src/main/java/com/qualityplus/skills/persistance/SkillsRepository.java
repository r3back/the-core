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

/**
 * Makes a skills repositories
 */
@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface SkillsRepository extends DocumentRepository<UUID, UserData> {

    /**
     * Adds a user data
     *
     * @param name Name
     * @return {@link UserData}
     */
    public @DocumentPath("name")
        Optional<UserData> findByName(String name);

    /**
     * Adds a uuid
     *
     * @param uuid {@link UUID}
     * @return {@link UserData}
     */
    public @DocumentPath("uuid")
        Optional<UserData> findByUuid(UUID uuid);


    /**
     * Adds a user data
     *
     * @param player {@link Player}
     * @return {@link UserData}
     */
    public default UserData get(Player player) {
        return get(player.getUniqueId(), player.getName());
    }

    /**
     * Adds a user data
     *
     * @param uuid {@link UUID}
     * @param name Name
     * @return {@link UserData}
     */
    public default UserData get(UUID uuid, String name) {

        final UserData user = this.findOrCreateByPath(uuid);

        user.setUuid(uuid);

        Optional.ofNullable(name).ifPresent(user::setName);

        user.getSkills().fillIfEmpty();

        return user;
    }
}
