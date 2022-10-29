package com.qualityplus.pets.persistance.data;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserSettings extends Document {
    private boolean convertPetToItemMode = false;
    private boolean petsAreHidden = false;
}
