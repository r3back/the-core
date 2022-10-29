package com.qualityplus.dragon.base.events;

import com.qualityplus.dragon.api.event.DragonGameEvent;
import com.qualityplus.dragon.api.event.SwitchableEvents;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public final class SwitchableEventImpl implements SwitchableEvents {
    private final Map<Integer, DragonGameEvent> events;
    private DragonGameEvent currentEvent;

    public DragonGameEvent getNext(){
        if(currentEvent == null){
            currentEvent = events.getOrDefault(1, null);
        }else{
            Integer next = getCurrentPosEvent();
            if(next != null){
                next++;
                currentEvent = events.containsKey(next) ? events.get(next) : events.getOrDefault(1, null);
            }
        }
        return currentEvent;
    }

    public DragonGameEvent getCurrentEvent(){
        return currentEvent;
    }

    private Integer getCurrentPosEvent(){
        for(Integer integer : events.keySet())
            if(events.get(integer) == currentEvent) return integer;
        return null;
    }
}
