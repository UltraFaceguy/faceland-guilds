package me.faceguy.guilds.storage.guild;

import co.aikar.idb.DB;
import co.aikar.idb.Database;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.DbRow;
import co.aikar.idb.DbStatement;
import co.aikar.idb.PooledDatabaseOptions;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.managers.GuildManager;
import org.bukkit.configuration.file.FileConfiguration;

public class MySQLGuildStorage implements GuildStorage {

  private GuildManager manager;
  private Database db;
  private final Gson gson = new Gson();

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
  }


  @Override
  public void onUnload() {
    db.close();
    db = null;
  }

  /**
   * Save a guild to database.
   * This method should be called async!
   * @param guild {@link Guild} to save
   * @return true if the guild was saved to database
   */
  @Override
  public boolean saveGuild(Guild guild) {
    if (!guild.isDirty()) return false;
    try {
      db.executeUpdate(
          "INSERT INTO `guilds` (`name`, `guild`) " +
              "VALUES (?, ?)  ON DUPLICATE KEY " +
              "UPDATE guild = VALUES(guild)",
          guild.getName(), gson.toJson(guild));
      guild.setDirty(false);
      return true;
      // we will catch any excption since it may not be a SQLException thrown
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  @Override
  public int saveGuilds(Collection<Guild> guilds) {
    if (guilds == null) return 0;
    int count = 0;
    for (Guild guild : guilds) {
      // only save guilds that have changed data...
      if (!guild.isDirty()) {
        continue;
      }
      if (saveGuild(guild)) {
        count++;
      }
    }
    return count;
  }


  // Should be called async
  @Override
  public Guild loadGuild(String name) {
    try {
      String json = db.getFirstColumn("SELECT guild FROM `guilds` WHERE name = ?", name);
      return json == null ? null : gson.fromJson(json, Guild.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Load all guilds stored in the database to  new HashSet
   * @return all guilds currently saved via MySQL
   */
  @Override
  public Set<Guild> loadGuilds() {
    Set<Guild> guilds = Sets.newHashSet();
    try {
      DbStatement query = db.query("SELECT name, guild FROM `guilds`");
      for (DbRow row : query.getResults()) {
        try {
          String s = row.get("guild");
          Guild guild = gson.fromJson(s, Guild.class);
          guilds.add(guild);
        } catch (Exception e) {
          e.printStackTrace();
          continue;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return guilds.isEmpty() ? null : guilds;
  }
}
