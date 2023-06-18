package com.qualityplus.anvil.base.provider

import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.enchanting.TheEnchanting
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment
import com.qualityplus.enchanting.base.session.EnchantmentSessionImpl
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TheEnchantingProvider : CommonEnchantmentProvider() {
    override fun getAnswer(session: AnvilSession): AnvilSession.SessionResult {
        val commonResult = getCommonAnswer(session)

        if (commonResult != null) {
            return commonResult
        }

        return if (TheEnchanting.getApi().getEnchantments(session.itemToSacrifice)
                .keys
                .stream()
                .anyMatch { enchantment: ICoreEnchantment? ->
                    TheEnchanting.getApi().getIncompatibility(session.itemToUpgrade, enchantment).isPresent
                }
        ) AnvilSession.SessionResult.ERROR_CONFLICT_ENCHANTMENTS else AnvilSession.SessionResult.BOTH_ITEMS_SET
    }

    override fun getFinalItem(session: AnvilSession): ItemStack {
        var newItem = session.itemToUpgrade.clone()
        for ((key, value) in getNewEnchantments(session)) newItem = TheEnchanting.getApi().addEnchantment(
            newItem, EnchantmentSessionImpl(
                key, value!!
            )
        )
        return newItem
    }

    override fun getMoneyCost(session: AnvilSession): Double {
        return getNewEnchantments(session)
            .entries
            .stream()
            .map { (key, value): Map.Entry<ICoreEnchantment, Int?> ->
                key.getRequiredMoneyToEnchant(
                    value!!
                )
            }
            .reduce(0.0) { a: Double, b: Double -> java.lang.Double.sum(a, b) }
    }

    override fun getLevelsCost(session: AnvilSession): Double {
        return getNewEnchantments(session)
            .entries
            .stream()
            .map { (key, value): Map.Entry<ICoreEnchantment, Int?> ->
                key.getRequiredLevelToEnchant(
                    value!!
                )
            }
            .reduce(0.0) { a: Double, b: Double -> java.lang.Double.sum(a, b) }
    }

    override fun dontHavePermission(session: AnvilSession, player: Player?): Boolean {
        return getNewEnchantments(session)
            .entries
            .stream()
            .anyMatch { (key, value): Map.Entry<ICoreEnchantment, Int?> -> !key.canEnchant(player, value!!) }
    }

    fun getNewEnchantments(session: AnvilSession): Map<ICoreEnchantment, Int?> {
        val toUpgradeEnchants = TheEnchanting.getApi().getEnchantments(session.itemToUpgrade)
        val toSacrificeEnchants = TheEnchanting.getApi().getEnchantments(session.itemToSacrifice)
        val newEnchants: MutableMap<ICoreEnchantment, Int?> = HashMap()
        for (enchantment in toSacrificeEnchants.keys) {
            if (!toUpgradeEnchants.containsKey(enchantment)) {
                newEnchants[enchantment] = toSacrificeEnchants[enchantment]
            } else {
                val sacrificeLevel = toSacrificeEnchants[enchantment]!!
                val upgradeLevel = toUpgradeEnchants[enchantment]!!
                if (sacrificeLevel != upgradeLevel) continue
                val newLevel = upgradeLevel + 1
                if (newLevel > enchantment.maxLevel) continue
                newEnchants[enchantment] = newLevel
            }
        }
        return newEnchants
    }
}