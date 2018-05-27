package me.faceguy.guilds;

import me.faceguy.guilds.managers.GuildManager;
import me.faceguy.guilds.utils.config.ConfigWrapper;
import me.faceguy.guilds.utils.lang.Lang;
import org.bukkit.plugin.java.JavaPlugin;

public class GuildsPlugin extends JavaPlugin {

  private GuildManager guildManager;

  public void onEnable() {

    // need to load the config

    // GuildManager
    guildManager = new GuildManager(this);
    guildManager.onLoad();

    // init lang
    Lang.init(new ConfigWrapper(this, "lang.yml"));
  }

  public void onDisable() {
    guildManager.onUnload();
    guildManager = null;
  }

  public GuildManager getGuildManager() {
    return guildManager;
  }
}
