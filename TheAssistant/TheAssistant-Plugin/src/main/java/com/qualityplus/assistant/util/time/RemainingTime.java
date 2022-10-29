package com.qualityplus.assistant.util.time;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public final class RemainingTime extends OkaeriConfig {
    private long days;
    private long hours;
    private long minutes;
    private long seconds;
    private long millis;
    private long nanos;

    public boolean isZero(){
        return days <= 0 && hours <= 0 && minutes <= 0 && seconds <= 0;
    }
}
