package com.qualityplus.anvil.base.gui.anvilmain

import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.anvil.base.gui.AnvilGUI
import com.qualityplus.anvil.base.gui.anvilmain.handler.ClickHandler
import com.qualityplus.anvil.base.gui.anvilmain.handler.MainClickHandler
import com.qualityplus.anvil.base.gui.anvilmain.handler.NormalClickHandler
import com.qualityplus.anvil.base.gui.anvilmain.handler.ShiftClickHandler
import com.qualityplus.assistant.api.util.BukkitItemUtil
import com.qualityplus.assistant.api.util.IPlaceholder
import com.qualityplus.assistant.util.StringUtils
import com.qualityplus.assistant.util.inventory.InventoryUtils
import com.qualityplus.assistant.util.itemstack.ItemStackUtils
import com.qualityplus.assistant.util.map.MapUtils
import com.qualityplus.assistant.util.placeholder.Placeholder
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder
import lombok.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.*
import java.util.function.Consumer

class AnvilMainGUI(box: Box?, session: AnvilSession) : AnvilGUI(
    box!!.getFiles()!!.inventories()!!.enchantMainGUI, box
) {
    @Getter
    private val config: AnvilMainGUIConfig?

    @Setter
    private val giveItem = true
    private val handler: ClickHandler
    private val session: AnvilSession

    init {
        handler = MainClickHandler(NormalClickHandler(box, this, session), ShiftClickHandler(box, this, session))
        config = box!!.getFiles()!!.inventories()!!.enchantMainGUI

        this.session = session
    }

    override fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (getTarget(event) == ClickTarget.INSIDE) {
            val slot = event.slot
            if (isItem(slot, config!!.closeGUI)) {
                event.isCancelled = true
                player.closeInventory()
            } else if (slot == config.toUpgradeSlot || slot == config.toSacrificeSlot || slot == config.combineFilledItem.slot || slot == config.combinedFilledItem.slot) {
                handler.handle(event)
            } else {
                event.isCancelled = true
            }
        } else {
            handler.handleOutSide(event)
        }
    }

    override fun getInventory(): Inventory {
        InventoryUtils.fillInventory(inventory, config!!.background)
        val answer: AnvilSession.SessionResult = getAnswer(session)

        //Izquierda
        if (answer == AnvilSession.SessionResult.BOTH_ITEMS_SET || answer == AnvilSession.SessionResult.ONLY_ITEM_TO_UPGRADE) config.toUpgradeFilledSlots.forEach(
            Consumer { slot: Int? -> inventory.setItem(slot!!, ItemStackUtils.makeItem(config.toUpgradeFilledItem)) })

        //Derecha
        if (answer == AnvilSession.SessionResult.BOTH_ITEMS_SET || answer == AnvilSession.SessionResult.ONLY_ITEM_TO_SACRIFICE) config.toSacrificeFilledSlots.forEach(
            Consumer { slot: Int? -> inventory.setItem(slot!!, ItemStackUtils.makeItem(config.toSacrificeFilledItem)) })

        //Items de abajo
        if (answer == AnvilSession.SessionResult.BOTH_ITEMS_SET) {
            val newItem: ItemStack = getFinalItem(session)
            //Final Item
            val newItemInInv =
                ItemStackUtils.makeItem(config.combinedFilledItem, getPlaceholders(newItem), newItem, false, false)
            inventory.setItem(config.combinedFilledItem.slot, newItemInInv)
            config.readyToCombineSlots.forEach(Consumer { slot: Int? ->
                inventory.setItem(
                    slot!!, ItemStackUtils.makeItem(config.readyToCombineItem)
                )
            })
            //Anvil
            setItem(
                config.combineFilledItem, Arrays.asList<IPlaceholder?>(
                    Placeholder(
                        "anvil_enchant_exp_cost", getParsed(
                            box!!.getFiles()!!.messages()!!.anvilPlaceholders.experienceCost
                        )
                    ),
                    Placeholder(
                        "anvil_enchant_money_cost", getParsed(
                            box.getFiles()!!.messages()!!.anvilPlaceholders.moneyCost
                        )
                    )
                )
            )
        }
        if (!BukkitItemUtil.isNull(session.result)) inventory.setItem(
            config.combinedFilledItem.slot,
            ItemStackUtils.makeItem(config.combinedFilledItem, getPlaceholders(session.result), session.result)
        )
        if (answer.isError) setItem(
            config.combinedErrorItem,
            listOf<IPlaceholder>(Placeholder("anvil_error", getErrorPlaceholder(answer)))
        )
        if (!BukkitItemUtil.isNull(session.itemToSacrifice)) inventory.setItem(
            config.toSacrificeSlot,
            session.itemToSacrifice
        )
        if (!BukkitItemUtil.isNull(session.itemToUpgrade)) inventory.setItem(
            config.toUpgradeSlot,
            session.itemToUpgrade
        )
        setItem(config.closeGUI)
        return inventory
    }

    protected fun getEnchantments(itemStack: ItemStack): Map<Enchantment, Int> {
        val meta = itemStack.itemMeta
        return if (meta is EnchantmentStorageMeta) HashMap(meta.storedEnchants) else MapUtils.check(
            HashMap(
                meta!!.enchants
            )
        )
    }

    private fun getParsed(msg: String?): String {
        val placeholders: List<IPlaceholder> = PlaceholderBuilder.create(
            Placeholder("enchanting_enchant_exp_cost_amount", getLevelsCost(session)),
            Placeholder("enchanting_enchant_money_cost_amount", getMoneyCost(session))
        ).get()
        return StringUtils.processMulti(msg, placeholders)
    }

    private fun getPlaceholders(itemStack: ItemStack?): List<IPlaceholder> {
        return Arrays.asList<IPlaceholder>(
            Placeholder("anvil_result_item_displayname", BukkitItemUtil.getName(itemStack)),
            Placeholder("anvil_result_item_lore", BukkitItemUtil.getItemLore(itemStack))
        )
    }

    private fun getErrorPlaceholder(answer: AnvilSession.SessionResult): List<String?>? {
        return if (answer == AnvilSession.SessionResult.ERROR_CANNOT_COMBINE_THOSE_ITEMS) box!!.getFiles()!!
            .messages()!!.anvilPlaceholders.youCannotCombineThoseItems else box!!.getFiles()!!
            .messages()!!.anvilPlaceholders.youCannotAddThatEnchantment
    }

    override fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player
        if (!giveItem) return
        Optional.ofNullable(session.itemToSacrifice).ifPresent { itemStack: ItemStack? ->
            player.inventory.addItem(
                itemStack!!
            )
        }
        Optional.ofNullable(session.itemToUpgrade).ifPresent { itemStack: ItemStack? ->
            player.inventory.addItem(
                itemStack!!
            )
        }
        Optional.ofNullable(session.result).ifPresent { itemStack: ItemStack? ->
            player.inventory.addItem(
                itemStack!!
            )
        }
    }
}