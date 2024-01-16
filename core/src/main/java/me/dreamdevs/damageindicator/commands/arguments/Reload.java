package me.dreamdevs.damageindicator.commands.arguments;

import me.dreamdevs.damageindicator.api.ArgumentCommand;
import me.dreamdevs.damageindicator.api.Config;
import me.dreamdevs.damageindicator.api.Language;
import org.bukkit.command.CommandSender;

public class Reload implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Config.reloadFile();
        Language.reloadLanguage();
        return true;
    }

    @Override
    public String getHelpText() {
        return "&6/edi reload - reloads configuration file";
    }

    @Override
    public String getPermission() {
        return "extradamageindicator.command.reload";
    }
}