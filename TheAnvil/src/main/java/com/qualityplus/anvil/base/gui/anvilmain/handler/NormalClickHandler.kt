package com.qualityplus.anvil.base.gui.anvilmain.handler

import com.cryptomorin.xseries.XMaterial
import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI
import com.qualityplus.anvil.base.session.AnvilSessionImpl
import com.qualityplus.assistant.TheAssistantPlugin
import com.qualityplus.assistant.api.util.BukkitItemUtil
import com.qualityplus.assistant.util.StringUtils
import lombok.AllArgsConstructor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*

@AllArgsConstructor
class NormalClickHandler : ClickHandler {
    private val box: Box? = null
    private val gui: AnvilMainGUI? = null
    private val session: AnvilSession? = null

    override fun handle(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.slot == gui.getConfig().combineFilledItem.slot) {
            event.isCancelled = true
            val sessionResult = session.getSessionResult()
            if (sessionResult != AnvilSession.SessionResult.BOTH_ITEMS_SET) return
            if (dontHavePermission(session, player)) {
                player.sendMessage(
                    StringUtils.color(
                        box!!.getFiles()!!.messages()!!.anvilMessages.youDontHaveEnoughPermissions
                    )
                )
                return
            }
            if (getMoneyCost(session) > TheAssistantPlugin.getAPI().addons.economy.getMoney(player)) {
                player.sendMessage(StringUtils.color(box!!.getFiles()!!.messages()!!.anvilMessages.youDontHaveEnoughMoney))
                return
            }
            if (getLevelsCost(session) > player.level) {
                player.sendMessage(
                    StringUtils.color(
                        box!!.getFiles()!!.messages()!!.anvilMessages.youDontHaveEnoughLevels
                    )
                )
                return
            }
            gui.setGiveItem(false)
            player.openInventory(AnvilMainGUI(box, AnvilSessionImpl(getFinalItem(session), null, null)).inventory)
        } else if (event.slot == gui.getConfig().combinedFilledItem.slot) {
            if (BukkitItemUtil.isNull(session.getResult())) {
                event.isCancelled = true
                return
            }
            gui.setGiveItem(false)
            player.openInventory(AnvilMainGUI(box, AnvilSessionImpl(null, null, null)).inventory)
            player.setItemOnCursor(session.getResult())
        } else {
            event.isCancelled = true
            if (!BukkitItemUtil.isNull(session.getResult())) {
                player.sendMessage(StringUtils.color(box!!.getFiles()!!.messages()!!.anvilMessages.thereIsAnItemToPickup))
                return
            }
            val copy = Optional.ofNullable(event.cursor).map { obj: ItemStack -> obj.clone() }
                .orElse(null)
            val current = Optional.ofNullable(event.currentItem).map { obj: ItemStack -> obj.clone() }
                .orElse(null)
            if (BukkitItemUtil.isNull(copy)) {
                val newSession: AnvilSession = if (event.slot == gui.getConfig().toSacrificeSlot) AnvilSessionImpl(
                    null,
                    session.getItemToUpgrade(),
                    null
                ) else AnvilSessionImpl(null, null, session.getItemToSacrifice())

                //PickUp
                gui.setGiveItem(false)
                event.currentItem = null
                player.openInventory(AnvilMainGUI(box, newSession).inventory)
                player.setItemOnCursor(current)
            } else {
                if (!box!!.getFiles()!!
                        .config()!!.allowedItems.contains(XMaterial.matchXMaterial(copy!!))
                ) return
                val newSession: AnvilSession = if (event.slot == gui.getConfig().toSacrificeSlot) AnvilSessionImpl(
                    null,
                    session.getItemToUpgrade(),
                    copy
                ) else AnvilSessionImpl(null, copy, session.getItemToSacrifice())
                //Put
                gui.setGiveItem(false)
                player.setItemOnCursor(null)
                player.openInventory(AnvilMainGUI(box, newSession).inventory)
                if (BukkitItemUtil.isNull(current)) return
                player.setItemOnCursor(current)
            }
        }
    }

    override fun handleOutSide(event: InventoryClickEvent) {}
}