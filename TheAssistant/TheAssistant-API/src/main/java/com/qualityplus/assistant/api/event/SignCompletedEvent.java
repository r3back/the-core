package com.qualityplus.assistant.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
@AllArgsConstructor
public final class SignCompletedEvent {
    private final Player player;
    private final List<String> lines;
}