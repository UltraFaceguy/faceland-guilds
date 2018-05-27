package me.faceguy.guilds.managers;

import java.util.HashMap;
import java.util.Map;
import me.faceguy.guilds.GuildsPlugin;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.storage.guild.GuildStorage;
import me.faceguy.guilds.storage.guild.MySQLGuildStorage;

public class GuildManager {
  private final Map<String, Guild> guildCache = new HashMap<>();
  private GuildStorage storage;
  private GuildsPlugin plugin;
  public GuildManager(GuildsPlugin instance) {
    plugin = instance;
  }

  public void onLoad() {
    switch (plugin.getConfig().getString("storage.guilds")) {
      case "mysql":
        storage = new MySQLGuildStorage();
    }

    if (storage == null) {
      throw new RuntimeException("Failed to initialize a storage type for guilds");
    }
    storage.onLoad(this);
  }

  public void onUnload() {
    storage.saveGuilds();
    guildCache.clear();
    storage.onUnload();
    storage = null;
    plugin = null;
  }

  public Guild getGuild(String name) {
    return guildCache.get(name);
  }

  public boolean addGuild(Guild guild) {
    if (getGuild(guild.getName()) == null) {
      guildCache.put(guild.getName(), guild);
      return true;
    }
    return false;
  }

  public boolean removeGuild(Guild guild) {
    return guildCache.remove(guild.getName()) != null;
  }

  public GuildStorage getStorage() {
    return storage;
  }

  public void setStorage(GuildStorage storage) {
    this.storage = storage;
  }

  public GuildsPlugin getPlugin() {
    return plugin;
  }
}
