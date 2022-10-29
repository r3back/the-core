package com.qualityplus.assistant.api.handler;

import com.qualityplus.assistant.api.event.SignCompletedEvent;

@FunctionalInterface
public interface SignCompleteHandler {
    void onSignClose(SignCompletedEvent event);
}
