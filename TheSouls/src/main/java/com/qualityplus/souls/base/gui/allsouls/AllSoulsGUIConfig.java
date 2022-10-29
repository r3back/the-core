package com.qualityplus.souls.base.gui.allsouls;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AllSoulsGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final CommonGUI commonGUI;
    private final Item backPageItem;
    private final Item nextPageItem;
    private final int soulsPerPage;
    private final Item soulItem;

    @Builder
    public AllSoulsGUIConfig(CommonGUI commonGUI, Item backPageItem, Item nextPageItem, Item soulItem, int soulsPerPage) {
        this.backPageItem = backPageItem;
        this.nextPageItem = nextPageItem;
        this.soulsPerPage = soulsPerPage;
        this.commonGUI = commonGUI;
        this.soulItem = soulItem;
    }
}
