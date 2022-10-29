package com.qualityplus.assistant.api.commands.setup.handler;

import com.qualityplus.assistant.api.commands.setup.event.CommandSetupEvent;

@FunctionalInterface
public interface CommandSetupHandler<T> {
    void onCompleteCommand(CommandSetupEvent<T> event);
}