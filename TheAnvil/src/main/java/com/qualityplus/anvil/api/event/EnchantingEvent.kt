package com.qualityplus.anvil.api.event

import com.qualityplus.assistant.api.event.PlayerHelperEvent
import org.bukkit.entity.Player

abstract class EnchantingEvent(who: Player) : PlayerHelperEvent(who)