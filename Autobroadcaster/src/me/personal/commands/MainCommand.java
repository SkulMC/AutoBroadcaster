package me.personal.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.personal.Main;

public class MainCommand implements CommandExecutor {

    Main pl;

    public MainCommand(Main plugin) {

        this.pl = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("autobroadcaster")) {

            if(args.length == 0) {

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &ehttps://www.spigotmc.org/resources/autobroadcaster.49662/"));

                return true;

            }

            if(args.length >= 1) {

                if(args[0].equalsIgnoreCase("reload")) {

                    if(p.hasPermission("abc.reload")) {

                        pl.reloadConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &bPlugin has been reloaded!"));

                        return true;

                    } else {

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " " + pl.noPerm));

                        return true;

                    }

                } else if(args[0].equalsIgnoreCase("add")) {

                    if(p.hasPermission("abc.add")) {

                        if(args.length > 1) {

                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; ++i) {
                                sb.append(args[i]).append(' ');
                            }

                            pl.abclist.add(sb.toString());

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &bAdded '" + sb.toString() + "' to the config!"));
                            pl.getConfig().set("Autobroadcast", pl.abclist);
                            pl.saveConfig();
                            pl.reloadConfig();

                        } else {

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &cPlease add a message!"));

                        }

                    } else {

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " " + pl.noPerm));

                    }

                } else if(args[0].equalsIgnoreCase("set")) {

                    if(args.length >= 2) {

                        if(args[1].equalsIgnoreCase("time")) {

                            if(p.hasPermission("abc.set.time")) {

                                if(args.length == 3) {

                                    if(NumberUtils.isNumber(args[2])) {

                                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &bTime has been set to '" + args[2].toString() + "!'"));

                                        pl.getConfig().set("Time", Integer.valueOf(args[2]));
                                        pl.saveConfig();
                                        pl.reloadConfig();

                                    } else {

                                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &bTime must be a number!"));

                                    }

                                } else {

                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &b/autobroadcaster set time <seconds>"));

                                }

                            } else {

                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " " + pl.noPerm));

                            }

                        }

                    } else {

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &b/autobroadcaster set time <seconds>"));

                    }

                } else {

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.prefix + " &cUsage: /autobroadcaster <reload, add, set>"));

                }

            }

        }

        return true;
    }

}
