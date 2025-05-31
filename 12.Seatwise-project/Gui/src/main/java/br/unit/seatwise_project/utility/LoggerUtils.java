
package br.unit.seatwise_project.utility;


// Init -------------------------------------------------------------------- //


public class LoggerUtils {


    // Main methods

    public static java.util.logging.Logger getLogger(Class<?> clazz) {

        return java.util.logging.Logger.getLogger(clazz.getName());
    }
}
