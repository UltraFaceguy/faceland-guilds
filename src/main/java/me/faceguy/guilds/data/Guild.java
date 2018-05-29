package me.faceguy.guilds.data;

import java.util.List;
import org.bukkit.DyeColor;

public class Guild {
  private boolean dirty;
  private String name;
  private GuildPlayer master;
  private List<GuildPlayer> memberList;
  private int guildLevel;
  private double vault;
  private DyeColor primaryColor;
  private DyeColor secondaryColor;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GuildPlayer getMaster() {
    return master;
  }

  public void setMaster(GuildPlayer master) {
    this.master = master;
  }

  public List<GuildPlayer> getMemberList() {
    return memberList;
  }

  public void setMemberList(List<GuildPlayer> memberList) {
    this.memberList = memberList;
  }

  public int getGuildLevel() {
    return guildLevel;
  }

  public void setGuildLevel(int guildLevel) {
    this.guildLevel = guildLevel;
  }

  public double getVault() {
    return vault;
  }

  public void setVault(double vault) {
    this.vault = vault;
  }

  public DyeColor getPrimaryColor() {
    return primaryColor;
  }

  public void setPrimaryColor(DyeColor primaryColor) {
    this.primaryColor = primaryColor;
  }

  public DyeColor getSecondaryColor() {
    return secondaryColor;
  }

  public void setSecondaryColor(DyeColor secondaryColor) {
    this.secondaryColor = secondaryColor;
  }

  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }
}
