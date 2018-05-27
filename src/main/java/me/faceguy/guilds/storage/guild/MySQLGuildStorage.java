package me.faceguy.guilds.storage.guild;

import co.aikar.idb.DB;
import co.aikar.idb.Database;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.PooledDatabaseOptions;
import java.sql.SQLException;
import java.util.Set;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.managers.GuildManager;
import org.bukkit.configuration.file.FileConfiguration;

public class MySQLGuildStorage implements GuildStorage {

  private GuildManager manager;
  private Database db;

  @Override
  public void onLoad(GuildManager manager) {
    this.manager = manager;
    FileConfiguration c = manager.getPlugin().getConfig();
    // initialize the db
    DatabaseOptions global = DatabaseOptions.builder().mysql(c.getString("database.user"),
        c.getString("database.pass"),
        c.getString("database.name"),
        c.getString("database.address")).build();
    db = PooledDatabaseOptions.builder().options(global).createHikariDatabase();
    DB.setGlobalDatabase(db);
    try {
      if (db.getResults("SHOW TABLES LIKE 'guilds'").isEmpty()) {
        db.query("CREATE TABLE IF NOT EXISTS `guilds` (" +
            "`name` varchar(16) NOT NULL, " +
            "`guild` text, " +
            "PRIMARY KEY `name`" +
            ") DEFAULT CHARSET=utf8");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //load the guilds into the cache async
  }


  @Override
  public void onUnload() {
    saveGuilds();
    db.close();
    db = null;
  }

  @Override
  public void saveGuilds() {

  }

  @Override
  public Set<Guild> loadGuilds() {
    return null;
  }

  @Override
  public boolean saveGuild(Guild guild) {
    return false;
  }

  @Override
  public Guild loadGuild(String name) {
    return null;
  }
}
