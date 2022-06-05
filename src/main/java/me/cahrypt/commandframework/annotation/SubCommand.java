package me.cahrypt.commandframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

    /**
     * The argument format pertaining to the execution of a subcommand.
     * The subcommand will prompt the user with an error message upon improper usage
     * <blockquote><pre>
     *     Format Placeholders Include:
     *     - %player% accepts any online player
     *     - %string% accepts any string value
     *     - %int% accepts any integer value
     *     - %offline_player% accepts any offline player
     * </pre></blockquote>
     * Note that the correct placeholder should be added wherever player input is needed.
     * Any certain arguments can be listed in the format as regular words,
     * and that the argument format should solely contain arguments and not the command
     * @return the required argument format
     */

    String subCommandFormat();

    /**
     * The permission one must have to execute the corresponding subcommand
     * @return the specified permission
     */

    String permission() default "";
}
