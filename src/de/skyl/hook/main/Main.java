package de.skyl.hook.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.mysql.MySQL;
import de.skyl.hook.vault.EconomyImplementer;
import de.skyl.hook.vault.VaultHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private Main instance;
    private File file;
    private EconomyImplementer economyImplementer;
    private VaultHook vaultHook;

    @Override
    public void onLoad() {
        instance = this;
        createFile();
    }

    @Override
    public void onEnable() {
        Core.setCore(new Core(this));
        Core.getCore().enableCore();
        if(!MySQL.isConnected()){
            MySQL.connect();
            MySQL.createTables();
        }
        economyImplementer = new EconomyImplementer();
        vaultHook = new VaultHook();
        vaultHook.hook();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if(MySQL.isConnected()){
            MySQL.disconnect();
        }
        vaultHook.unHook();
    }

    public void createFile(){
            try {
                if(!getDataFolder().exists()){
                    getDataFolder().mkdir();
                }
                file = new File(getDataFolder() + "/mysql.yml");
                if(!file.exists()){
                file.createNewFile();
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                configuration.set("host", "localhost");
                configuration.set("port", 3306);
                configuration.set("username", "test");
                configuration.set("password", "test");
                configuration.set("database", "test");
                configuration.save(file);
                }

            } catch (IOException e) {
                e.printStackTrace();

        }
    }

    public Main getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public File getFile() {
        return file;
    }

    public EconomyImplementer getEconomyImplementer() {
        return economyImplementer;
    }
}
