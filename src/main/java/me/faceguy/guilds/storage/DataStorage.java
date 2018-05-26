package me.faceguy.guilds.storage;

import java.util.Collection;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.data.GuildPlayer;

public interface DataStorage {

  void saveAllGuilds();

  void saveAllGuildPlayers();

  void saveGuildPlayer(GuildPlayer guildPlayer);

  Collection<Guild> loadGuilds();

  Collection<GuildPlayer> loadGuildPlayers();

  GuildPlayer loadGuildPlayer();
}
