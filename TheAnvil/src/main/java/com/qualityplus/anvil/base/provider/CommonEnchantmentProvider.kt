package com.qualityplus.anvil.base.provider

import com.qualityplus.anvil.api.provider.EnchantmentProvider
import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.assistant.api.util.BukkitItemUtil
import org.bukkit.inventory.meta.EnchantmentStorageMeta

abstract class CommonEnchantmentProvider : EnchantmentProvider {
    protected fun getCommonAnswer(session: AnvilSession): AnvilSession.SessionResult? {
        val toUpgradeIsNull = BukkitItemUtil.isNull(session.itemToUpgrade)

        val toSacrificeIsNull = BukkitItemUtil.isNull(session.itemToSacrifice)

        if (toSacrificeIsNull && toUpgradeIsNull) {
            return AnvilSession.SessionResult.NOTHING_SET
        }

        if (!toUpgradeIsNull && toSacrificeIsNull) {
            return AnvilSession.SessionResult.ONLY_ITEM_TO_UPGRADE
        }

        if (toUpgradeIsNull) {
            return AnvilSession.SessionResult.ONLY_ITEM_TO_SACRIFICE
        }

        val toUpgrade = session.itemToUpgrade.type
        val toSacrifice = session.itemToSacrifice.type

        return if (toUpgrade != toSacrifice && session.itemToSacrifice.itemMeta !is EnchantmentStorageMeta) AnvilSession.SessionResult.ERROR_CANNOT_COMBINE_THOSE_ITEMS else null
    }
}