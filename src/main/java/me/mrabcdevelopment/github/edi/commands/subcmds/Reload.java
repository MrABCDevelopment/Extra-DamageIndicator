package me.mrabcdevelopment.github.edi.commands.subcmds;

import me.mrabcdevelopment.github.edi.EDIMain;
import me.mrabcdevelopment.github.edi.Settings;
import me.mrabcdevelopment.github.edi.commands.ArgumentCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Reload implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        new Settings(EDIMain.getInstance());
        commandSender.sendMessage(ChatColor.RED+"Configuration file has reloaded!");
        return true;
    }

    @Override
    public String getHelpText() {
        return ChatColor.GOLD+ "/edi reload - reloads configuration file";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }
}