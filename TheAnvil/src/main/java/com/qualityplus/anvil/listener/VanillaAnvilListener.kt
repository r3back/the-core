package com.qualityplus.anvil.listener

import com.cryptomorin.xseries.XMaterial
import com.qualityplus.anvil.api.box.Box
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI
import com.qualityplus.anvil.base.session.AnvilSessionImpl
import com.qualityplus.assistant.util.block.BlockUtils
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.platform.core.annotation.Component
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import java.util.stream.Collectors
import java.util.stream.Stream

@Component
class VanillaAnvilListener : Listener {
    @Inject
    private lateinit var box: Box

    @EventHandler
    fun onOpenBrewingStand(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) {
            return
        }

        val block = e.clickedBlock

        if (BlockUtils.isNull(block)) {
            return
        }

        if (!blockIsAnvil(block)) {
            return
        }

        if (!box.getFiles()!!.config()!!.openAsVanillaAnvil) {
            return
        }

        e.isCancelled = true

        e.player.openInventory(AnvilMainGUI(box, AnvilSessionImpl(null, null, null)).inventory)
    }

    private fun blockIsAnvil(block: Block?): Boolean {
        return Stream.of(XMaterial.ANVIL, XMaterial.CHIPPED_ANVIL, XMaterial.DAMAGED_ANVIL)
            .map { obj: XMaterial -> obj.parseMaterial() }
            .collect(Collectors.toList())
            .contains(block!!.type)
    }
}