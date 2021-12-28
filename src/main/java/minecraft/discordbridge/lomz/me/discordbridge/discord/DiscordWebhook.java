package minecraft.discordbridge.lomz.me.discordbridge.discord;

import java.util.logging.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DiscordWebhook implements IDiscordWebhook {
  String webhookUrl;
  Logger _logger;

  public DiscordWebhook(Logger logger, String webhookUrl) {
    this._logger = logger;
    this.webhookUrl = webhookUrl;
  }

  void sendDiscordMessage(String content) {
    this._logger.info(String.format("Sending webhook: %s", content));
    this._logger.info(String.format(this.webhookUrl));
    try {
      HttpResponse<String> response = Unirest.post(this.webhookUrl)
        .header("content-type", "application/json")
        .body(String.format("{\"content\": \"%s\"}", content))
        .asString();
      this._logger.info(response.getBody());
    } catch (UnirestException e) {
      this._logger.warning(String.format("Could not send webhook: %s", e));
    }
  }

  public void sendPlayerLeaveEvent(String leftPlayerName) {
    this.sendDiscordMessage(String.format("%s is a stinky little boy and left the server", leftPlayerName));
  }

  public void sendPlayerJoinEvent(String joinedPlayerName) {
    this.sendDiscordMessage(String.format("%s joined the minecraft server, ready to play the gam", joinedPlayerName));
  }

}
