package com.qualityplus.assistant.base.event;

import com.qualityplus.assistant.api.event.PlayerHelperEvent;
import com.qualityplus.assistant.util.armor.ArmorType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public final class ArmorEquipEvent  extends PlayerHelperEvent {
    private final EquipMethod method;
    private final ArmorType type;
    private ItemStack oldArmorPiece, newArmorPiece;

    public ArmorEquipEvent(final Player player, final EquipMethod equipType, final ArmorType type, final ItemStack oldArmorPiece, final ItemStack newArmorPiece) {
        super(player);
        this.method = equipType;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    public enum EquipMethod {

        SHIFT_CLICK,

        DRAG,

        PICK_DROP,

        HOTBAR,

        HOTBAR_SWAP,

        DISPENSER,

        BROKE,

        DEATH;
    }


}