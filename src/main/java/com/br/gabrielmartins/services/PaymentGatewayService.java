package com.br.gabrielmartins.services;

import org.bukkit.entity.Player;
import com.br.gabrielmartins.models.Product;

public class PaymentGatewayService {

    private final PaymentProcessorService paymentProcessorService;

    public PaymentGatewayService(PaymentProcessorService paymentProcessorService) {
        this.paymentProcessorService = paymentProcessorService;
    }

    public void startPaymentProcess(Player player, Product product) {
        paymentProcessorService.processPayment(player, product);
    }
}
