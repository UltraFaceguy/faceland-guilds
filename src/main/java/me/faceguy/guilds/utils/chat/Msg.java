package me.faceguy.guilds.utils.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class Msg {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void msg(CommandSender s, String... message) {
        Arrays.asList(message).forEach(line -> s.sendMessage(color(line)));
    }

    public static void msg(CommandSender s, List<String> message) {
        message.forEach(line -> s.sendMessage(color(line)));
    }
}
