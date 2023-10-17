package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Makes a perk files
 *
 * @param <AD>  Ability Damage
 * @param <BA>  Bonus Attack
 * @param <CS>  Cactus Skin
 * @param <EE>  Eagle Eyes
 * @param <FAF> Farming Fortune
 * @param <FOF> Foragning Fortune
 * @param <IL>  Iron Lungs
 * @param <LM>  Leaves Master
 * @param <LP>  Lightning Punch
 * @param <MM>  Medicine Man
 * @param <MF>  Mining Fortune
 * @param <MS>  Mining Speed
 * @param <OP>  One Punch Man
 * @param <PM>  Projectile Master
 * @param <RF>  Refurbished
 * @param <SP>  Spiderman
 * @param <SK>  Steel Skin
 * @param <W>   Wizard
 * @param <SF>  Sea Fortune
 * @param <PTM> Potion Master
 * @param <BC>  Brew Chance
 * @param <OM>  Orb Master
 */
public interface PerkFiles<AD, BA, CS, EE, FAF, FOF, IL, LM, LP, MM, MF, MS, OP, PM, RF, SP, SK, W, SF, PTM, BC, OM> extends ConfigReloader, AllGetter {
    /**
     * Makes an AD
     *
     * @return Ability Damage
     */
    public AD abilityDamage();

    /**
     * Makes an BA
     *
     * @return Bonus Attack
     */
    public BA bonusAttack();

    /**
     * Makes an CS
     *
     * @return Cactus Skin
     */
    public CS cactusSkin();

    /**
     * Makes an EE
     *
     * @return Eagle Eyes
     */
    public EE eagleEyes();

    /**
     * Makes an FAF
     *
     * @return Farming Fortune
     */
    public FAF farmingFortune();

    /**
     * Makes an FOF
     *
     * @return Foragning Fortune
     */
    public FOF foragingFortune();

    /**
     * Makes an IL
     *
     * @return Iron Lungs
     */
    public IL ironLungs();

    /**
     * Makes an LM
     *
     * @return Leaves Master
     */
    public LM leavesMaster();

    /**
     * Makes an LP
     *
     * @return Lightning Punch
     */
    public LP lightningPunch();

    /**
     * Makes an MM
     *
     * @return Medicine Man
     */
    public MM medicineMan();

    /**
     * Makes an MF
     *
     * @return Mining Fortune
     */
    public MF miningFortune();

    /**
     * Makes an MS
     *
     * @return Mining Speed
     */
    public MS miningSpeed();

    /**
     * Makes an OP
     *
     * @return One Punch Man
     */
    public OP onePunchMan();

    /**
     * Makes an PM
     *
     * @return Projectile Master
     */
    public PM projectileMaster();

    /**
     * Makes an RF
     *
     * @return Refurbished
     */
    public RF refurbished();

    /**
     * Makes an SP
     *
     * @return Spiderman
     */
    public SP spiderman();

    /**
     * Makes a SK
     *
     * @return Steel Skin
     */
    public SK steelSkin();

    /**
     * Makes an W
     *
     * @return  Wizard
     */
    public W wizard();

    /**
     * Makes an SF
     *
     * @return Sea Fortune
     */
    public SF seaFortune();

    /**
     * Makes a PTM
     *
     * @return Potion Master
     */
    public PTM potionMaster();

    /**
     * Makes an BC
     *
     * @return  Brew Chance
     */
    public BC brewChance();

    /**
     * Makes an OM
     *
     * @return Orb Master
     */
    public OM orbMaster();
}
