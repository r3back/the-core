package com.qualityplus.anvil.util

import com.qualityplus.assistant.api.util.IPlaceholder
import com.qualityplus.assistant.util.placeholder.Placeholder
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder
import com.qualityplus.enchanting.api.enchantment.ICoreEnchantment
import lombok.experimental.UtilityClass

@UtilityClass
class AnvilPlaceholderUtils {
    fun getEnchantBuilder(enchantment: ICoreEnchantment): PlaceholderBuilder {
        return PlaceholderBuilder.create(
            Placeholder("enchanting_enchant_displayname", enchantment.name),
            Placeholder("enchanting_enchant_description", enchantment.description),
            Placeholder("enchanting_enchant_required_bookshelf", enchantment.requiredBookShelf)
        )
    }

    fun getEnchantPlaceholders(enchantment: ICoreEnchantment): List<IPlaceholder> {
        return getEnchantBuilder(enchantment).get()
    }

    private fun emptyIfNull(input: String?): String {
        return if (input == null || input == "") "&cEmpty" else input
    }
}