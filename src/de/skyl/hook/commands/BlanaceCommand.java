package de.skyl.hook.commands;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.main.Core;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BlanaceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1){
            OfflinePlayer player = Bukkit.getPlayer(args[0]);
            sender.sendMessage("§7Der Spieler hat §b" + Core.getCore().getMain().getEconomyImplementer().getBalance(player) + " Dollar");
        }
        if(args.length == 3){
            if(args[0].equalsIgnoreCase("set")){
                OfflinePlayer player = Bukkit.getPlayer(args[1]);
                Core.getCore().getMain().getEconomyImplementer().withdrawPlayer(player, Double.parseDouble(args[2]));
                sender.sendMessage("§7Du hast den Kontostand von dem Spieler §b" + player.getName() + " §7auf §b" + args[2] + " Dollar §7gesetzt");
                }
            if(args[0].equalsIgnoreCase("add")){
                OfflinePlayer player = Bukkit.getPlayer(args[1]);
                Core.getCore().getMain().getEconomyImplementer().depositPlayer(player, Double.parseDouble(args[2]));
                sender.sendMessage("§7Du hast dem Spieler §b" + player.getName() + "§b " + args[2] + " Dollar §7hinzugefügt");
            }
            }
        return false;
    }
}
