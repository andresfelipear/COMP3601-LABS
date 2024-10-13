package ca.bcit.comp3601.A01394332.lab03.logging.log4j;

import ca.bcit.comp3601.A01394332.lab03.data.ApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.FileInputStream;
import java.io.IOException;

public class LoggingConfiguration
{

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	private static final Logger LOG;

	static {
		configureLogging();
		LOG = LogManager.getLogger(LoggingConfiguration.class);
	}

	public static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
			LOG.error(e.getMessage());
		}
	}

	public static Logger getLOG()
	{
		return LOG;
	}
}
