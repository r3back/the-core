package com.qualityplus.anvil.base.gui.anvilmain.request;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUIConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public final class ClickRequest {
    private AnvilMainGUIConfig config;
    private InventoryClickEvent event;
    private AnvilSession session;
    private AnvilMainGUI gui;
    private int slot;
    private Box box;

    @Builder
    public ClickRequest(final InventoryClickEvent event,
                        final AnvilSession session,
                        final AnvilMainGUI gui,
                        final Box box,
                        final AnvilMainGUIConfig config) {
        this.session = session;
        this.config = config;
        this.event = event;
        this.slot = event.getSlot();
        this.box = box;
        this.gui = gui;
    }

    public Optional<Player> getPlayer() {
        return Optional.of(this.event.getWhoClicked())
                .filter(p -> p instanceof Player)
                .map(p -> (Player) p);
    }

    public boolean isShiftClick() {
        return this.event.isShiftClick();
    }

    public boolean isSacrificeSlot() {
        return slot == config.getToSacrificeSlot();
    }

    public boolean isCloseSlot() {
        return slot == config.getCloseGUI().getSlot();
    }

    public boolean isUpgradeSlot() {
        return slot == config.getToUpgradeSlot();
    }

    public boolean isClickToCombineSlot() {
        return slot == config.getCombineFilledItem().getSlot();
    }

    public boolean isPickUpSlot() {
        return slot == config.getCombinedFilledItem().getSlot();
    }
}
