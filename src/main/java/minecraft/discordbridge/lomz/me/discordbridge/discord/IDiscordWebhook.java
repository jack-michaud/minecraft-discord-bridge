package minecraft.discordbridge.lomz.me.discordbridge.discord;

public interface IDiscordWebhook {
  public void sendPlayerJoinEvent(String joinedPlayerName);
  public void sendPlayerLeaveEvent(String leftPlayerName);
}
