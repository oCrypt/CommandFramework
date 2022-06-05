package me.cahrypt.commandframework.argument;

public interface Argument<T> {
    boolean isArg(String arg);
    T getArg(String arg);
}
