package me.faceguy.guilds.storage;

import java.io.File;
import java.util.Collection;
import me.faceguy.guilds.ActuallyGoodGuildsThePlugin;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.data.GuildPlayer;
import org.bukkit.configuration.file.YamlConfiguration;

public class JsonStorage implements DataStorage {

  private final ActuallyGoodGuildsThePlugin plugin;
  private YamlConfiguration guildConfiguration;
  private YamlConfiguration garrisonConfiguration;

  public JsonStorage(ActuallyGoodGuildsThePlugin plugin) {
    this.plugin = plugin;
    this.guildConfiguration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "data.json"));
    this.garrisonConfiguration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "data.json"));
  }

  @Override
  public void saveAllGuilds() {
    // stuff
  }

  @Override
  public void saveAllGuildPlayers() {

  }

  @Override
  public void saveGuildPlayer(GuildPlayer guildPlayer) {

  }

  @Override
  public Collection<Guild> loadGuilds() {
    return null;
  }

  @Override
  public Collection<GuildPlayer> loadGuildPlayers() {
    return null;
  }

  @Override
  public GuildPlayer loadGuildPlayer() {
    return null;
  }
}
