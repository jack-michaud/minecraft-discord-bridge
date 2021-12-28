package minecraft.discordbridge.lomz.me.discordbridge.listeners;

import minecraft.discordbridge.lomz.me.discordbridge.DiscordBridge;
import minecraft.discordbridge.lomz.me.discordbridge.discord.IDiscordWebhook;
import net.kyori.adventure.text.Component;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class MessageListener implements Listener {

    private final FileConfiguration _config;
    private final Plugin _plugin;
    private final Logger _logger;
    private IDiscordWebhook discordWebhook;

    public MessageListener(DiscordBridge plugin, Logger logger, IDiscordWebhook discordWebhook) {
        _plugin = plugin;
        _logger = logger;
        _config = plugin.getConfig();
        this.discordWebhook = discordWebhook;
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
      String joinedPlayerName = event.getPlayer().getName();
      this.discordWebhook.sendPlayerJoinEvent(joinedPlayerName);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
      String leftPlayerName = event.getPlayer().getName();
      this.discordWebhook.sendPlayerLeaveEvent(leftPlayerName);
    }
}
