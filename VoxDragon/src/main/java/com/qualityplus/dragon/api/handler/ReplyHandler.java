package com.qualityplus.dragon.api.handler;

/**
 * Reply Handler
 */
public interface ReplyHandler<P, T> {
    void handle(P player, T message);
}
