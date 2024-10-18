package ca.bcit.comp3601.A01394332.lab03;

import ca.bcit.comp3601.A01394332.lab03.data.ApplicationException;
import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerReader;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerReport;
import ca.bcit.comp3601.A01394332.lab03.logging.log4j.LoggingConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Lab2
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Lab3
{

    private static Logger LOG;

    static
    {
        LoggingConfiguration.configureLogging();
        LOG = LogManager.getLogger(LoggingConfiguration.class);
    }

    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            LOG.error("Usage: must provide customer info as a command argument");
            System.exit(0);
        }

        final CustomerReader      reader;
        final ArrayList<Customer> customers;

        reader = new CustomerReader(args[0]);
        try
        {
            reader.readCustomers();
            customers = reader.getCustomers();
            CustomerReport.printCustomerReport(customers);
        }
        catch(ApplicationException e)
        {
            LOG.error(e.getMessage());
        }
    }
}
