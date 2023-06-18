package com.qualityplus.anvil.base.provider

import com.cryptomorin.xseries.XEnchantment
import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.anvil.base.config.Config
import com.qualityplus.anvil.base.requirement.VanillaEnchantRequirement
import com.qualityplus.assistant.util.faster.FastMap
import com.qualityplus.assistant.util.map.MapUtils
import eu.okaeri.injector.annotation.Inject
import lombok.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import org.bukkit.inventory.meta.ItemMeta
import java.util.*

class VanillaEnchantingProvider : CommonEnchantmentProvider() {
    @Inject
    private val config: Config? = null

    override fun getAnswer(session: AnvilSession): AnvilSession.SessionResult {
        val commonResult = getCommonAnswer(session)

        if (commonResult != null) {
            return commonResult
        }
        return if (getEnchantments(session.itemToSacrifice)
                .keys
                .stream()
                .anyMatch { enchantment: Enchantment ->
                    getIncompatibility(
                        session.itemToUpgrade,
                        enchantment
                    ).isPresent
                }
        ) AnvilSession.SessionResult.ERROR_CONFLICT_ENCHANTMENTS else AnvilSession.SessionResult.BOTH_ITEMS_SET
    }

    override fun getFinalItem(anvilSession: AnvilSession): ItemStack {
        var newItem = anvilSession.itemToUpgrade.clone()
        for ((enchantment, value) in getNewEnchantments(anvilSession)) {
            newItem = addEnchantment(newItem, VanillaEnchantmentSession(enchantment, value))
        }
        return newItem
    }

    override fun getMoneyCost(session: AnvilSession): Double {
        return 0
    }

    override fun getLevelsCost(session: AnvilSession): Double {
        val levelsReference: Map<Int?, Int> = FastMap.builder(
            Int::class.java, Int::class.java
        )
            .put(1, 2)
            .put(2, 4)
            .put(3, 6)
            .put(4, 8)
            .put(5, 10)
            .build()
        return getNewEnchantments(session)
            .entries
            .stream()
            .map { (key, value): Map.Entry<Enchantment, Int?> ->
                val enchantment = XEnchantment.matchXEnchantment(
                    key
                )
                val requirement = Optional.ofNullable(
                    config!!.requiredLevelsForVanilla.getOrDefault(enchantment, null)
                )
                    .map { obj: VanillaEnchantRequirement -> obj.requiredLevelsToEnchant }
                    .filter { obj: Map<Int?, Int>? -> Objects.nonNull(obj) }
                    .orElse(levelsReference)
                requirement[value]!!.toDouble()
            }
            .reduce(0.0) { a: Double, b: Double -> java.lang.Double.sum(a, b) }
    }

    override fun dontHavePermission(session: AnvilSession, player: Player?): Boolean {
        return false
    }

    private fun getIncompatibility(itemStack: ItemStack?, enchantment: Enchantment): Optional<Enchantment> {
        return getEnchantments(itemStack)
            .keys
            .stream()
            .filter { itemEnch: Enchantment -> itemEnch == enchantment != enchantment.conflictsWith(itemEnch) }
            .findFirst()
    }

    fun addEnchantment(itemStack: ItemStack, session: VanillaEnchantmentSession): ItemStack {
        val incompatibility = getIncompatibility(itemStack, session.getEnchantment())
        val enchantmentList = getEnchantments(itemStack)

        //Remove All Enchantments
        removeAllEnchantments(itemStack, enchantmentList)

        //Add new Enchantment
        enchantmentList[session.getEnchantment()] = session.getLevel()
        //Remove Incompatibility Enchantment
        incompatibility.ifPresent { key: Enchantment -> enchantmentList.remove(key) }

        //Add All Enchantments
        addAllEnchantments(itemStack, enchantmentList)

        /*ItemMeta meta = itemStack.getItemMeta();

        Optional.ofNullable(meta).ifPresent(m -> m.getItemFlags().remove(ItemFlag.HIDE_ENCHANTS));

        itemStack.setItemMeta(meta);*/return itemStack
    }

    private fun removeAllEnchantments(itemStack: ItemStack, toRemove: Map<Enchantment, Int>) {
        val meta = itemStack.itemMeta
        if (meta is EnchantmentStorageMeta) toRemove.forEach { (key: Enchantment?, value: Int?) ->
            meta.removeStoredEnchant(
                key
            )
        } else toRemove.forEach { (key: Enchantment?, value: Int?) ->
            Optional.ofNullable(meta).ifPresent { m: ItemMeta ->
                m.removeEnchant(
                    key
                )
            }
        }
        itemStack.itemMeta = meta
    }

    private fun addAllEnchantments(itemStack: ItemStack, toAdd: Map<Enchantment, Int>) {
        val meta = itemStack.itemMeta
        if (meta is EnchantmentStorageMeta) {
            toAdd.forEach { (ench: Enchantment?, level: Int?) ->
                meta.addStoredEnchant(
                    ench, level, false
                )
            }
        } else {
            toAdd.forEach { (ench: Enchantment?, level: Int?) ->
                Optional.ofNullable(meta).ifPresent { m: ItemMeta ->
                    m.addEnchant(
                        ench, level, false
                    )
                }
            }
        }
        itemStack.itemMeta = meta
    }

    private fun getNewEnchantments(session: AnvilSession): Map<Enchantment, Int?> {
        val toUpgradeEnchants: Map<Enchantment, Int> = getEnchantments(session.itemToUpgrade)
        val toSacrificeEnchants: Map<Enchantment, Int> = getEnchantments(session.itemToSacrifice)
        val newEnchants: MutableMap<Enchantment, Int?> = HashMap()
        for (enchantment in toSacrificeEnchants.keys) {
            if (!toUpgradeEnchants.containsKey(enchantment)) {
                newEnchants[enchantment] = toSacrificeEnchants[enchantment]
            } else {
                val sacrificeLevel = toSacrificeEnchants[enchantment]!!
                val upgradeLevel = toUpgradeEnchants[enchantment]!!
                if (upgradeLevel + sacrificeLevel > enchantment.maxLevel) continue
                newEnchants[enchantment] = upgradeLevel + sacrificeLevel
            }
        }
        return newEnchants
    }

    private fun getEnchantments(itemStack: ItemStack?): MutableMap<Enchantment, Int> {
        val meta = itemStack!!.itemMeta
        return if (meta is EnchantmentStorageMeta) HashMap(meta.storedEnchants) else MapUtils.check(
            HashMap(
                meta!!.enchants
            )
        )
    }

    @Data
    @Builder
    @AllArgsConstructor
    class VanillaEnchantmentSession {
        private val enchantment: Enchantment? = null
        private val level = 0
    }
}