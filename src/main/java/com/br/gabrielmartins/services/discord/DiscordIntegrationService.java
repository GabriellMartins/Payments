package com.br.gabrielmartins.services.discord;

public class DiscordIntegrationService {

    public void initialize() {
        System.out.println("Discord integrado!");
    }

    public void sendPaymentNotification(String playerName, double amount) {
        System.out.println("Enviando notificação ao Discord: " + playerName + " pagou R$" + amount);
    }
}
