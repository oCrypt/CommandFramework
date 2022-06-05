package me.cahrypt.commandframework;

import me.cahrypt.commandframework.command.SimpleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandFramework extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommand(String string, SimpleCommand simpleCommand) {
        getCommand(string).setExecutor(simpleCommand);
    }
}
