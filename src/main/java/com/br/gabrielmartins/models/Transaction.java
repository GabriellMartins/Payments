package com.br.gabrielmartins.models;

import java.sql.Timestamp;

public class Transaction {

    private String transactionId;
    private String playerId;
    private String productName;
    private double amount;
    private String status;
    private Timestamp createdAt;

    public Transaction(String transactionId, String playerId, String productName, double amount, String status, Timestamp createdAt) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Transaction(String transactionId, String playerId, String productName, double amount, String status) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
        this.createdAt = new Timestamp(System.currentTimeMillis());  // Se o createdAt não for fornecido, define o timestamp atual
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getFormattedCreatedAt() {
        return createdAt.toString();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
