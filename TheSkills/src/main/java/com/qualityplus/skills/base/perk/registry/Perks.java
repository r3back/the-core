package com.qualityplus.skills.base.perk.registry;

import com.google.common.collect.ImmutableSet;
import com.qualityplus.skills.api.box.Box;
import com.qualityplus.skills.api.effect.CommonObject;
import com.qualityplus.skills.base.perk.Perk;
import com.qualityplus.skills.base.stat.Stat;
import eu.okaeri.commons.bukkit.time.MinecraftTimeEquivalent;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.bukkit.annotation.Delayed;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public final class Perks {
    private static final Map<String, Perk> PERK_REGISTRY = new HashMap<>();

    @ApiStatus.Internal
    public static void registerNewPerk(@NotNull final Perk perk) {
        PERK_REGISTRY.put(perk.getId().toLowerCase(), perk);
    }

    @Nullable
    public static Perk getByID(@NotNull final String id) {
        return PERK_REGISTRY.get(id.toLowerCase());
    }

    @Nullable
    public static Perk getByKey(@NotNull final NamespacedKey key) {
        return PERK_REGISTRY.get(key.getKey());
    }

    public static Set<Perk> values() {
        return ImmutableSet.copyOf(PERK_REGISTRY.values());
    }

    public static Set<Perk> values(Predicate<Perk> filter) {
        return ImmutableSet.copyOf(PERK_REGISTRY.values().stream().filter(filter).collect(Collectors.toList()));
    }

    @Delayed(time = MinecraftTimeEquivalent.SECOND, async = true)
    public static void reloadPerks(@Inject Box box){
        values().forEach(Perk::unregisterListeners);

        Stream.of(box.perkFiles().abilityDamage().abilityDamagePerk,
                        box.perkFiles().bonusAttack().bonusAttackSpeedPerk,
                        box.perkFiles().cactusSkin().cactusSkinPerk,
                        box.perkFiles().eagleEyes().eagleEyesPerk,
                        box.perkFiles().farmingFortune().farmingFortuneConfig,
                        box.perkFiles().foragingFortune().foragingFortunePerk,
                        box.perkFiles().ironLungs().ironLungsPerk,
                        box.perkFiles().leavesMaster().leavesMasterPerk,
                        box.perkFiles().lightningPunch().lightningPunchPerk,
                        box.perkFiles().medicineMan().medicineManPerk,
                        box.perkFiles().miningFortune().miningFortunePerk,
                        box.perkFiles().miningSpeed().miningSpeedPerk,
                        box.perkFiles().onePunchMan().onePunchManPerk,
                        box.perkFiles().projectileMaster().projectileMasterPerk,
                        box.perkFiles().refurbished().refurbishedPerk,
                        box.perkFiles().spiderman().spidermanPerk,
                        box.perkFiles().steelSkin().steelSkinPerk,
                        box.perkFiles().wizard().wizardPerk,
                        box.perkFiles().seaFortune().seaFortunePerk,
                        box.perkFiles().potionMaster().potionMasterPerk,
                        box.perkFiles().brewChance().brewerChancePerk,
                        box.perkFiles().orbMaster().orbMasterPerk)
                .filter(CommonObject::isEnabled)
                .forEach(perk -> {
                    perk.register();
                    perk.registerListeners(box);
                });

        box.log().info("Registered " + values().size() + " Perks!");

    }
}
