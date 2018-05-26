package me.faceguy.guilds.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GuildPlayer {
  private String name;
  private OfflinePlayer player;
  private Long lastLogin;
  private int glory;

  public GuildPlayer(Player player) {
    this.name = player.getName();
    this.player = player;
    this.lastLogin = System.currentTimeMillis();
  }
}
