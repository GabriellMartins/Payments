package com.br.gabrielmartins.models;

public class Transaction {

    private String transactionId;
    private String playerId;
    private String productName;
    private double amount;
    private String status;

    public Transaction(String transactionId, String playerId, String productName, double amount, String status) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
