package net.unix.proctor.util;

/**
 * @author Unix
 * @since 27.07.2020
 */

public final class ConditionsUtil {

    private ConditionsUtil() {
    }

    public static void check(boolean value, Runnable runnable) {
        if (!value) {
            return;
        }

        runnable.run();
    }

}