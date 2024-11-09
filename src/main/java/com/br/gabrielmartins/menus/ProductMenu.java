package com.br.gabrielmartins.menus;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.models.Product;
import com.br.gabrielmartins.services.ProductService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

import java.util.List;

public class ProductMenu implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final BukkitMain plugin;
    private final ProductService productService;
    private final String category;

    public ProductMenu(BukkitMain plugin, String category) {
        this.plugin = plugin;
        this.productService = plugin.getProductService();
        this.category = category;
        this.inventory = Bukkit.createInventory(this, 27, "Produtos - " + category);
        addProductsToMenu();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
    private void addProductsToMenu() {
        List<Product> products = productService.getProductsByCategory(category);

        int slot = 0;
        for (Product product : products) {
            if (slot >= 26) break;
            ItemStack itemStack = new ItemStack(Material.DIAMOND);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName("§6" + product.getName());
            meta.setLore(List.of("§7Preço: R$" + product.getPrice()));
            itemStack.setItemMeta(meta);

            inventory.setItem(slot, itemStack);
            slot++;
        }

        ItemStack backButton = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = backButton.getItemMeta();
        backMeta.setDisplayName("§cVoltar");
        backButton.setItemMeta(backMeta);
        inventory.setItem(26, backButton);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getInventory().equals(inventory))) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clickedItem.getType() == Material.DIAMOND) {
            String productName = clickedItem.getItemMeta().getDisplayName().substring(2); // Remove o prefixo "§6"
            Product selectedProduct = (Product) productService.getProducts(productName);

            if (selectedProduct != null) {
                player.sendMessage("§7Você comprou o produto: §6" + selectedProduct.getName() + "§7 por R$" + selectedProduct.getPrice());
            } else {
                player.sendMessage("§cProduto não encontrado!");
            }
        }

        if (clickedItem.getType() == Material.BARRIER) {
            player.openInventory(new CategoryMenu(plugin).getInventory());
        }
    }
}
