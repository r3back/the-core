package com.qualityplus.assistant.api.commands.setup.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CommandSetupEvent<T> {
    private final T command;
}