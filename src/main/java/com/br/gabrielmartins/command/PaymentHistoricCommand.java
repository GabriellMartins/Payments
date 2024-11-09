package com.br.gabrielmartins.command;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.services.TransactionHistoryService;
import com.br.gabrielmartins.models.Transaction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PaymentHistoricCommand implements CommandExecutor {

    private final BukkitMain plugin;

    public PaymentHistoricCommand(BukkitMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            TransactionHistoryService transactionHistoryService = plugin.getTransactionHistoryService();
            List<Transaction> transactions = transactionHistoryService.getTransactionHistory(player);

            if (transactions.isEmpty()) {
                player.sendMessage("§cVocê ainda não fez nenhuma compra.");
            } else {
                player.sendMessage("§6Histórico de Compras:");
                for (Transaction transaction : transactions) {
                    player.sendMessage("§7Produto: §a" + transaction.getProductName());
                    player.sendMessage("§7Preço: §aR$" + transaction.getAmount());
                    player.sendMessage("§7Status: §a" + transaction.getStatus());
                    player.sendMessage("§7Data: §a" + transaction.getTransactionId());
                    player.sendMessage("--------------------------");
                }
            }
        } else {
            sender.sendMessage("§cEste comando só pode ser usado por jogadores.");
        }
        return true;
    }
}
