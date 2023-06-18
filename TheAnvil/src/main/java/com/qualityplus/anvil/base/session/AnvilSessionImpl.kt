package com.qualityplus.anvil.base.session

import com.qualityplus.anvil.api.session.AnvilSession
import com.qualityplus.anvil.util.AnvilFinderUtil
import lombok.AllArgsConstructor
import lombok.Getter
import org.bukkit.inventory.ItemStack

@Getter
@AllArgsConstructor
class AnvilSessionImpl : AnvilSession {
    override var result: ItemStack? = null
    override var itemToUpgrade: ItemStack? = null
    override var itemToSacrifice: ItemStack? = null

    override val sessionResult: AnvilSession.SessionResult
        get() = AnvilFinderUtil.getAnswer(this)
}