package com.br.gabrielmartins.menus;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.models.Product;
import com.br.gabrielmartins.services.ProductService;
import com.br.gabrielmartins.services.PaymentProcessorService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IndexMenu {

    private final BukkitMain plugin;
    private final Inventory inventory;

    public IndexMenu(BukkitMain plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, 9, "§6Loja de Pagamentos");
    }

    public void openMenu(Player player) {
        this.setMenuItems(player);
        player.openInventory(inventory);
    }

    private void setMenuItems(Player player) {
        ProductService productService = plugin.getProductService();
        PaymentProcessorService paymentProcessorService = plugin.getPaymentProcessorService();

        Product product1 = productService.getProductByName("Kit Iniciante");
        ItemStack productItem1 = createProductItem(product1);
        inventory.setItem(0, productItem1);

        Product product2 = productService.getProductByName("Kit VIP");
        ItemStack productItem2 = createProductItem(product2);
        inventory.setItem(1, productItem2);


    }

    private ItemStack createProductItem(Product product) {
        ItemStack item = new ItemStack(org.bukkit.Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§a" + product.getName());
            meta.setLore(java.util.Arrays.asList(
                    "§7Preço: §aR$ " + product.getPrice(),
                    "§7Categoria: §b" + product.getCategory(),
                    "§7Tipo de Recurso: §6" + product.getResourceType(),
                    "§7Ação: §e" + product.getAction(),
                    "§7Descrição: §7" + product.getDescription(),
                    "§7Clique para comprar!"
            ));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void handleMenuClick(Player player, int slot) {
        ProductService productService = plugin.getProductService();
        PaymentProcessorService paymentProcessorService = plugin.getPaymentProcessorService();

        if (slot == 0) {
            Product product = productService.getProductByName("Kit Iniciante");
            paymentProcessorService.processPayment(player, product);
        } else if (slot == 1) {
            Product product = productService.getProductByName("Kit VIP");
            paymentProcessorService.processPayment(player, product);
        }
    }
}
