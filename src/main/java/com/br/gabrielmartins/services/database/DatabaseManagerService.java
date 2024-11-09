package com.br.gabrielmartins.services.database;

import com.br.gabrielmartins.models.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagerService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao conectar ao banco de dados.");
        }
    }

    /**
     * Salva uma nova transação no banco de dados.
     * @param transaction A transação a ser salva.
     */
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

    /**
     * Atualiza o status de uma transação no banco de dados.
     * @param transactionId O ID da transação.
     * @param status O novo status da transação.
     */
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

    /**
     * Recupera as transações de um jogador no banco de dados.
     * @param playerId O ID do jogador.
     * @return Uma lista de transações do jogador.
     */
    public List<Transaction> getTransactionsByPlayer(String playerId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE player_id = ? ORDER BY created_at DESC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String transactionId = resultSet.getString("transaction_id");
                String productName = resultSet.getString("product_name");
                double price = resultSet.getDouble("price");
                String status = resultSet.getString("status");
                Timestamp createdAt = resultSet.getTimestamp("created_at");

                Transaction transaction = new Transaction(transactionId, playerId, productName, price, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
