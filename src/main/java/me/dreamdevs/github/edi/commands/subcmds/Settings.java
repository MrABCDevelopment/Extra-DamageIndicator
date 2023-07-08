package me.dreamdevs.github.edi.commands.subcmds;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.api.menu.settings.SettingsMenu;
import me.dreamdevs.github.edi.commands.ArgumentCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Settings implements ArgumentCommand
{

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(EDIMain.getInstance().getSettingsManager().getMessage("not-player"));
            return true;
        }

        Player player = (Player) commandSender;
        new SettingsMenu().open(player);
        return true;
    }

    @Override
    public String getHelpText() {
        return "&6/edi settings - change Extra-DamageIndicator settings";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }

}