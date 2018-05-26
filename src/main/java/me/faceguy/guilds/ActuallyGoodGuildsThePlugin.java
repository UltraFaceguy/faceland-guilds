package me.faceguy.guilds;

import java.io.File;
import me.faceguy.guilds.managers.GuildManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class ActuallyGoodGuildsThePlugin extends JavaPlugin {

  private YamlConfiguration configYAML;
  private GuildManager guildManager;

  public void onEnable() {
    configYAML = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
    guildManager = new GuildManager(this);
  }

  public void onDisable() {
    //storage.saveAll();
    HandlerList.unregisterAll(this);
    configYAML = null;
    guildManager = null;
  }

  public GuildManager getGuildManager() {
    return guildManager;
  }
}
