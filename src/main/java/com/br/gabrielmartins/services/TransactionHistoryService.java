package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Transaction;
import com.br.gabrielmartins.services.database.DatabaseManagerService;
import org.bukkit.entity.Player;

import java.util.List;

public class TransactionHistoryService {

    private final DatabaseManagerService databaseManagerService;

    public TransactionHistoryService(DatabaseManagerService databaseManagerService) {
        this.databaseManagerService = databaseManagerService;
    }

    public void displayTransactionHistory(Player player) {
        List<Transaction> transactions = databaseManagerService.getTransactionsByPlayer(player.getUniqueId().toString());
        player.sendMessage("§6Histórico de Transações:");
        for (Transaction transaction : transactions) {
            player.sendMessage("Produto: " + transaction.getProductName() + " | Status: " + transaction.getStatus() + " | Valor: " + transaction.getAmount());
        }
    }
}
