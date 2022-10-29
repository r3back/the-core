package com.qualityplus.dragon.base.game.guardian;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class GuardianArmor extends OkaeriConfig {
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack weapon;
}
