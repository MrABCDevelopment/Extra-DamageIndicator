package me.dreamdevs.damageindicator.api;

import org.bukkit.command.CommandSender;

public interface ArgumentCommand {

    boolean execute(CommandSender commandSender, String[] args);

    String getHelpText();

    String getPermission();

}