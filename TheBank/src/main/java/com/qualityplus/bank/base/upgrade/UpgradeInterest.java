package com.qualityplus.bank.base.upgrade;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class UpgradeInterest extends OkaeriConfig {
    private double interestPercentage;
    private double interestLimitInMoney;
    private double from;
    private double to;
}
