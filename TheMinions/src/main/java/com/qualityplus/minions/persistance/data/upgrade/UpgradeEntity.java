package com.qualityplus.minions.persistance.data.upgrade;

import eu.okaeri.persistence.document.Document;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class UpgradeEntity extends Document {
    private String id;
}
