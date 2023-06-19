package com.qualityplus.minions.persistance.data.upgrade;

import com.qualityplus.assistant.util.time.Markable;
import com.qualityplus.assistant.lib.eu.okaeri.persistence.document.Document;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class FuelEntity extends Document {
    private String id;
    private Markable markable;
}
