package com.br.gabrielmartins.services.permission;

import org.bukkit.entity.Player;

public class PermissionsService {

    public boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permission);
    }
}
