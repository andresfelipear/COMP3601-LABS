package ca.bcit.comp3601.A01394332.lab03.logging.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * LoggingConfiguration is responsible for configuring the Log4j logging system.
 * It initializes the logging configuration from a specified XML file and provides
 * access to the logger instance.
 * <p>
 * This class sets up the logging framework, handling potential file-related exceptions
 * and logging them as errors for diagnosis.
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class LoggingConfiguration
{

    private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
    private static final Logger LOG;

    static
    {
        configureLogging();
        LOG = LogManager.getLogger(LoggingConfiguration.class);
    }

    /**
     * Configures the logging system by loading the configuration from
     * the specified LOG4J_CONFIG_FILENAME. If the configuration file
     * cannot be found or read, it logs an error message.
     */
    public static void configureLogging()
    {
        ConfigurationSource source;
        try
        {
            source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
            Configurator.initialize(null,
                                    source);

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println(String.format("Can't find the log4j logging configuration file %s.",
                                             LOG4J_CONFIG_FILENAME));
        }
    }

    /**
     * Gets the Logger instance associated with this class.
     *
     * @return The Logger instance used for logging messages.
     */
    public static Logger getLOG()
    {
        return LOG;
    }
}
