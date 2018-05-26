package me.faceguy.guilds.utils.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    /**
     * Serialize a location into a string
     * @param loc Location to serialize
     * @return Serialized location string
     */
    public static String serializeLocation(Location loc) {
        return  loc.getWorld().getName() + "," +
                loc.getX()               + "," +
                loc.getY()               + "," +
                loc.getZ()               + "," +
                loc.getYaw()             + "," +
                loc.getPitch();
    }

    /**
     * Deserialize a location from a string
     * @param loc Serialized location string
     * @return Location
     */
    public static Location deserializeLocation(String loc) {
        if (loc == null || !loc.contains(","))
            return null;

        String data[] = loc.split(",");

        if (data.length != 6)
            return null;

        return new Location(
                Bukkit.getServer().getWorld(data[0]),
                Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3]),
                Float.parseFloat(data[4]),
                Float.parseFloat(data[5]));
    }
}
