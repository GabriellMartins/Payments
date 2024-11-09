package com.br.gabrielmartins.listener;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.events.PaymentCompletedEvent;
import com.br.gabrielmartins.events.PaymentExpiredEvent;
import com.br.gabrielmartins.models.Transaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

public class PaymentListener implements Listener {

    private final BukkitMain plugin;

    public PaymentListener(BukkitMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        Transaction transaction = event.getTransaction();
        Player player = plugin.getServer().getPlayer(transaction.getPlayerId());

        if (player != null) {
            player.sendMessage("§aSua compra foi concluída com sucesso! Produto: " + transaction.getProductName());
            plugin.getDatabaseManagerService().updateTransactionStatus(transaction.getTransactionId(), "COMPLETO");
            plugin.getDiscordIntegrationService().sendDiscordMessage("Pagamento completado de " + player.getName() + " - Produto: " + transaction.getProductName());
        }
    }

    @EventHandler
    public void onPaymentExpired(PaymentExpiredEvent event) {
        Transaction transaction = event.getTransaction();
        Player player = plugin.getServer().getPlayer(transaction.getPlayerId());

        if (player != null) {
            player.sendMessage("§cO tempo para concluir o pagamento expirou. Sua compra foi cancelada.");
            plugin.getDatabaseManagerService().updateTransactionStatus(transaction.getTransactionId(), "EXPIRADO");
            plugin.getDiscordIntegrationService().sendDiscordMessage("Pagamento expirado de " + player.getName() + " - Produto: " + transaction.getProductName());
        }
    }
}
