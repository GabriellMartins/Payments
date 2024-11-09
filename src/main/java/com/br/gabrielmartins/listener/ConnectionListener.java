package com.br.gabrielmartins.listener;

import com.br.gabrielmartins.BukkitMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener implements Listener {

    private final BukkitMain plugin;

    public ConnectionListener(BukkitMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Bem-vindo ao servidor! Use /Payments para ver suas transações.");
    }
}
