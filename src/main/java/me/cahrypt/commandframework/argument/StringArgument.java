package me.cahrypt.commandframework.argument;

public class StringArgument implements Argument<String> {

    @Override
    public boolean isArg(String arg) {
        return true;
    }

    @Override
    public String getArg(String arg) {
        return arg;
    }
}
