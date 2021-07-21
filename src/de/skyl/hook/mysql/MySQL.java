package de.skyl.hook.mysql;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.hook.main.Core;
import io.papermc.paper.event.entity.EntityLoadCrossbowEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {
    public static Connection con;
    public static String host ;
    public static int port ;
    public static String database ;
    public static String username;
    public static String password ;



    public static void connect() {
        if(!isConnected()) {
            try {
                File file = new File(Core.getCore().getMain().getDataFolder() + "/mysql.yml");
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                MySQL.host = configuration.getString("host");
                MySQL.port =configuration.getInt("port");
                MySQL.database = configuration.getString("database");
                MySQL.username = configuration.getString("username");
                MySQL.password = configuration.getString("password");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":"+port+"/"+database+"?useSSL=false", username, password);
                System.out.println("[PunishSystem] Du wurdest erflogreich mit der Datenbank verbunden");
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String sql) {
        if(isConnected()) {
            PreparedStatement ps;
            try {
                File file = new File(Core.getCore().getMain().getDataFolder() + "/mysql.yml");
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                MySQL.host = configuration.getString("host");
                MySQL.port =configuration.getInt("port");
                MySQL.database = configuration.getString("database");
                MySQL.username = configuration.getString("username");
                MySQL.password = configuration.getString("password");
                ps = con.prepareStatement(sql);
                return ps;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[PunishSystem] Du wurdest erflogreich mit der Datenbank getrennt");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static void createTables(){
        try{
            PreparedStatement preparedStatement = getStatement("CREATE TABLE IF NOT EXISTS balance (Spieler VARCHAR(100), UUID VARCHAR(100), Balance DOUBLE PRECISION);");
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
