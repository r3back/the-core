package com.qualityplus.anvil.base.gui.anvilmain.strategy.gui.normal;

import com.qualityplus.anvil.api.box.Box;
import com.qualityplus.anvil.api.session.AnvilSession;
import com.qualityplus.anvil.base.config.Messages;
import com.qualityplus.anvil.base.gui.anvilmain.AnvilMainGUI;
import com.qualityplus.anvil.base.gui.anvilmain.request.ClickRequest;
import com.qualityplus.anvil.base.gui.anvilmain.strategy.CancellableClickRequestStrategy;
import com.qualityplus.anvil.base.session.AnvilSessionImpl;
import com.qualityplus.anvil.util.AnvilFinderUtil;
import com.qualityplus.anvil.util.ClickLocation;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.util.StringUtils;
import org.bukkit.entity.Player;

public final class NormalClickToCombineRequestStrategy extends CancellableClickRequestStrategy {
    @Override
    public boolean applies(final ClickRequest request) {
        return ClickLocation.of(request).isGuiInventory() &&
                request.isClickToCombineSlot() &&
                !request.isShiftClick();
    }

    @Override
    public void execute(final ClickRequest request) {
        cancelEvent(request);

        final AnvilSession session = request.getSession();
        final Player player = request.getPlayer().orElse(null);
        final AnvilMainGUI gui = request.getGui();
        final Box box = request.getBox();

        if (player == null) {
            return;
        }

        AnvilSession.SessionResult sessionResult = session.getSessionResult();

        if (!sessionResult.equals(AnvilSession.SessionResult.BOTH_ITEMS_SET)) {
            return;
        }

        final Messages.AnvilMessages msgs = box.files().messages().anvilMessages;

        if (AnvilFinderUtil.dontHavePermission(session, player)) {
            player.sendMessage(StringUtils.color(msgs.youDontHaveEnoughPermissions));
            return;
        }

        if (AnvilFinderUtil.getMoneyCost(session) > TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(player)) {
            player.sendMessage(StringUtils.color(msgs.youDontHaveEnoughMoney));
            return;
        }

        if (AnvilFinderUtil.getLevelsCost(session) > player.getLevel()) {
            player.sendMessage(StringUtils.color(msgs.youDontHaveEnoughLevels));
            return;
        }

        gui.setGiveItem(false);

        player.openInventory(new AnvilMainGUI(box, new AnvilSessionImpl(AnvilFinderUtil.getFinalItem(session), null, null)).getInventory());

    }
}
