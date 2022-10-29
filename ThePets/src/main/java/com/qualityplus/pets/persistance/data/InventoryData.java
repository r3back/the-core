package com.qualityplus.pets.persistance.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public final class InventoryData extends Document{
    private List<InventoryPet> pets = new ArrayList<>();

    public void addInventoryPet(UUID uuid, String petId){
        pets.add(new InventoryPet(uuid, petId));
    }
}
