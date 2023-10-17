package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;

/**
 * Makes a stat files
 *
 * @param <CC> Crit Chance
 * @param <CD> Crit Damage
 * @param <D>  Defense
 * @param <F>  Ferocity
 * @param <I>  Intelligence
 * @param <M>  Magic Find
 * @param <P>  Pet Luck
 * @param <S>  Speed
 * @param <ST> Strength
 */
public interface StatFiles<CC, CD, D, F, I, M, P, S, ST> extends ConfigReloader, AllGetter {
    /**
     * Makes a CC
     *
     * @return Crit Chance
     */
    public CC critChance();

    /**
     * Makes a CD
     *
     * @return Crit Damage
     */
    public CD critDamage();

    /**
     * Makes a D
     *
     * @return Defense
     */
    public D defense();

    /**
     * Makes an F
     *
     * @return Ferocity
     */
    public F ferocity();

    /**
     * Makes a I
     *
     * @return Intelligence
     */
    public I intelligence();

    /**
     * Makes an M
     *
     * @return Magic Find
     */
    public M magicFind();

    /**
     * Makes a P
     * @return Pet Luck
     */
    public P petLuck();

    /**
     * Makes an S
     *
     * @return Speed
     */
    public S speed();

    /**
     * Makes an ST
     *
     * @return Strength
     */
    public ST strength();
}
