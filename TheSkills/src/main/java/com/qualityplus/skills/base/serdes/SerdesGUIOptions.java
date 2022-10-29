package com.qualityplus.skills.base.serdes;

import com.cryptomorin.xseries.XMaterial;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;

import java.util.Collections;

public final class SerdesGUIOptions implements ObjectSerializer<GUIOptions> {
    @Override
    public boolean supports(@NonNull Class<? super GUIOptions> type) {
        return GUIOptions.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull GUIOptions object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("slot", object.getSlot());
        data.add("item", object.getItem());
        data.add("page", object.getPage());
        data.add("mainMenuLore", object.getMainMenuLore());
        data.add("texture", object.getTexture());
    }

    @Override
    public GUIOptions deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        GUIOptions item = new GUIOptions();

        item.setSlot(data.containsKey("slot") ? data.get("slot", Integer.class) : 0);
        item.setPage(data.containsKey("page") ? data.get("page", Integer.class) : 1);
        item.setItem(data.containsKey("item") ? data.get("item", XMaterial.class) : XMaterial.STONE);
        item.setTexture(data.containsKey("texture") ? data.get("texture", String.class) : null);
        item.setMainMenuLore(data.containsKey("mainMenuLore") ? data.getAsList("mainMenuLore", String.class) : Collections.emptyList());

        return item;
    }
}
