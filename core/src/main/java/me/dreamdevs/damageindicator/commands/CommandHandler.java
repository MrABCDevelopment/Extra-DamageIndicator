package me.dreamdevs.damageindicator.commands;

import me.dreamdevs.damageindicator.ExtraDamageIndicatorMain;
import me.dreamdevs.damageindicator.api.ArgumentCommand;
import me.dreamdevs.damageindicator.api.utils.ColourUtil;
import me.dreamdevs.damageindicator.api.utils.Util;
import me.dreamdevs.damageindicator.commands.arguments.Reload;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandHandler implements TabExecutor {

    private final Map<String, ArgumentCommand> arguments;

    public CommandHandler(ExtraDamageIndicatorMain plugin) {
        this.arguments = new LinkedHashMap<>();
        registerCommand("reload", Reload.class);
        Objects.requireNonNull(plugin.getCommand("extradamageindicator")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("extradamageindicator")).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        try {
            if(strings.length == 0) {
                commandSender.sendMessage(ColourUtil.colorize("&aHelp for Extra-DamageIndicator"));
                for(ArgumentCommand argumentCommand : arguments.values()) {
                    commandSender.sendMessage(ColourUtil.colorize(argumentCommand.getHelpText()));
                }
                return true;
            } else if(strings.length >= 1) {
                if(arguments.containsKey(strings[0])) {
                    ArgumentCommand argument = arguments.get(strings[0]);
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
            Util.sendPluginMessage("&cSomething went wrong! Please contanct with developer!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        List<String> completions = new ArrayList<>();
        if(strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], arguments.keySet(), completions);
            Collections.sort(completions);
            return completions;
        } else return null;
    }

    public void registerCommand(String command, Class<? extends ArgumentCommand> clazz) {
        try {
            ArgumentCommand argumentCommand = clazz.getConstructor().newInstance();
            arguments.put(command, argumentCommand);
            Bukkit.getPluginManager().addPermission(new Permission(argumentCommand.getPermission()));
        } catch (Exception e) {
            Util.sendPluginMessage("&cSomething went wrong while registering argument '"+command+"'!");
        }
    }
}