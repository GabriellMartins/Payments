package com.br.gabrielmartins.services;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;

import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;

public class DiscordIntegrationService {

    private static final String DISCORD_TOKEN = "";
    private static final String CHANNEL_ID = "1302719442278416586";

    private net.dv8tion.jda.api.JDA jda;

    public DiscordIntegrationService() {
        jda = JDABuilder.createDefault(DISCORD_TOKEN).build();
    }

    public void notifyPayment(Player player, String productName, double price) {
        String message = player.getName() + " comprou " + productName + " por R$" + price;

        sendDiscordMessage(message);
    }

    public void sendDiscordMessage(String message) {
        net.dv8tion.jda.api.entities.channel.concrete.TextChannel channel = jda.getTextChannelById(CHANNEL_ID);

        if (channel != null) {
            channel.sendMessage(message).queue();
        } else {
            System.out.println("Canal do Discord não encontrado.");
        }
    }

    public void sendEmbeddedMessage(String title, String description, String footer) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setFooter(footer);

        net.dv8tion.jda.api.entities.channel.concrete.TextChannel channel = jda.getTextChannelById(CHANNEL_ID);

        if (channel != null) {
            channel.sendMessageEmbeds(embed.build()).queue();
        } else {
            System.out.println("Canal do Discord não encontrado.");
        }
    }

    public void sendPaymentDetails(String playerName, String productName, double price) {
        String title = "Pagamento Realizado";
        String description = "O jogador **" + playerName + "** comprou o produto **" + productName + "** por R$" + price;
        String footer = "BRPayments | Transação completada com sucesso.";

        sendEmbeddedMessage(title, description, footer);
    }

    public void sendErrorMessage(String errorMessage) {
        sendDiscordMessage("⚠️ **Erro**: " + errorMessage);
    }
}
