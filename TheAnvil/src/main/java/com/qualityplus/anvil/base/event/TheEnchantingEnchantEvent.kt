package com.qualityplus.anvil.base.event

import com.qualityplus.anvil.api.event.EnchantingEvent
import com.qualityplus.enchanting.api.session.EnchantmentSession
import lombok.Getter
import org.bukkit.entity.Player

@Getter
class TheEnchantingEnchantEvent(who: Player, val session: EnchantmentSession) : EnchantingEvent(who)