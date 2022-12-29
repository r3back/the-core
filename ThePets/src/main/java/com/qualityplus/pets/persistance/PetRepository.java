package com.qualityplus.pets.persistance;

import com.qualityplus.pets.persistance.data.PetData;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import eu.okaeri.persistence.repository.annotation.DocumentPath;
import eu.okaeri.platform.core.annotation.DependsOn;

import java.util.Optional;
import java.util.UUID;

@DependsOn(type = DocumentPersistence.class, name = "persistence")
@DocumentCollection(path = "pets", keyLength = 36)
public interface PetRepository extends DocumentRepository<UUID, PetData> {
    @DocumentPath("uuid")
    Optional<PetData> findByUuid(UUID uuid);


    default PetData get(PetData petData) {
        PetData data = this.findOrCreateByPath(petData.getUuid());

        data.setPetId(petData.getPetId());
        data.setLevel(petData.getLevel());
        data.setXp(petData.getXp());
        data.setUuid(petData.getUuid());

        return data;
    }
}
