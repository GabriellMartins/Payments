package com.br.gabrielmartins.services.database;

import com.br.gabrielmartins.models.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManagerService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, player_id, product_name, price, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transaction.getTransactionId());
            statement.setString(2, transaction.getPlayerId());
            statement.setString(3, transaction.getProductName());
            statement.setDouble(4, transaction.getAmount());
            statement.setString(5, transaction.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransactionStatus(String transactionId, String status) {
        String updateQuery = "UPDATE transactions SET status = ? WHERE transaction_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, status);
            statement.setString(2, transactionId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
