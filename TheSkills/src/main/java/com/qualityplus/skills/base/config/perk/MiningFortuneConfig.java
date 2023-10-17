package com.qualityplus.skills.base.config.perk;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.perk.perks.MiningFortunePerk;
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
 * Utility class for mining fortune config
 */
@Getter
@Setter
@Configuration(path = "perks/mining_fortune_perk.yml")
@Header("================================")
@Header("       Mining Fortune Perk      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class MiningFortuneConfig extends OkaeriConfig implements PerkFile {
    private String id = "mining_fortune";
    private boolean enabled = true;
    private String displayName = "Mining Fortune";
    private List<String> description = Arrays.asList("  &a%percent%% &7chance to get &a%multiplier%x &7drops", "  &7when break ores.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(21)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cm" +
                    "UvOTAwZDc1YThjYzAzZTU5YzQ3NWNhYzc2ODFiYjRlNmQwOGFjNTkyOTBkNWYyMzlkNjA1Njc2MmI0ZTU1ZjkxNSJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_mining_fortune_description%"))
            .build();

    /**
     * Adds a perk
     *
     * @return {@link MiningFortunePerk}
     */
    public Perk getPerk() {
        return MiningFortunePerk.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .chancePerLevel(0.1)
                .initialAmount(0)
                .allowedMaterials(Arrays.asList(
                        XMaterial.COAL_ORE,
                        XMaterial.DIAMOND_ORE,
                        XMaterial.GOLD_ORE,
                        XMaterial.IRON_ORE,
                        XMaterial.REDSTONE_ORE,
                        XMaterial.LAPIS_ORE
                ))
                .build();
    }
}
