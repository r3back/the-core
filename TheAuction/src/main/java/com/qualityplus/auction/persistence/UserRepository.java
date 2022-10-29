package com.qualityplus.auction.persistence;

import com.qualityplus.auction.persistence.data.User;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import eu.okaeri.persistence.repository.annotation.DocumentIndex;
import eu.okaeri.persistence.repository.annotation.DocumentPath;
import eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface UserRepository extends DocumentRepository<UUID, User> {

    @DocumentPath("name")
    Optional<User> findByName(String name);

    @DocumentPath("uuid")
    Optional<User> findByUuid(UUID uuid);


    default User get(OfflinePlayer player) {
        User user = this.findOrCreateByPath(player.getUniqueId());
        user.setUuid(player.getUniqueId());
        Optional.ofNullable(player.getName()).ifPresent(user::setName);
        return user;
    }

    default User get(UUID player) {
        return get(Bukkit.getOfflinePlayer(player));
    }
}
