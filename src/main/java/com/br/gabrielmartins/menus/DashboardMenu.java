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

public class DashboardMenu implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final BukkitMain plugin;

    public DashboardMenu(BukkitMain plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, 27, "Menu Principal");
        addMenuItems();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private void addMenuItems() {
        // Criando botões no menu principal
        ItemStack categoriesButton = new ItemStack(Material.BOOK);
        ItemMeta meta = categoriesButton.getItemMeta();
        meta.setDisplayName("§6Ver Categorias");
        categoriesButton.setItemMeta(meta);
        inventory.setItem(11, categoriesButton);

        ItemStack historyButton = new ItemStack(Material.PAPER);
        ItemMeta meta2 = historyButton.getItemMeta();
        meta2.setDisplayName("§aHistórico de Transações");
        historyButton.setItemMeta(meta2);
        inventory.setItem(13, historyButton);

        ItemStack settingsButton = new ItemStack(Material.REDSTONE);
        ItemMeta meta3 = settingsButton.getItemMeta();
        meta3.setDisplayName("§eConfigurações");
        settingsButton.setItemMeta(meta3);
        inventory.setItem(15, settingsButton);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getInventory().equals(inventory))) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clickedItem.getType() == Material.BOOK) {
            player.openInventory(new CategoryMenu(plugin).getInventory());
        }

        if (clickedItem.getType() == Material.PAPER) {
            player.sendMessage("§7Histórico de transações (Em breve)");
        }

        if (clickedItem.getType() == Material.REDSTONE) {
            player.sendMessage("§7Abrindo configurações (Em breve)");
        }
    }
}
