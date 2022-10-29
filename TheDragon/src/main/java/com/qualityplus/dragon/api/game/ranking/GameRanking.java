package com.qualityplus.dragon.api.game.ranking;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.dragon.base.game.player.EventPlayer;

import java.util.List;
import java.util.Optional;

public interface GameRanking {
    List<IPlaceholder> getPlaceholders(EventPlayer eventPlayer);

    Optional<EventPlayer> getByRank(int ranking);

    void refreshRanking();
}
