package com.qualityplus.souls.api.edition;

import com.qualityplus.souls.base.soul.Soul;
import lombok.Data;

@Data
public final class EditionObject {
    private final SoulEdition.EditionType type;
    private final Soul soul;
}
