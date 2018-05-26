package me.faceguy.guilds.data;

import java.util.Map;

public class Garrison {
  private Guild owningGuild;
  private Map<Guild, Double> captureMap;
  private long captureTime;
}
