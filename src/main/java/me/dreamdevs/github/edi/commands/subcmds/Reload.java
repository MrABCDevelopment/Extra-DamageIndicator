package me.dreamdevs.github.edi.commands.subcmds;

import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.commands.ArgumentCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class Reload implements ArgumentCommand {

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        EDIMain.getInstance().getSettingsManager().load(EDIMain.getInstance());
        Bukkit.getWorlds().forEach(world -> world.getEntities().stream()
                .filter(ArmorStand.class::isInstance)
                .filter(entity -> entity.hasMetadata("holo"))
                .forEach(Entity::remove));
        commandSender.sendMessage(EDIMain.getInstance().getSettingsManager().getMessage("loaded-configuration").replaceAll("%AMOUNT_MESSAGES%", Integer.toString(EDIMain.getInstance().getSettingsManager().getMessages().size())).replaceAll("%AMOUNT_SETTINGS%", Integer.toString(EDIMain.getInstance().getSettingsManager().getSettings().size())));
        return true;
    }

    @Override
    public String getHelpText() {
        return "&6/edi reload - reloads configuration file";
    }

    @Override
    public String getPermission() {
        return "edi.command";
    }
}