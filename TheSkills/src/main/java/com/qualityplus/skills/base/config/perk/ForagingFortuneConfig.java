package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.ForagingFortunePerk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Header;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameModifier;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.NameStrategy;
import com.qualityplus.assistant.lib.eu.okaeri.configs.annotation.Names;
import com.qualityplus.assistant.lib.eu.okaeri.platform.core.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for foraging fortune config
 */
@Getter
@Setter
@Configuration(path = "perks/foraging_fortune_perk.yml")
@Header("================================")
@Header("       Foraging Fortune Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class ForagingFortuneConfig extends OkaeriConfig implements PerkFile {
    private String id = "foraging_fortune";
    private boolean enabled = true;
    private String displayName = "Foraging Fortune";
    private List<String> description = Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when break logs.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(14)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZn" +
                    "QubmV0L3RleHR1cmUvNmIzNTE2OWI3NjU5ZGM3MGYzZDc5MzQzYjM5YTA5Yjk1ZjQ0MzBhZTAxMWRlY2E2Y2FmMzU4YzIwZTkyZGM0YSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_foraging_fortune_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link ForagingFortunePerk}
     */
    public Perk getPerk() {
        return ForagingFortunePerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .initialAmount(0)
                .allowedMaterials(Arrays.asList(
                        XMaterial.OAK_LOG,
                        XMaterial.ACACIA_LOG,
                        XMaterial.BIRCH_LOG,
                        XMaterial.DARK_OAK_LOG,
                        XMaterial.SPRUCE_LOG,
                        XMaterial.JUNGLE_LOG
                ))
                .build();
    }
}
