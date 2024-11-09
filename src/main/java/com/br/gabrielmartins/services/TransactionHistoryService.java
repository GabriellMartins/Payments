package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Transaction;
import com.br.gabrielmartins.services.database.DatabaseManagerService;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryService {

    private final DatabaseManagerService databaseManagerService;

    public TransactionHistoryService(DatabaseManagerService databaseManagerService) {
        this.databaseManagerService = databaseManagerService;
    }

    public void displayTransactionHistory(Player player) {
        List<Transaction> transactions = getTransactionHistory(player);

        player.sendMessage("§6Histórico de Transações:");
        if (transactions.isEmpty()) {
            player.sendMessage("§cVocê não tem transações realizadas.");
        } else {
            // Exibe as transações no chat
            for (Transaction transaction : transactions) {
                player.sendMessage("Produto: " + transaction.getProductName()
                        + " | Status: " + transaction.getStatus()
                        + " | Valor: R$" + transaction.getAmount()
                        + " | Data: " + transaction.getCreatedAt());
            }
        }
    }

    public List<Transaction> getTransactionHistory(Player player) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE player_id = ? ORDER BY created_at DESC";

        try (Connection connection = databaseManagerService.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String transactionId = resultSet.getString("transaction_id");
                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                String status = resultSet.getString("status");
                String createdAt = resultSet.getString("created_at");

                Transaction transaction = new Transaction(transactionId, player.getUniqueId().toString(), productName, price, status);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
