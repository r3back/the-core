package com.qualityplus.skills.base.serdes.perks.common;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.map.MapUtils;
import com.qualityplus.skills.base.perk.perks.common.AbstractRandomBlockDropPerk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.HashMap;

public final class SerdesRandomBlockDropPerk implements ObjectSerializer<AbstractRandomBlockDropPerk> {
    @Override
    public boolean supports(@NonNull Class<? super AbstractRandomBlockDropPerk> type) {
        return AbstractRandomBlockDropPerk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull AbstractRandomBlockDropPerk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.addAsMap("itemAndChances", MapUtils.check(object.getItemAndChances()), XMaterial.class, Double.class);
    }

    @Override
    public AbstractRandomBlockDropPerk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(AbstractRandomBlockDropPerk skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setItemAndChances(data.containsKey("itemAndChances") ? data.getAsMap("rewards", XMaterial.class, Double.class) : new HashMap<>());
    }

}
