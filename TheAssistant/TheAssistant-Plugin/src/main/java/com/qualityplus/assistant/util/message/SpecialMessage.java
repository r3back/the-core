package com.qualityplus.assistant.util.message;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public final class SpecialMessage extends OkaeriConfig {
    public List<String> message;
    public String action;
    public String aboveMessage;

    public SpecialMessage(String message){
        this.message = Collections.singletonList(message);
    }
}