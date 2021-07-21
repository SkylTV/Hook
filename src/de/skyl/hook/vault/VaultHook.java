package de.skyl.hook.vault;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.main.Core;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {

    private Economy provider;

    public void hook(){
        provider = Core.getCore().getMain().getEconomyImplementer();
        Bukkit.getServicesManager().register(Economy.class, this.provider, Core.getCore().getMain(), ServicePriority.Normal);
        Bukkit.getConsoleSender().sendMessage("§aVaultAPI hooked into §b" + Core.getCore().getMain().getName());
    }

    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, this.provider);
        Bukkit.getConsoleSender().sendMessage("§aVaultAPI unhooked from §b" + Core.getCore().getMain().getName());
    }

}
