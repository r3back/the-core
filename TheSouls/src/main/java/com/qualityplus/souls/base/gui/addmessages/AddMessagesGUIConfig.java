package com.qualityplus.souls.base.gui.addmessages;

import com.qualityplus.assistant.inventory.CommonGUI;
import com.qualityplus.assistant.inventory.Item;
import com.qualityplus.assistant.inventory.SimpleGUI;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class AddMessagesGUIConfig extends OkaeriConfig implements SimpleGUI{
    private final List<Integer> messagesSlots;
    private final Item previousPageItem;
    private final CommonGUI commonGUI;
    private final Item addMessageItem;
    private final Item nextPageItem;
    private final Item messageItem;
    private final Item goBackItem;

    @Builder
    public AddMessagesGUIConfig(List<Integer> messagesSlots, Item previousPageItem, CommonGUI commonGUI, Item addMessageItem, Item nextPageItem, Item messageItem, Item goBackItem) {
        this.messagesSlots = messagesSlots;
        this.previousPageItem = previousPageItem;
        this.commonGUI = commonGUI;
        this.addMessageItem = addMessageItem;
        this.nextPageItem = nextPageItem;
        this.messageItem = messageItem;
        this.goBackItem = goBackItem;
    }
}
