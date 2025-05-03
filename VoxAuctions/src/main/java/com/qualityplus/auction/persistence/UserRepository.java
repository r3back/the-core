package com.qualityplus.auction.persistence;

import com.qualityplus.auction.persistence.data.User;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.DocumentPersistence;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.DocumentRepository;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentCollection;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentIndex;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.repository.annotation.DocumentPath;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.UUID;

/**
 * Makes a name user
 */
@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface UserRepository extends DocumentRepository<UUID, User> {

    /**
     * Makes a name user
     *
     * @param name Name
     * @return     {@link User}
     */
    public @DocumentPath("name")
        Optional<User> findByName(String name);

    /**
     * Makes an optional user
     *
     * @param uuid {@link UUID}
     * @return     {@link User}
     */
    public @DocumentPath("uuid")
        Optional<User> findByUuid(UUID uuid);


    /**
     * Makes a default user
     *
     * @param player {@link OfflinePlayer}
     * @return       {@link User}
     */
    public default User get(OfflinePlayer player) {
        final User user = this.findOrCreateByPath(player.getUniqueId());
        user.setUuid(player.getUniqueId());
        Optional.ofNullable(player.getName()).ifPresent(user::setName);
        return user;
    }

    /**
     * Makes a default user
     *
     * @param player {@link UUID}
     * @return       {@link User}
     */
    public default User get(UUID player) {
        return get(Bukkit.getOfflinePlayer(player));
    }
}
