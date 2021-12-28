package minecraft.discordbridge.lomz.me.discordbridge;

import minecraft.discordbridge.lomz.me.discordbridge.listeners.MessageListener;
import minecraft.discordbridge.lomz.me.discordbridge.discord.DiscordWebhook;
import minecraft.discordbridge.lomz.me.discordbridge.discord.IDiscordWebhook;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordBridge extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Logger logger = getLogger();
        if(getConfig().getBoolean("disabled")) {
            getLogger().warning("DiscordBridge is disabled in the plugins/DiscordBridge/config.yml file.");
            return;
        }
        String webhookUrl = getConfig().getString("discord_webhook_url");
        if (webhookUrl.isEmpty()) {
            getLogger().warning("Must set discord_webhook_url in config.");
            return;
        }
        IDiscordWebhook discordWebhook = new DiscordWebhook(logger, webhookUrl);
        new MessageListener(this, logger, discordWebhook);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
