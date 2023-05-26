package com.qualityplus.bank.util;

import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.bank.api.request.TrxRequest;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public final class BankMessageUtil {
    public void sendMessageToTrxActor(final TrxRequest request, final String message) {
        if (!request.isSendMsg()) {
            return;
        }

        final Player player = Bukkit.getPlayer(request.getBankData().getUuid());

        if (player == null) {
            return;
        }

        player.sendMessage(StringUtils.color(message));
    }
}
