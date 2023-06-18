package com.qualityplus.anvil.base.config

import com.cryptomorin.xseries.XEnchantment
import com.cryptomorin.xseries.XMaterial
import com.qualityplus.anvil.base.requirement.VanillaEnchantRequirement
import com.qualityplus.assistant.api.gui.LoreWrapper
import com.qualityplus.assistant.util.faster.FastMap
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*
import eu.okaeri.platform.core.annotation.Configuration
import java.util.*

@Configuration
@Header("================================")
@Header("       Config      ")
@Header("================================")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class Config : OkaeriConfig() {
    var prefix = "[TheAnvil] "

    @Comment("Mark as true if you want to open plugin's gui")
    @Comment("with the vanilla anvil")
    var openAsVanillaAnvil = true

    @Comment("- wrapLength = After how many characters in a lore you want")
    @Comment("               to separate it.")
    @Comment("- wrapStart = After line is separated what character do you")
    @Comment("              want to start the new line with.")
    var loreWrapper = LoreWrapper(40, "&7")
    var allowedItems = materials

    @Comment("Required levels when TheEnchanting is")
    @Comment("disabled.")
    var requiredLevelsForVanilla: Map<XEnchantment?, VanillaEnchantRequirement?> = FastMap.builder(
        XEnchantment::class.java, VanillaEnchantRequirement::class.java
    )
        .put(
            XEnchantment.DAMAGE_ALL, VanillaEnchantRequirement.builder()
                .requiredLevelsToEnchant(
                    FastMap.builder(Int::class.java, Int::class.java)
                        .put(2, 4)
                        .put(3, 6)
                        .put(4, 8)
                        .put(5, 10)
                        .build()
                )
                .build()
        )
        .build()
    private val materials: List<XMaterial>
        private get() {
            val allowed: MutableList<XMaterial> = ArrayList()
            for (material in XMaterial.VALUES) {
                val parsed = material.toString().lowercase(Locale.getDefault())
                if (parsed.contains("waxed")) continue
                if (parsed.contains("axe") || parsed.contains("pickaxe") || parsed.contains("shovel") || parsed.contains(
                        "hoe"
                    ) || parsed.contains("sword") || parsed.contains("leggings") ||
                    parsed.contains("chestplate") || parsed.contains("boots") || parsed.contains("helmet")
                ) allowed.add(material)
            }
            allowed.add(XMaterial.ENCHANTED_BOOK)
            return allowed
        }
}