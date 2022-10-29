package com.qualityplus.assistant.util.time;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class TimeUtils {
    public String getParsedTime(RemainingTime time, String message, String days, String hours, String minutes, String seconds, String noTimeSymbol, boolean show){
        String d = String.valueOf(time.getDays());
        String h = String.valueOf(time.getHours());
        String m = String.valueOf(time.getMinutes());
        String s = String.valueOf(time.getSeconds());

        List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("days_placeholder", time.getDays() >= 0 ? days.replace("%days%", d) : show ? noTimeSymbol : ""),
                new Placeholder("hours_placeholder",time.getHours() >= 0 ?  hours.replace("%hours%", h) : show ? noTimeSymbol : ""),
                new Placeholder("minutes_placeholder", time.getMinutes() >= 0 ? minutes.replace("%minutes%", m) : show ? noTimeSymbol : ""),
                new Placeholder("seconds_placeholder", time.getSeconds() >= 0 ? seconds.replace("%seconds%", s) : show ? noTimeSymbol : "")

        );

        return StringUtils.processMulti(message, placeholders);
    }

    public static RemainingTime getTimeWhenAgo(long lastTime){
        long millis = System.currentTimeMillis() - lastTime;

        return getRemainingTime(millis);
    }

    public static RemainingTime getRemainingTime(long millis){
        long nanos = TimeUnit.MILLISECONDS.toNanos(millis);
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return new RemainingTime(days, hours, minutes, seconds, millis, nanos);
    }
}
