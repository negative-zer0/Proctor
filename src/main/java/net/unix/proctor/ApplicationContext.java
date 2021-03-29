package net.unix.proctor;

/**
 * @author Unix
 * @since 01.10.2020
 */

public class ApplicationContext {

    public static void main(String... args) throws Throwable {
        new Proctor().onStart();
    }

}