package com.br.gabrielmartins.command;

import com.br.gabrielmartins.BukkitMain;
import com.br.gabrielmartins.menus.IndexMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaymentStoreCommand implements CommandExecutor {

    private final BukkitMain plugin;

    public PaymentStoreCommand(BukkitMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("open")) {
                new IndexMenu(plugin).openMenu(player);
                return true;
            }

            player.sendMessage("§cUso correto: /paymentstore open");
        }
        return false;
    }
}
