package me.faceguy.guilds.storage.guild;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import me.faceguy.guilds.data.Guild;
import me.faceguy.guilds.data.GuildData;
import me.faceguy.guilds.managers.GuildManager;

public class JsonGuildStorage implements GuildStorage {

  private GuildManager manager;
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private File folder;

  @Override
  public void onLoad(GuildManager manager) {
    this.manager = manager;
    folder = new File(manager.getPlugin().getDataFolder() + File.separator + "guilds");
    if (!folder.exists() || !folder.isDirectory()) {
      folder.mkdirs();
    }
  }

  @Override
  public void onUnload() {

  }

  @Override
  public boolean containsGuild(String name) {
    if (!name.endsWith(".json")) {
      name = name + ".json";
    }
    return new File(folder.getPath(), name).exists();
  }

  @Override
  public boolean saveGuild(Guild guild) {
    if (!guild.isDirty()) return false;
    File file = new File(folder, guild.getData(GuildData.NAME) + ".json");
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      writer.write(gson.toJson(guild));
      writer.flush();
      writer.close();
      return true;
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
      if (!guild.isDirty()) {
        continue;
      }
      if (saveGuild(guild)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public Guild loadGuild(String name) {
    if (!containsGuild(name)) return null;
    if (!name.endsWith(".json")) {
      name = name + ".json";
    }
    BufferedReader br = null;
    try {
      return gson.fromJson(new BufferedReader(new FileReader(new File(folder.getPath(), name + ".json"))), Guild.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean deleteGuild(String name) {
    if (!containsGuild(name)) return false;
    return new File(folder, name + ".json").delete();
  }

  @Override
  public Set<Guild> loadGuilds() {
    try {
      List<File> guildFiles = Files.walk(Paths.get(folder.getPath()))
          .filter(file -> file.getFileName().endsWith(".json"))
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .collect(Collectors.toList());
      if (guildFiles == null) return null;
      return guildFiles.stream().map(file -> loadGuild(file.getName()))
          .filter(Objects::nonNull).collect(Collectors.toSet());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
