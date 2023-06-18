package com.qualityplus.anvil.api.provider

import com.qualityplus.anvil.api.session.AnvilSession
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface EnchantmentProvider {
    public fun getAnswer(session: AnvilSession): AnvilSession.SessionResult

    public fun getFinalItem(anvilSession: AnvilSession): ItemStack

    public fun getMoneyCost(session: AnvilSession): Double

    public fun getLevelsCost(session: AnvilSession): Double

    public fun dontHavePermission(session: AnvilSession, player: Player?): Boolean
}