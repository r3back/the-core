package com.qualityplus.alchemist.listener;

import com.qualityplus.alchemist.api.box.Box;
import com.qualityplus.alchemist.api.service.StandService;
import com.qualityplus.alchemist.api.session.StandSession;
import com.qualityplus.alchemist.base.gui.brewing.AlchemistStandGUI;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.util.StringUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import com.qualityplus.assistant.util.placeholder.PlaceholderBuilder;
import com.qualityplus.assistant.util.player.PlayerUtils;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.platform.core.annotation.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.Optional;

/**
 * Brewing stand listener
 */
@Component
public final class VanillaStandListener implements Listener {
    private @Inject StandService standService;
    private @Inject Box box;

    /**
     *
     * @param e {@link PlayerInteractEvent}
     */
    @EventHandler
    public void onOpenBrewingStand(final PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        final Block bl = e.getClickedBlock();

        if (bl == null || bl.getType() != Material.BREWING_STAND) {
            return;
        }

        if (!this.box.getFiles().config().isOpenAsVanillaBrewingStand()) {
            return;
        }

        final Location location = bl.getLocation();

        final Optional<StandSession> session = this.standService.getSession(location);

        final Player player = e.getPlayer();

        if (session.isPresent()) {
            final String using = session
                    .map(StandSession::getPlayer)
                    .map(PlayerUtils::getPlayerName)
                    .orElse("");

            final List<IPlaceholder> placeholders = PlaceholderBuilder.create()
                    .with(new Placeholder("player", using))
                    .get();

            player.sendMessage(StringUtils.processMulti(this.box.getFiles().messages().getStandMessages().getAlreadyInUse(), placeholders));
            return;
        }

        e.setCancelled(true);

        this.standService.addSession(player.getUniqueId(), location);

        player.openInventory(new AlchemistStandGUI(this.box, location, this.standService).getInventory());
    }
}
