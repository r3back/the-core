package com.qualityplus.skills.base.serdes;

import com.qualityplus.skills.base.perk.Perk;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

public final class SerdesPerk implements ObjectSerializer<Perk> {
    private static final SerdesGUIOptions SERDES_GUI_OPTIONS = new SerdesGUIOptions();

    @Override
    public boolean supports(@NonNull Class<? super Perk> type) {
        return Perk.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Perk object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("id", object.getId());
        data.add("displayName", object.getDisplayName());
        data.add("description", object.getDescription());
        data.add("enabled", object.isEnabled());
        data.add("baseAmount", object.getInitialAmount());
        data.add("chancePerLevel", object.getChancePerLevel());

        SERDES_GUI_OPTIONS.serialize(object.getGuiOptions(), data, generics);
    }

    @Override
    public Perk deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(Perk skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setDisplayName(data.get("displayName", String.class));
        skill.setDescription(data.getAsList("description", String.class));
        skill.setEnabled(data.get("enabled", Boolean.class));
        skill.setId(data.get("id", String.class));
        skill.setInitialAmount(data.get("baseAmount", Double.class));
        skill.setChancePerLevel(data.get("chancePerLevel", Double.class));

        skill.setGuiOptions(SERDES_GUI_OPTIONS.deserialize(data, generics));

    }

}
