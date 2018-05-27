package me.faceguy.guilds.storage.guild;

import java.util.Set;
import me.faceguy.guilds.GuildsPlugin;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.managers.GuildManager;

public interface GuildStorage {
  void onLoad(GuildManager manager);
  void onUnload();
  void saveGuilds();
  Set<Guild> loadGuilds();
  boolean saveGuild(Guild guild);
  Guild loadGuild(String name);
}
