package me.faceguy.guilds.storage.guild;

import java.util.Collection;
import java.util.Set;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.managers.GuildManager;

public interface GuildStorage {
  void onLoad(GuildManager manager);
  void onUnload();
  boolean containsGuild(String name);
  int saveGuilds(Collection<Guild> guilds);
  Set<Guild> loadGuilds();
  boolean saveGuild(Guild guild);
  Guild loadGuild(String name);
  boolean deleteGuild(String name);
}
