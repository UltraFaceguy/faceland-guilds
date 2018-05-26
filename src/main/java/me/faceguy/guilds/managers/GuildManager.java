package me.faceguy.guilds.managers;

import java.util.ArrayList;
import java.util.List;
import me.faceguy.guilds.ActuallyGoodGuildsThePlugin;
import me.faceguy.guilds.data.Guild;

public class GuildManager {

  private final ActuallyGoodGuildsThePlugin plugin;
  private final List<Guild> guildList;

  public GuildManager(ActuallyGoodGuildsThePlugin plugin) {
    this.plugin = plugin;
    this.guildList = new ArrayList<>();
    loadAllGuilds();
  }

  public void loadAllGuilds() {
    guildList.clear();
    // guildList = plugin.getStorage().loadAll()
  }

}
