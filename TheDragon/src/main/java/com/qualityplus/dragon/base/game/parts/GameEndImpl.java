package com.qualityplus.dragon.base.game.parts;

import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.dragon.TheDragon;
import com.qualityplus.dragon.api.game.dragon.TheDragonEntity;
import com.qualityplus.dragon.api.game.part.GameEnd;
import com.qualityplus.dragon.api.game.ranking.GameRanking;
import com.qualityplus.dragon.api.game.reward.DragonReward;
import com.qualityplus.dragon.api.service.UserDBService;
import com.qualityplus.dragon.base.configs.Messages;
import com.qualityplus.dragon.base.configs.DragonRewardsFile;
import com.qualityplus.dragon.base.game.player.EventPlayer;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Bukkit;

import java.util.*;

@Component
public final class GameEndImpl implements GameEnd {
    private @Inject UserDBService userDBService;
    private @Inject GameRanking gameRanking;
    private @Inject Messages messages;
    private @Inject DragonRewardsFile rewards;

    @Override
    public void sendFinishMessage() {

        gameRanking.refreshRanking();

        TheDragon.getApi()
                .getUserService()
                .getUsers()
                .forEach(this::managePlayer);
    }

    private void managePlayer(EventPlayer player){
        List<String> finalMessage = StringUtils.processMulti(messages.setupMessages.newGameEndMessage, gameRanking.getPlaceholders(player));

        player.sendMessage(finalMessage);

        TheDragonEntity theDragonEntity = TheDragon.getApi().getDragonService().getActiveDragon();

        Optional<DragonReward> reward = getRewardByDamage(player);

        List<IPlaceholder> placeholders = Arrays.asList(
                new Placeholder("thedragon_player_reward_xp", theDragonEntity.getXp()),
                new Placeholder("player", player.getName()));

        if(!reward.isPresent()) return;

        reward.get().getCommands().forEach(command -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), StringUtils.processMulti(command, placeholders)));
    }


    private Optional<DragonReward> getRewardByDamage(EventPlayer eventPlayer){
        List<DragonReward> dragonRewards = new ArrayList<>(rewards.dragonRewards);
        dragonRewards.sort((o1, o2) -> o2.getDamageDone() - o1.getDamageDone());
        return dragonRewards.stream()
                .filter(dragonReward -> eventPlayer.getDamage() >= dragonReward.getDamageDone())
                .findFirst();
    }


}
