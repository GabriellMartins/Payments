package com.br.gabrielmartins.menus;

import com.br.gabrielmartins.BukkitMain;

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

public class CategoryMenu implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final BukkitMain plugin;

    public CategoryMenu(BukkitMain plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, 27, "Categorias");
        addCategories();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private void addCategories() {
        ItemStack cosmeticsCategory = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta1 = cosmeticsCategory.getItemMeta();
        meta1.setDisplayName("§bCosméticos");
        cosmeticsCategory.setItemMeta(meta1);
        inventory.setItem(11, cosmeticsCategory);

        ItemStack abilitiesCategory = new ItemStack(Material.POTION);
        ItemMeta meta2 = abilitiesCategory.getItemMeta();
        meta2.setDisplayName("§aHabilidades Especiais");
        abilitiesCategory.setItemMeta(meta2);
        inventory.setItem(13, abilitiesCategory);

        ItemStack backButton = new ItemStack(Material.BARRIER);
        ItemMeta meta3 = backButton.getItemMeta();
        meta3.setDisplayName("§cVoltar");
        backButton.setItemMeta(meta3);
        inventory.setItem(26, backButton);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getInventory().equals(inventory))) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clickedItem.getType() == Material.DIAMOND_SWORD) {
            player.openInventory(new ProductMenu(plugin, "Cosméticos").getInventory());
        }

        if (clickedItem.getType() == Material.POTION) {
            player.openInventory(new ProductMenu(plugin, "Habilidades Especiais").getInventory());
        }

        if (clickedItem.getType() == Material.BARRIER) {
            player.openInventory(new DashboardMenu(plugin).getInventory());
        }
    }
}
