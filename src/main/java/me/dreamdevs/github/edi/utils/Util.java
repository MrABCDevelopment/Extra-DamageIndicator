package me.dreamdevs.github.edi.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;;

@UtilityClass
public class Util {

    public static void sendPluginMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ColourUtil.colorize(message));
    }

}