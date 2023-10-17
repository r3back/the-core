package com.qualityplus.skills.api.config;

import com.qualityplus.assistant.api.config.ConfigReloader;
import com.qualityplus.assistant.lib.eu.okaeri.configs.OkaeriConfig;

import java.util.List;

/**
 * Makes a Skill files
 *
 * @param <A>  Alchemy
 * @param <CA> Carpentry
 * @param <C>  Combat
 * @param <D>  Discoverer
 * @param <E>  Enchanting
 * @param <F>  Farming
 * @param <FH> Fishing
 * @param <FG> Foraging
 * @param <M>  Mining
 * @param <R>  Rune Crafting
 * @param <T>  Taming
 * @param <DU> Dungeoneering
 */
public interface SkillFiles <A, CA, C, D, E, F, FH, FG, M, R, T, DU> extends ConfigReloader {

    /**
     * Makes an A
     *
     * @return Alchemy
     */
    public A alchemy();

    /**
     * Makes a CA
     *
     * @return Carpentry
     */
    public CA carpentry();

    /**
     * Makes a C
     *
     * @return Combat
     */
    public C combat();

    /**
     * Makes a D
     *
     * @return Discoverer
     */
    public D discoverer();

    /**
     * Makes an DU
     *
     * @return Dungeoneering
     */
    public DU dungeoneering();

    /**
     * Makes an E
     *
     * @return Enchanting
     */
    public E enchanting();

    /**
     * Makes an F
     *
     * @return Farming
     */
    public F farming();

    /**
     * Makes an FH
     *
     * @return Fishing
     */
    public FH fishing();

    /**
     * Makes an FG
     *
     * @return Foraging
     */
    public FG foraging();

    /**
     * Makes an M
     *
     * @return Mining
     */
    public M mining();

    /**
     * Makes an R
     *
     * @return Rune Crafting
     */
    public R runeCrafting();

    /**
     * Adds a T
     *
     * @return Taming
     */
    public T taming();

    /**
     * Adds okaeri config all
     *
     * @return  list of {@link OkaeriConfig}
     */
    public List<OkaeriConfig> getAll();
}
