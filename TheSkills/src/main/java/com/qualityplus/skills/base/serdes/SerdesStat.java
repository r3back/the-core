package com.qualityplus.skills.base.serdes;

import com.qualityplus.skills.base.stat.Stat;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;


public final class SerdesStat implements ObjectSerializer<Stat> {
    private static final SerdesGUIOptions SERDES_GUI_OPTIONS = new SerdesGUIOptions();

    @Override
    public boolean supports(@NonNull Class<? super Stat> type) {
        return Stat.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull Stat object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("id", object.getId());
        data.add("displayName", object.getDisplayName());
        data.add("description", object.getDescription());
        data.add("enabled", object.isEnabled());
        data.add("baseAmount", object.getInitialAmount());

        SERDES_GUI_OPTIONS.serialize(object.getGuiOptions(), data, generics);
    }

    @Override
    public Stat deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return null;
    }

    public void deserialize(Stat skill, @NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        skill.setDisplayName(data.get("displayName", String.class));
        skill.setDescription(data.getAsList("description", String.class));
        skill.setEnabled(data.get("enabled", Boolean.class));
        skill.setId(data.get("id", String.class));
        skill.setInitialAmount(data.get("baseAmount", Double.class));

        skill.setGuiOptions(SERDES_GUI_OPTIONS.deserialize(data, generics));

    }

}
