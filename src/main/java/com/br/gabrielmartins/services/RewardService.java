package com.br.gabrielmartins.services;

import com.br.gabrielmartins.models.Product;
import org.bukkit.entity.Player;

public class RewardService {

    public void giveReward(Player player, Product product) {
        switch (product.getResourceType()) {
            case "item":
                giveItem(player, product);
                break;
            case "permission":
                givePermission(player, product);
                break;
            case "command":
                executeCommand(player, product);
                break;
            default:
                break;
        }
    }

    private void giveItem(Player player, Product product) {
        player.getInventory().addItem(new org.bukkit.inventory.ItemStack(org.bukkit.Material.DIAMOND_SWORD, 1));
        player.sendMessage("§aVocê recebeu um item: " + product.getName());
    }

    private void givePermission(Player player, Product product) {
        player.addAttachment(player.getServer().getPluginManager().getPlugin("Payments"), product.getAction(), true);
        player.sendMessage("§aVocê recebeu a permissão: " + product.getAction());
    }

    private void executeCommand(Player player, Product product) {
        player.getServer().dispatchCommand(player, product.getAction());
        player.sendMessage("§aO comando foi executado: " + product.getAction());
    }
}
