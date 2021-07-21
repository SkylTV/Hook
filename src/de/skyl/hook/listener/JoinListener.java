package de.skyl.hook.listener;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.main.Core;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        OfflinePlayer player = event.getPlayer();
        if(!Core.getCore().getMain().getEconomyImplementer().isPlayerRegistered(player)){
            Core.getCore().getMain().getEconomyImplementer().registerPlayer(player);
        }

    }

}
