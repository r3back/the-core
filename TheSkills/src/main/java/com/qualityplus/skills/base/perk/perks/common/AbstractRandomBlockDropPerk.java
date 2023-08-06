package com.qualityplus.skills.base.perk.perks.common;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.random.EasyRandom;
import com.qualityplus.assistant.util.random.RandomSelector;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for Abstract Random Block Drop Perk
 */
@NoArgsConstructor
public abstract class AbstractRandomBlockDropPerk extends Perk {
    @Getter @Setter
    private Map<XMaterial, Double> itemAndChances;

    /**
     * Makes an Abstract Random Block Dro pPerk
     *
     * @param id               Id
     * @param enabled          Enabled
     * @param displayName      Display Name
     * @param description      Description
     * @param skillGUIOptions  {@link GUIOptions}
     * @param baseAmount       Base Amount
     * @param chancePerLevel   Chance Per Level
     * @param itemAndChances   Item And Chances
     */
    public AbstractRandomBlockDropPerk(final String id,
                                       final boolean enabled,
                                       final String displayName,
                                       final List<String> description,
                                       final GUIOptions skillGUIOptions,
                                       final double baseAmount,
                                       final double chancePerLevel,
                                       final Map<XMaterial, Double> itemAndChances) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount, chancePerLevel);

        this.itemAndChances = itemAndChances;
    }

    protected ItemStack getItem() {
        final List<EasyRandom<XMaterial>> items = this.itemAndChances.keySet().stream()
                .map(item -> new EasyRandom<>(item, this.itemAndChances.get(item)))
                .collect(Collectors.toList());

        return Optional.ofNullable(new RandomSelector<>(items).getRandom()).map(item -> item.getItem().parseItem()).orElse(null);
    }
}
