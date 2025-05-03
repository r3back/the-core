package com.qualityplus.anvil.util;

import com.qualityplus.anvil.VoxAnvil;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.api.session.AnvilSession.SessionResult;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


@UtilityClass
public class AnvilFinderUtil {
    public SessionResult getAnswer(AnvilSession session) {
        return VoxAnvil.getApi().getProvider().getAnswer(session);
    }

    public double getMoneyCost(AnvilSession session) {
        return VoxAnvil.getApi().getProvider().getMoneyCost(session);
    }

    public double getLevelsCost(AnvilSession session) {
        return VoxAnvil.getApi().getProvider().getLevelsCost(session);
    }

    public boolean dontHavePermission(AnvilSession session, Player player) {
        return VoxAnvil.getApi().getProvider().dontHavePermission(session, player);
    }

    public ItemStack getFinalItem(AnvilSession session) {
        return VoxAnvil.getApi().getProvider().getFinalItem(session);
    }
}
