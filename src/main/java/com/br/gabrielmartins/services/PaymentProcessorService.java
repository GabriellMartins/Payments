package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Transaction;
import com.br.gabrielmartins.models.Product;
import com.br.gabrielmartins.services.database.CacheManagerService;
import com.br.gabrielmartins.services.database.DatabaseManagerService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.bukkit.entity.Player;


public class PaymentProcessorService {

    private DatabaseManagerService databaseManagerService;
    private CacheManagerService cacheManagerService;

    public PaymentProcessorService(DatabaseManagerService databaseManagerService, CacheManagerService cacheManagerService) {
        this.databaseManagerService = databaseManagerService;
        this.cacheManagerService = cacheManagerService;
    }

    public BitMatrix generatePIXQRCode(Player player, Product product) {
        String pixQRCodeData = generatePIXPaymentData(player, product);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            return qrCodeWriter.encode(pixQRCodeData, BarcodeFormat.QR_CODE, 250, 250);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generatePIXPaymentData(Player player, Product product) {
        return "pix://payment?player=" + player.getName() + "&product=" + product.getName() + "&price=" + product.getPrice();
    }

    public void processPayment(Player player, Product product) {
        String transactionId = "txn_" + System.currentTimeMillis();
        Transaction transaction = new Transaction(transactionId, player.getUniqueId().toString(), product.getName(), product.getPrice(), "PENDENTE");

        databaseManagerService.saveTransaction(transaction);
        cacheManagerService.cacheTransaction(transactionId, transaction);

        transaction.setStatus("COMPLETO");
        databaseManagerService.updateTransactionStatus(transactionId, transaction.getStatus());

        player.sendMessage("§aPagamento Completo! Você comprou: " + product.getName());
    }
}
