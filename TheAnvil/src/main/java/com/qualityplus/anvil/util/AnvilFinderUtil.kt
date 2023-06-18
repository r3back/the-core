package com.qualityplus.anvil.util

import com.qualityplus.anvil.TheAnvil
import com.qualityplus.anvil.api.session.AnvilSession
import lombok.experimental.UtilityClass
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@UtilityClass
class AnvilFinderUtil {
    fun getAnswer(session: AnvilSession): AnvilSession.SessionResult {
        return TheAnvil.getApi().provider.getAnswer(session)
    }

    fun getMoneyCost(session: AnvilSession): Double {
        return TheAnvil.getApi().provider.getMoneyCost(session)
    }

    fun getLevelsCost(session: AnvilSession): Double {
        return TheAnvil.getApi().provider.getLevelsCost(session)
    }

    fun dontHavePermission(session: AnvilSession, player: Player?): Boolean {
        return TheAnvil.getApi().provider.dontHavePermission(session, player)
    }

    fun getFinalItem(session: AnvilSession): ItemStack {
        return TheAnvil.getApi().provider.getFinalItem(session)
    }
}