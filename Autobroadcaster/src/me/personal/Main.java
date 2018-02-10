package me.personal;

import java.util.ArrayList;
import java.util.Random;

import me.personal.commands.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public String prefix = getConfig().getString("prefix");
    public String noPerm = getConfig().getString("noPermission");
    public ArrayList<String> abclist = (ArrayList<String>) this.getConfig().getStringList("Autobroadcast");

    public void onEnable() {

        Bukkit.getLogger().info("AutoBroadcast is online!");

        saveDefaultConfig();

        autobroadcast();

        this.getCommand("autobroadcaster").setExecutor(new MainCommand(this));

    }

    public void onDisable() {

        Bukkit.getLogger().info("AutoBroadcast is offline!");

    }

    public void autobroadcast() {

        int time = getConfig().getInt("Time");

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                if(getConfig().getBoolean("WantHeader") == true) {

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Header")));

                }

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getList("Autobroadcast").get(new Random().nextInt(getConfig().getStringList("Autobroadcast").size())).toString()));

                if(getConfig().getBoolean("WantFooter") == true) {

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Footer")));

                }

            }

        }, 0, 20 * time);

    }

}
