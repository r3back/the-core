package com.qualityplus.skills.base.serdes.perks.common;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.perk.perks.common.AbstractFortuneBlockPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesFortunePerk implements ObjectSerializer<AbstractFortuneBlockPerk> {
    @Override
    public boolean supports(@NonNull Class<? super AbstractFortuneBlockPerk> type) {
        return AbstractFortuneBlockPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull AbstractFortuneBlockPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.addCollection("allowedMaterials", object.getAllowedMaterials(), XMaterial.class);
    }

    @Override
    public AbstractFortuneBlockPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(AbstractFortuneBlockPerk skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setAllowedMaterials(data.getAsList("allowedMaterials", XMaterial.class));
    }
}
