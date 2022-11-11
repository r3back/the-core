package com.qualityplus.minions.base.minion.layout;

import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class MinionLayout extends OkaeriConfig {
    private LayoutType type;
    private List<XMaterial> materialThatCauseException;
    private List<XMaterial> allMaterialsCauseExceptionExcept;
}
