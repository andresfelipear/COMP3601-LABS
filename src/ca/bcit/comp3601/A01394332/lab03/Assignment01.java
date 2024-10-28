package ca.bcit.comp3601.A01394332.lab03;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.util.Common;
import ca.bcit.comp3601.A01394332.lab03.database.CustomerDaoTester;
import ca.bcit.comp3601.A01394332.lab03.database.DbConstants;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerGUI;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Lab2
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Assignment01
{

    private static final Logger LOG;
    private static final String CUSTOMER_FILE_NAME;
    private static final String LOG4J_CONFIG_FILENAME;

    static
    {
        LOG4J_CONFIG_FILENAME = "log4j2.xml";
        configureLogging();
        LOG                   = LogManager.getLogger(Assignment01.class);
        CUSTOMER_FILE_NAME    = "customers.dat";
    }

    public static void main(String[] args)
    {
        final LocalDateTime  timestampStart;
        final LocalDateTime  timestampEnd;
        final Duration       duration;

        final CustomerDaoTester   customerDaoTester;
        final CustomerReader      reader;

        ArrayList<Customer> customers;

        customers = null;
        timestampStart = LocalDateTime.now();
        System.out.println(timestampStart);

        reader = new CustomerReader(Common.readAndGetFileAsString(CUSTOMER_FILE_NAME));

        try
        {
            reader.readCustomers();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        customers = reader.getCustomers();

        File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
        if (!dbPropertiesFile.exists()) {
            System.out.println("Please check your db.properties file. The " + DbConstants.DB_PROPERTIES_FILENAME + " file does not exist.");
            System.exit(-1);
        }

        try
        {
            customerDaoTester =  new CustomerDaoTester(dbPropertiesFile, customers);
            customerDaoTester.run();

            new CustomerGUI(customerDaoTester, CUSTOMER_FILE_NAME);
        }
        catch(Exception e)
        {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }


        timestampEnd = LocalDateTime.now();
        System.out.println(timestampEnd);

        duration = Duration.between(timestampStart, timestampEnd);
        System.out.println("Duration: " + duration.toMillis() + " ms");
    }

    private static void configureLogging() {
        ConfigurationSource source;
        try {
            source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
            Configurator.initialize(null, source);

        } catch (IOException e) {
            System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
        }
    }
}
