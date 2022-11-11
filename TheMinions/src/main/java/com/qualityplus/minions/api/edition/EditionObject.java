package com.qualityplus.minions.api.edition;

import com.qualityplus.minions.base.soul.Soul;
import lombok.Data;

@Data
public final class EditionObject {
    private final SoulEdition.EditionType type;
    private final Soul soul;
}
