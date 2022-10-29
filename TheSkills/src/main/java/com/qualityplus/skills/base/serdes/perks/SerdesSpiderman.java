package com.qualityplus.skills.base.serdes.perks;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.RefurbishedPerk;
import com.qualityplus.skills.base.perk.perks.SpidermanPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.entity.Spider;

public final class SerdesSpiderman implements ObjectSerializer<SpidermanPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super SpidermanPerk> type) {
        return SpidermanPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull SpidermanPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.add("canBeUsedWithPlayers", object.isCanBeUsedWithPlayers(), Boolean.class);
        data.addCollection("enabledWorlds", object.getEnabledWorlds(), String.class);
        data.add("secondsDuration", object.getSecondsDuration(), Integer.class);

    }

    @Override
    public SpidermanPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        SpidermanPerk alchemySkill = new SpidermanPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setSecondsDuration(data.get("secondsDuration", Integer.class));
        alchemySkill.setEnabledWorlds(data.getAsList("enabledWorlds", String.class));
        alchemySkill.setCanBeUsedWithPlayers(data.get("canBeUsedWithPlayers", Boolean.class));

        return alchemySkill;
    }
}
