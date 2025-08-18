package com.dearmariarenie.bardicsoundboard.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * Utility class for formatting strings using brace parameterization ({}).
 * Wraps SLF4J's MessageFormatter to abstract away the dependency. (This would
 * be more useful as a separate core library, I'll make a separate project for
 * that at some point.)
 */
public class Fmt
{
    /**
     * Returns a formatted string, using the included object as a parameter
     * @param msg the message to format, using {} where parameters should go.
     * @param param the parameter to insert into the string
     * @return the formatted string
     */
    public static String format(String msg, Object param)
    {
        return MessageFormatter.format(msg, param).getMessage();
    }

    /**
     * Returns a formatted string, using two objects as parameters
     * @param msg the message to format, using {} where parameters should go.
     * @param param1 the first parameter to insert into the string
     * @param param2 the second parameter to insert into the string
     * @return the formatted string
     */
    public static String format(String msg, Object param1, Object param2)
    {
        return MessageFormatter.format(msg, param1, param2).getMessage();
    }

    /**
     * Returns a formatted string, using multiple objects as parameters
     * @param msg the message to format, using {} where parameters should go.
     * @param params the parameters to insert into the string
     * @return the formatted string
     */
    public static String format(String msg, Object... params)
    {
        return MessageFormatter.arrayFormat(msg, params).getMessage();
    }
}
