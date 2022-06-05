package me.cahrypt.commandframework.argument;

public class IntArgument implements Argument<Integer> {

    @Override
    public boolean isArg(String arg) {
        return Integer.getInteger(arg) != null;
    }

    @Override
    public Integer getArg(String arg) {
        return Integer.parseInt(arg);
    }
}
