package com.qualityplus.skills.base.config.stat;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import com.qualityplus.skills.base.stat.stats.PetLuckStat;
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
 * Utility class for pet luck config
 */
@Getter
@Setter
@Configuration(path = "stats/pet_luck_stat.yml")
@Header("================================")
@Header("       Pet Luck Stat      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public final class PetLuckConfig extends OkaeriConfig implements StatFile {
    private String id = "pet_luck";
    private boolean enabled = true;
    private String displayName = "&d♣ Pet Luck";
    private List<String> description = Arrays.asList("&a%chance% &7chance to get better pets", "&7when crafting.");
    private int maxLevel = 50;
    private GUIOptions guiOptions = GUIOptions.builder()
            .slot(16)
            .page(1)
            .item(XMaterial.PLAYER_HEAD)
            .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubm" +
                    "V0L3RleHR1cmUvMTllY2JkY2I1NTQxMjNmYTRkMzE4NzY1MzhiYzdmYjI0NzQ5NGFlYTMyNWJkMjY1OTU2OTQ1MDVhZWJkMTBlZCJ9fX0=")
            .mainMenuLore(Collections.singletonList("%skill_pet_luck_description%"))
            .build();

    /**
     * Adds a stat
     *
     * @return {@link Stat}
     */
    public Stat getStat() {
        return PetLuckStat.builder()
                .id(this.id)
                .displayName(this.displayName)
                .description(this.description)
                .enabled(this.enabled)
                .skillGUIOptions(this.guiOptions)
                .baseAmount(0)
                .build();
    }
}
