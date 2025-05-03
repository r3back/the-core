package com.qualityplus.minions.base.gui.changeitem;

import com.qualityplus.minions.base.minions.minion.Minion;
import com.qualityplus.minions.base.minions.minion.upgrade.MinionUpgrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChangeItemRequest {
    private final MinionUpgrade minionUpgrade;
    private final ChangeItem changeItem;
    private final Minion minion;

    public boolean is(ChangeItem changeItem) {
        return this.changeItem.equals(changeItem);
    }
}
