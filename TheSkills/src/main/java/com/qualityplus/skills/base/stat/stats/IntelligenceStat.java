package com.qualityplus.skills.base.stat.stats;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.math.MathUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.skills.base.skill.gui.GUIOptions;
import com.qualityplus.skills.base.stat.Stat;
import lombok.*;

import java.util.List;

@Data @EqualsAndHashCode(callSuper = true) @NoArgsConstructor
public final class IntelligenceStat extends Stat {
    @Builder
    public IntelligenceStat(String id, boolean enabled, String displayName, List<String> description, GUIOptions skillGUIOptions, double baseAmount) {
        super(id, enabled, displayName, description, skillGUIOptions, baseAmount);
    }

    @Override
    public List<String> getFormattedDescription(int level) {
        List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                .with(new Placeholder("intelligence", level),
                      new Placeholder("level_number", level),
                      new Placeholder("level_roman", MathUtils.toRoman(level))
                ).get();
        return StringUtils.processMulti(description, placeholders);
    }
}
