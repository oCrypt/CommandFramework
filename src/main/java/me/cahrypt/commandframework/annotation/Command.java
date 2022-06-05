package me.cahrypt.commandframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

    /**
     * The command that will be the basis of all
     * the class subcommands
     * @return the command group command
     */

    String command() default "unspecified_cmd";
}
