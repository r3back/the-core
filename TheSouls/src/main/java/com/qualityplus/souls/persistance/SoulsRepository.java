package com.qualityplus.souls.persistance;

import com.qualityplus.souls.persistance.data.SoulsData;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import eu.okaeri.persistence.repository.annotation.DocumentIndex;
import eu.okaeri.persistence.repository.annotation.DocumentPath;
import eu.okaeri.platform.core.annotation.DependsOn;
import org.bukkit.OfflinePlayer;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "player", keyLength = 36, indexes = {
        @DocumentIndex(path = "name", maxLength = 24)
})
public interface SoulsRepository extends DocumentRepository<UUID, SoulsData> {
    @DocumentPath("name")
    Optional<SoulsData> findByName(String name);

    @DocumentPath("uuid")
    Optional<SoulsData> findByUuid(UUID uuid);


    default SoulsData get(OfflinePlayer player) {
        SoulsData data = this.findOrCreateByPath(player.getUniqueId());

        if(data.getSoulsCollected() == null) data.setSoulsCollected(new ArrayList<>());
        if(data.getTiaSoulsCollected() == null) data.setTiaSoulsCollected(new ArrayList<>());

        Optional.ofNullable(player).ifPresent(p -> data.setName(p.getName()));
        Optional.ofNullable(player).ifPresent(p -> data.setUuid(p.getUniqueId()));

        return data;
    }
}
