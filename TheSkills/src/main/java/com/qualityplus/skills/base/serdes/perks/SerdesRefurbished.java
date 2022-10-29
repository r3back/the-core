package com.qualityplus.skills.base.serdes.perks;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.ProjectileMasterPerk;
import com.qualityplus.skills.base.perk.perks.RefurbishedPerk;
import com.qualityplus.skills.base.serdes.SerdesPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesRefurbished implements ObjectSerializer<RefurbishedPerk> {
    private static final SerdesPerk SERDES_PERK = new SerdesPerk();

    @Override
    public boolean supports(@NonNull Class<? super RefurbishedPerk> type) {
        return RefurbishedPerk.class.isAssignableFrom(type);
    }


    @Override
    public void serialize(@NonNull RefurbishedPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        SERDES_PERK.serialize(object, data, generics);

        data.add("initialAmount", object.getInitialAmount(), Double.class);
        data.add("chancePerLevel", object.getChancePerLevel(), Double.class);
        data.addCollection("toolList", object.getToolList(), XMaterial.class);

    }

    @Override
    public RefurbishedPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        RefurbishedPerk alchemySkill = new RefurbishedPerk();

        SERDES_PERK.deserialize(alchemySkill, data, generics);

        alchemySkill.setChancePerLevel(data.get("chancePerLevel", Double.class));
        alchemySkill.setInitialAmount(data.get("initialAmount", Double.class));
        alchemySkill.setToolList(data.getAsList("toolList", XMaterial.class));

        return alchemySkill;
    }
}
