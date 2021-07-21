package de.skyl.hook.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.commands.BlanaceCommand;
import de.skyl.hook.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Manager {

    private Main main;
    private PluginManager pluginManager;

    public Manager(Main main){
        this.main = main;
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerListener(){
        pluginManager.registerEvents(new JoinListener(), main);
    }
    public void registerCommands(){
        main.getCommand("balance").setExecutor(new BlanaceCommand());
    }

    public Main getMain() {
        return main;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
