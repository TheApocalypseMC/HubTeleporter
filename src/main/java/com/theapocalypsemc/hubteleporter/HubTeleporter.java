package com.theapocalypsemc.hubteleporter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SirFaizdat
 */
public class HubTeleporter extends JavaPlugin {

    long time = 0;
    int ypos = 0;

    public void onEnable() {
        this.saveDefaultConfig();
        time = Math.round(getConfig().getDouble("time") * 20L);
        ypos = getConfig().getInt("y-pos");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    if (p.getLocation().getY() < ypos) {
                        p.chat("/spawn");
                    }
                }
            }
        }, time, time);

        getLogger().info("Enabled " + getDescription().getVersion() + "!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("htreload")) {
            this.reloadConfig();
            time = Math.round(getConfig().getDouble("time") * 20L);
        }
        return true;
    }
}
