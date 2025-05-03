package com.qualityplus.pets.persistance.data.inside;

import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InventoryPet extends Document {
    private UUID uuid;
    private String petId;
}
