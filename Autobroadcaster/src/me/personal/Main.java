package me.personal;

import java.util.ArrayList;
import java.util.Random;

import me.personal.commands.MainCommand;
import me.personal.events.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public final String prefix = getConfig().getString("prefix");
    public final String noPerm = getConfig().getString("noPermission");
    public ArrayList<String> abclist = (ArrayList<String>) this.getConfig().getStringList("Autobroadcast");
    public int chatActivity;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("AutoBroadcast is online!");

        getConfig().options().copyDefaults(true);
        saveConfig();

        autobroadcast();

        this.getCommand("autobroadcaster").setExecutor(new MainCommand(this));

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("AutoBroadcast is offline!");

    }

    public void autobroadcast() {

        long time = getConfig().getInt("Time") * 20;
        int interval = getConfig().getInt("ChatInterval");

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {

                if(!(chatActivity < interval)) {

                    chatActivity = 0;

                    if(getConfig().getBoolean("WantHeader") == true) {

                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Header")));

                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getList("Autobroadcast").get(new Random().nextInt(getConfig().getStringList("Autobroadcast").size())).toString()));

                    if(getConfig().getBoolean("WantFooter") == true) {

                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Footer")));

                    }

                }

            }

        }, 0, time);

    }

}
