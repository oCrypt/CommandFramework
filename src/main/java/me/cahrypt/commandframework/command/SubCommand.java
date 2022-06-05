package me.cahrypt.commandframework.command;

import me.cahrypt.commandframework.argument.Argument;
import me.cahrypt.commandframework.argument.IntArgument;
import me.cahrypt.commandframework.argument.PlayerArgument;
import me.cahrypt.commandframework.argument.StringArgument;
import org.bukkit.entity.Player;

import java.util.*;

public class SubCommand {
    private final Map<Integer, Argument<?>> arguments;
    private final Map<Integer, String> constants;
    private final String permission;
    private final int requiredSize;

    public SubCommand(String rawArguments, String permission) {
        this.arguments = new HashMap<>();
        this.constants = new HashMap<>();

        assembleArgs(rawArguments);

        this.permission = permission;
        this.requiredSize = arguments.size() + constants.size();
    }

    private void assembleArgs(String rawArguments) {
        List<String> rawArgList = Arrays.stream(rawArguments.split(" ")).toList();
        rawArgList.forEach(arg -> registerArgument(arg, rawArgList.indexOf(arg)));
    }

    private void registerArgument(String arg, int position) {
        switch (arg.toLowerCase()) {
            case "%player%" -> arguments.put(position, new PlayerArgument());
            case "%int%" -> arguments.put(position, new IntArgument());
            case "%string%" -> arguments.put(position, new StringArgument());
            default -> constants.put(position, arg);
        }
    }

    protected boolean hasPermission(Player player) {
        return player.hasPermission(permission);
    }

    protected boolean isSubCommand(String[] argArray) {
        if (argArray.length != requiredSize) {
            return false;
        }

        for (int i = 0; i < requiredSize; i++) {
            if (this.constants.get(i) != null && this.constants.get(i).equals(argArray[i])) {
                continue;
            }

            if (this.arguments.get(i) != null && this.arguments.get(i).isArg(argArray[i])) {
                continue;
            }

            return false;
        }

        return true;
    }

    protected List<Object> getUsefulArgs(String[] argArray) {
        List<String> argList = List.of(argArray);
        List<Object> objectList = new ArrayList<>();
        argList.forEach(arg -> {
            int argIndex = argList.indexOf(arg);
            if (arguments.get(argIndex) != null) {
                objectList.add(arguments.get(argIndex).getArg(arg));
            }
        });
        return objectList;
    }
}
