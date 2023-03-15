package me.dreamdevs.github.edi.commands;

import me.dreamdevs.github.edi.ColourUtil;
import me.dreamdevs.github.edi.EDIMain;
import me.dreamdevs.github.edi.commands.subcmds.Info;
import me.dreamdevs.github.edi.commands.subcmds.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommandHandler implements TabExecutor {

    private EDIMain plugin;
    private HashMap<String, Class<? extends ArgumentCommand>> arguments;

    public CommandHandler(EDIMain plugin) {
        this.plugin = plugin;
        this.arguments = new HashMap<>();
        this.arguments.put("reload", Reload.class);
        this.arguments.put("info", Info.class);
        plugin.getCommand("extradamageindicator").setExecutor(this);
        plugin.getCommand("extradamageindicator").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            if(strings.length == 0) {
                commandSender.sendMessage(ColourUtil.colorize("&aHelp for Extra-DamageIndicator"));
                for(Class<? extends ArgumentCommand> argumentCommand : arguments.values()) {
                    commandSender.sendMessage(ColourUtil.colorize(argumentCommand.newInstance().getHelpText()));
                }
                return true;
            } else if(strings.length >= 1) {
                if(arguments.containsKey(strings[0])) {
                    Class<? extends ArgumentCommand> argumentCommand = arguments.get(strings[0]).asSubclass(ArgumentCommand.class);
                    ArgumentCommand argument = argumentCommand.newInstance();
                    if(commandSender.hasPermission(argument.getPermission())) {
                        argument.execute(commandSender, strings);
                    } else {
                        commandSender.sendMessage(ColourUtil.colorize("&cYou don't have permission to do this!"));
                    }
                    return true;
                } else {
                    commandSender.sendMessage(ColourUtil.colorize("&cArgument doesn't exist!"));
                    return true;
                }
            }
        } catch (Exception e) {

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> completions = new ArrayList<>();
        if(strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], arguments.keySet(), completions);
            Collections.sort(completions);
            return completions;
        } else return null;
    }
}