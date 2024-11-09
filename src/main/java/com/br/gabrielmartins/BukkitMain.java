package com.br.gabrielmartins;

import com.br.gabrielmartins.command.BRPaymentsCommand;
import com.br.gabrielmartins.listener.ConnectionListener;
import com.br.gabrielmartins.listener.PaymentListener;
import com.br.gabrielmartins.services.*;
import com.br.gabrielmartins.listeners.*;
import com.br.gabrielmartins.services.database.CacheManagerService;
import com.br.gabrielmartins.services.database.DatabaseManagerService;
import com.br.gabrielmartins.services.permission.PermissionsService;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {

    private DatabaseManagerService databaseManagerService;
    private CacheManagerService cacheManagerService;
    private PaymentProcessorService paymentProcessorService;
    private ProductService productService;
    private TransactionHistoryService transactionHistoryService;
    private PaymentGatewayService paymentGatewayService;
    private DiscordIntegrationService discordIntegrationService;
    private PermissionsService permissionsService;

    @Override
    public void onEnable() {
        // Inicializando os serviços
        this.databaseManagerService = new DatabaseManagerService();
        this.cacheManagerService = new CacheManagerService();
        this.paymentProcessorService = new PaymentProcessorService(databaseManagerService, cacheManagerService);
        this.productService = new ProductService();
        this.transactionHistoryService = new TransactionHistoryService(databaseManagerService);
        this.paymentGatewayService = new PaymentGatewayService(paymentProcessorService);
        this.discordIntegrationService = new DiscordIntegrationService();
        this.permissionsService = new PermissionsService();

        // Registrando listeners
        getServer().getPluginManager().registerEvents(new PaymentListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(this), this);
        getServer().getPluginManager().registerEvents(new ConnectionListener(this), this);

        // Registrando comandos
        getCommand("brpayments").setExecutor(new BRPaymentsCommand(this));

        // Mensagem de inicialização
        getLogger().info("BRPayments plugin ativado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BRPayments plugin desativado.");
    }

    // Getters para os serviços
    public DatabaseManagerService getDatabaseManagerService() {
        return databaseManagerService;
    }

    public CacheManagerService getCacheManagerService() {
        return cacheManagerService;
    }

    public PaymentProcessorService getPaymentProcessorService() {
        return paymentProcessorService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public TransactionHistoryService getTransactionHistoryService() {
        return transactionHistoryService;
    }

    public PaymentGatewayService getPaymentGatewayService() {
        return paymentGatewayService;
    }

    public DiscordIntegrationService getDiscordIntegrationService() {
        return discordIntegrationService;
    }

    public PermissionsService getPermissionsService() {
        return permissionsService;
    }
}
