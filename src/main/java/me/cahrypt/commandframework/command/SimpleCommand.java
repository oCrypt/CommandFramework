package me.cahrypt.commandframework.command;

import me.cahrypt.commandframework.CommandFramework;
import me.cahrypt.commandframework.annotation.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class SimpleCommand implements CommandExecutor {
    private final CommandFramework main;
    private final Class<? extends SimpleCommand> commandClass;
    private final Map<SubCommand, Method> subCommands;

    public SimpleCommand() {
        this.main = JavaPlugin.getPlugin(CommandFramework.class);
        this.commandClass = this.getClass();
        this.subCommands = new LinkedHashMap<>();
        registerCommand();
        registerSubCommands();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("Only players can run that command!");
            return true;
        }

        subCommands.forEach(((subCommand, method) -> {

            if (!subCommand.isSubCommand(args)) {
                return;
            }

            if (!subCommand.hasPermission(player)) {
                player.sendMessage(ChatColor.RED + "You do not have the proper permissions to run that command!");
                return;
            }

            try {
                method.invoke(this, player, subCommand.getUsefulArgs(args));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }));
        return true;
    }

    private void registerCommand() {
        if (!commandClass.isAnnotationPresent(Command.class)) {
            throw new RuntimeException("Command registration attempt without annotation!");
        }

        String command = commandClass.getAnnotation(Command.class).command();
        main.registerCommand(command, this);
    }

    private void registerSubCommands() {
        for (Method method : commandClass.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(me.cahrypt.commandframework.annotation.SubCommand.class)) {
                continue;
            }
            me.cahrypt.commandframework.annotation.SubCommand methodAnnotation = method.getAnnotation(me.cahrypt.commandframework.annotation.SubCommand.class);
            subCommands.put(new SubCommand(methodAnnotation.subCommandFormat(), methodAnnotation.permission()), method);
        }
    }
}