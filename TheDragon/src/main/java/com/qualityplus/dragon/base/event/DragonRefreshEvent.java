package com.qualityplus.dragon.base.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Arrays;

public final class DragonRefreshEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final RefreshType refreshType;

    public DragonRefreshEvent(RefreshType refreshType) {
        this.refreshType = refreshType;
    }

    public RefreshType getRefreshType() {
        return this.refreshType;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum RefreshType {
        MIN_64(3840000L),
        MIN_32(1920000L),
        MIN_16(960000L),
        MIN_08(480000L),
        MIN_04(240000L),
        MIN_02(120000L),
        MIN_01(60000L),
        SLOWEST(32000L),
        SLOWER(16000L),
        SLOW(4000L),
        SEC(1000L),
        FAST(500L),
        FASTER(250L),
        FASTEST(125L),
        TICKS_2(75L),
        TICK(49L);

        private final long time;
        private long last;

        RefreshType(long paramLong) {
            this.time = paramLong;
            this.last = System.currentTimeMillis();
        }

        public boolean elapsed() {
            if (elapsed(this.last, this.time)) {
                this.last = System.currentTimeMillis();
                return true;
            }
            return false;
        }

        private boolean elapsed(long paramLong1, long paramLong2) {
            return (System.currentTimeMillis() - paramLong1 > paramLong2);
        }
    }
}
