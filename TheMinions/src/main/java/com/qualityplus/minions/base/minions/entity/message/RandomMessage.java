package com.qualityplus.minions.base.minions.entity.message;

import com.qualityplus.assistant.api.util.Randomable;
import eu.okaeri.configs.OkaeriConfig;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RandomMessage extends OkaeriConfig implements Randomable {
    private List<String> message;
    private double probability;
}
