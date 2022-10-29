package com.qualityplus.assistant.util.time;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public final class Timer extends OkaeriConfig {
    private int amount;
    private TimeType type;

    public long getEffectiveTime(){
        switch (type){
            case MINUTES:
                return Duration.ofMinutes(amount).toMillis();
            case DAYS:
                return Duration.ofDays(amount).toMillis();
            case HOURS:
                return Duration.ofHours(amount).toMillis();
            case SECONDS:
                return Duration.ofSeconds(amount).toMillis();
        }
        return 0L;
    }

    public enum TimeType{
        MINUTES(3),
        HOURS(2),
        SECONDS(1),
        DAYS(0);

        @Getter
        public int level;

        TimeType(int level){
            this.level = level;
        }

        public TimeType getNext(){
            return Arrays.stream(TimeType.values()).filter(timeType -> timeType.getLevel() == this.level + 1).findFirst().orElse(TimeType.DAYS);
        }
    }
}