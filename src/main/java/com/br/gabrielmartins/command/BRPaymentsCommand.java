package com.br.gabrielmartins.command;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.services.TransactionHistoryService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BRPaymentsCommand implements CommandExecutor {

    private final BukkitMain plugin;

    public BRPaymentsCommand(BukkitMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                plugin.getTransactionHistoryService().displayTransactionHistory(player);
            }
            return true;
        }
        return false;
    }
}
