package com.qualityplus.dragon.base.editmode;

import com.qualityplus.dragon.base.game.guardian.DragonGuardian;
import com.qualityplus.dragon.base.service.GuardianEditServiceImpl.EditType;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class GuardianEditMode {
    private UUID uuid;
    private EditType editType;
    private DragonGuardian dragonGuardian;
}
