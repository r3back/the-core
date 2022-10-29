package com.qualityplus.bank.base.gui.deposit;

import com.qualityplus.assistant.inventory.GUI;
import com.qualityplus.bank.api.box.Box;
import com.qualityplus.bank.api.transaction.TransactionType;
import com.qualityplus.bank.base.gui.BankGUI;
import com.qualityplus.bank.base.gui.main.BankInterfaceGUI;
import com.qualityplus.bank.persistence.data.BankData;
import com.qualityplus.bank.persistence.data.BankTransaction;
import com.qualityplus.assistant.TheAssistantPlugin;
import com.qualityplus.assistant.api.event.SignCompletedEvent;
import com.qualityplus.assistant.api.util.IPlaceholder;
import com.qualityplus.assistant.inventory.SignGUI;
import com.qualityplus.assistant.util.inventory.InventoryUtils;
import com.qualityplus.assistant.util.placeholder.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import com.qualityplus.bank.base.gui.main.BankInterfaceGUI.GUIType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class BankDepositGUI extends BankGUI {
    private final BankDepositGUIConfig config;
    private final GUIType type;
    private final UUID uuid;

    public BankDepositGUI(Box box, Player player, GUIType type) {
        super(box.files().inventories().bankDepositGUIConfig, box);

        this.config = box.files().inventories().bankDepositGUIConfig;
        this.uuid = player.getUniqueId();
        this.type = type;
    }

    @Override
    public @NotNull Inventory getInventory() {
        InventoryUtils.fillInventory(inventory, config.getBackground());

        BankData bankData = box.service().getBankData(uuid).orElse(new BankData());

        List<IPlaceholder> placeholders = getPlaceholders(bankData);

        setItem(config.getCloseGUI());
        setItem(config.getGoBack());

        setItem(config.getDepositAll(), placeholders);
        setItem(config.getDepositHalf(), placeholders);
        setItem(config.getDepositCustomAmount(), placeholders);

        return inventory;
    }

    private List<IPlaceholder> getPlaceholders(BankData bankData){
        return Arrays.asList(
                new Placeholder("bank_balance", bankData.getMoney()),
                new Placeholder("player_balance", getBalance()),
                new Placeholder("player_half_balance", getHalf())
        );
    }


    private double getHalf(){
        return Math.max(0, TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid)) / 2);
    }

    private double getBalance(){
        return TheAssistantPlugin.getAPI().getAddons().getEconomy().getMoney(Bukkit.getOfflinePlayer(uuid));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        int slot = e.getSlot();

        e.setCancelled(true);

        if(!getTarget(e).equals(ClickTarget.INSIDE)) return;

        if (isItem(slot, config.getCloseGUI())) {
            e.setCancelled(true);
            player.closeInventory();
        }else if(isItem(slot, config.getGoBack())){
            player.openInventory(new BankInterfaceGUI(box, player, type).getInventory());
        }else if(isItem(slot, config.getDepositAll())){
            box.service().handleTransaction(player, new BankTransaction(getBalance(), TransactionType.DEPOSIT, type));
            player.openInventory(new BankDepositGUI(box, player, type).getInventory());
        }else if(isItem(slot, config.getDepositHalf())){
            box.service().handleTransaction(player, new BankTransaction(getHalf(), TransactionType.DEPOSIT, type));
            player.openInventory(new BankDepositGUI(box, player, type).getInventory());
        }else if(isItem(slot, config.getDepositCustomAmount())){
            SignGUI.builder()
                    .action(this::handleDeposit)
                    .withLines(box.files().messages().bankMessages.enterAmountToDeposit)
                    .uuid(player.getUniqueId())
                    .plugin(box.plugin())
                    .build()
                    .open();
        }
    }

    private void handleDeposit(SignCompletedEvent event){
        Player player = event.getPlayer();

        int value = 0;
        try {
            value = Integer.parseInt(event.getLines().get(0));
        }catch (NumberFormatException ignored){}

        box.service().handleTransaction(player, new BankTransaction(Math.max(0, value), TransactionType.DEPOSIT, type));

        Bukkit.getScheduler().runTaskLater(box.plugin(), () -> player.openInventory(new BankDepositGUI(box, player, type).getInventory()), 3);
    }
}
