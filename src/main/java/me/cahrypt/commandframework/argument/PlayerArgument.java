package me.cahrypt.commandframework.argument;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerArgument implements Argument<Player> {

    @Override
    public boolean isArg(String arg) {
        return Bukkit.getPlayer(arg) != null;
    }

    @Override
    public Player getArg(String arg) {
        return Bukkit.getPlayer(arg);
    }
}
