package com.qualityplus.anvil.base.gui.anvilmain.handler

import com.cryptomorin.xseries.XMaterial
import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI
import com.qualityplus.anvil.base.session.AnvilSessionImpl
import com.qualityplus.assistant.api.util.BukkitItemUtil
import com.qualityplus.assistant.util.StringUtils
import lombok.AllArgsConstructor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*

@AllArgsConstructor
class ShiftClickHandler : ClickHandler {
    private val box: Box? = null
    private val gui: AnvilMainGUI? = null
    private val session: AnvilSession? = null
    override fun handle(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.slot == gui.getConfig().combinedFilledItem.slot) {
            if (BukkitItemUtil.isNull(session.getResult())) {
                event.isCancelled = true
                return
            }
            event.isCancelled = true
            gui.setGiveItem(false)
            player.openInventory(AnvilMainGUI(box, AnvilSessionImpl(null, null, null)).inventory)
            player.inventory.addItem(session.getResult())
        } else if (event.slot == gui.getConfig().combineFilledItem.slot) {
            event.isCancelled = true
        } else {
            if (!BukkitItemUtil.isNull(session.getResult())) {
                player.sendMessage(StringUtils.color(box!!.getFiles()!!.messages()!!.anvilMessages.thereIsAnItemToPickup))
                event.isCancelled = true
                return
            }
            val copy = Optional.ofNullable(event.cursor).map { obj: ItemStack -> obj.clone() }
                .orElse(null)
            val current = Optional.ofNullable(event.currentItem).map { obj: ItemStack -> obj.clone() }
                .orElse(null)
            if (event.slot == gui.getConfig().toSacrificeSlot) {
                if (BukkitItemUtil.isNull(copy) && !BukkitItemUtil.isNull(current)) {
                    gui.setGiveItem(false)
                    player.openInventory(
                        AnvilMainGUI(
                            box,
                            AnvilSessionImpl(null, session.getItemToUpgrade(), null)
                        ).inventory
                    )
                    player.inventory.addItem(current!!)
                }
            } else {
                if (BukkitItemUtil.isNull(copy) && !BukkitItemUtil.isNull(current)) {
                    gui.setGiveItem(false)
                    player.openInventory(
                        AnvilMainGUI(
                            box,
                            AnvilSessionImpl(null, null, session.getItemToSacrifice())
                        ).inventory
                    )
                    player.inventory.addItem(current!!)
                }
            }
        }
    }

    override fun handleOutSide(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val current = Optional.ofNullable(event.currentItem)
            .map { obj: ItemStack -> obj.clone() }
            .orElse(null)
        val target = target
        if (!BukkitItemUtil.isNull(session.getResult())) {
            player.sendMessage(StringUtils.color(box!!.getFiles()!!.messages()!!.anvilMessages.thereIsAnItemToPickup))
            event.isCancelled = true
            return
        }
        if (BukkitItemUtil.isNull(session.getItemToSacrifice()) && !BukkitItemUtil.isNull(current) && target == ShiftTarget.SACRIFICE) {
            if (!box!!.getFiles()!!
                    .config()!!.allowedItems.contains(XMaterial.matchXMaterial(current!!))
            ) {
                event.isCancelled = true
                return
            }
            gui.setGiveItem(false)
            event.currentItem = null
            player.openInventory(
                AnvilMainGUI(
                    box,
                    AnvilSessionImpl(null, session.getItemToUpgrade(), current)
                ).inventory
            )
        }
        if (BukkitItemUtil.isNull(session.getItemToUpgrade()) && !BukkitItemUtil.isNull(current) && target == ShiftTarget.UPGRADE) {
            if (!box!!.getFiles()!!
                    .config()!!.allowedItems.contains(XMaterial.matchXMaterial(current!!))
            ) {
                event.isCancelled = true
                return
            }
            gui.setGiveItem(false)
            event.currentItem = null
            player.openInventory(
                AnvilMainGUI(
                    box,
                    AnvilSessionImpl(null, current, session.getItemToSacrifice())
                ).inventory
            )
        }
    }

    private val target: ShiftTarget
        private get() {
            val toUpgradeIsNull = BukkitItemUtil.isNull(session.getItemToUpgrade())
            return if (toUpgradeIsNull) ShiftTarget.UPGRADE else ShiftTarget.SACRIFICE
        }

    internal enum class ShiftTarget {
        UPGRADE, SACRIFICE
    }
}