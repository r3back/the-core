package com.qualityplus.runes.base.rune;

import com.qualityplus.assistant.lib.com.cryptomorin.xseries.XMaterial;
import com.qualityplus.assistant.util.particle.ParticleColor;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public final class RuneEffect extends OkaeriConfig {
    private Particle particle;
    private ParticleColor particleColor;
    private int particleQuantity;
    private double repeatEverySeconds;
    private boolean strikeLightning;
    private RuneApply toApplyIn;
    private List<XMaterial> fakeDropItems = new ArrayList<>();

    public boolean canBeAppliedIn(ItemStack itemStack) {
        String material = itemStack.getType().toString();
        return toApplyIn.equals(RuneApply.BOW) ? material.contains("BOW") :
                toApplyIn.equals(RuneApply.BOOTS) ? material.contains("BOOT") :
                        material.contains("SWORD");
    }
}
