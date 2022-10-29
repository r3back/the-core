package com.qualityplus.assistant.util.actionbar;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ActionBarUtils {
    private static final int ACTION_BAR_LENGTH = 20;

    public static double getPercentageFromTotal(Double amount, Double total) {
        return amount > 0 ? (amount * 100) / total : 0D;
    }

    public int getParsedInPercentage(int percentage) {
        return percentage > 0 ? (percentage * ACTION_BAR_LENGTH) / 100 : 0;
    }

    public String getReplacedBar(int percentage) {
        StringBuilder bar = new StringBuilder();

        int current = getParsedInPercentage(percentage);

        for (int i = 0; i < current && i < ACTION_BAR_LENGTH; i++)
            bar.append("&a-");
        for (int i = 0; i < ACTION_BAR_LENGTH - current; i++)
            bar.append("&f-");
        return bar.toString();
    }

    public String getReplacedBar(double percentage) {
        return getReplacedBar((int)percentage);
    }
}
