package me.faceguy.guilds.storage.player;

import java.util.Set;
import java.util.UUID;
import me.faceguy.guilds.data.GuildPlayer;
import me.faceguy.guilds.managers.PlayerManager;

public interface PlayerStorage {
  void onLoad(PlayerManager manager);
  void onUnload();
  boolean savePlayer(GuildPlayer player);
  GuildPlayer loadPlayer(UUID uuid);
  Set<GuildPlayer> loadAllPlayers();
  boolean saveAllPlayers(Set<GuildPlayer> players);
}
