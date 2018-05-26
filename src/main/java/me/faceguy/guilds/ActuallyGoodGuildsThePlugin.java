package me.faceguy.guilds;

import com.tealcube.minecraft.bukkit.facecore.plugin.FacePlugin;
import io.pixeloutlaw.minecraft.spigot.config.MasterConfiguration;
import io.pixeloutlaw.minecraft.spigot.config.VersionedConfiguration;
import io.pixeloutlaw.minecraft.spigot.config.VersionedSmartYamlConfiguration;
import java.io.File;
import me.faceguy.guilds.managers.GuildManager;
import org.bukkit.event.HandlerList;

public class ActuallyGoodGuildsThePlugin extends FacePlugin {

  private MasterConfiguration settings;
  private VersionedSmartYamlConfiguration configYAML;
  private GuildManager guildManager;

  @Override
  public void enable() {
    configYAML = new VersionedSmartYamlConfiguration(new File(getDataFolder(), "config.yml"),
        getResource("config.yml"), VersionedConfiguration.VersionUpdateType.BACKUP_AND_UPDATE);

    if (configYAML.update()) {
      getLogger().info("Updating config.yml");
    }

    settings = MasterConfiguration.loadFromFiles(configYAML);

    guildManager = new GuildManager(this);

  }

  @Override
  public void disable() {
    //storage.saveAll();
    HandlerList.unregisterAll(this);
    settings = null;
    configYAML = null;
    guildManager = null;
  }

  public MasterConfiguration getSettings() {
    return settings;
  }

  public GuildManager getGuildManager() {
    return guildManager;
  }
}
