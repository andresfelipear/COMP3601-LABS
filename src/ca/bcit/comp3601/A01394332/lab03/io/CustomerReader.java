package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.ApplicationException;
import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;
import ca.bcit.comp3601.A01394332.lab03.data.util.Common;
import ca.bcit.comp3601.A01394332.lab03.data.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * CustomerReader
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerReader
{
    public static final String  SPLIT_CHAR_LINE;
    public static final String  FORMATTER_DATE;
    public static final String SPLIT_CHAR_CUSTOMER_DETAILS;
    private static final Logger LOG;

    private final String stringOfFile;
    private final ArrayList<Customer> customers;

    static
    {
        SPLIT_CHAR_LINE             = ":";
        SPLIT_CHAR_CUSTOMER_DETAILS = "\\|";
        FORMATTER_DATE              = "yyyyMMdd";
        LOG                         = LogManager.getLogger(CustomerReader.class);
    }

    /**
     * Constructs a CustomerReader with the provided input string of customer data.
     *
     * @param string The string containing customer data, with each customer separated by the SPLIT_CHAR.
     */
    public CustomerReader(final String string)
    {
        this.stringOfFile = string;
        customers = new ArrayList<>();
    }

    /**
     * Returns the list of customers that have been read and parsed.
     *
     * @return An ArrayList of Customer objects.
     */
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }

    /**
     * Reads the input string, splits it into customer records, and parses each record into Customer objects.
     * Each record is split into details using the defined delimiter (SPLIT_CHAR_CUSTOMER_DETAILS),
     * and those details are used to build and store Customer objects in the customers list.
     */
    public void readCustomers() throws ApplicationException
    {
        final String[] stringCostumers;
        stringCostumers = stringOfFile.split(SPLIT_CHAR_LINE);

        for(final String str: stringCostumers)
        {
            final String[] customerDetailsArray;
            final Customer customer;

            final String    id;
            final String    firstName;
            final String    lastName;
            final String    streetName;
            final String    city;
            final String    postalCode;
            final String    email;
            final String    phoneNumber;

            final LocalDate         joinDate;

            customerDetailsArray = str.split(SPLIT_CHAR_CUSTOMER_DETAILS);

            if(customerDetailsArray.length != Customer.ATTRIBUTE_COUNT)
            {
                final String errorMsg;
                errorMsg = String.format("Invalid number of attributes, expected %d but got %d",
                                         Customer.ATTRIBUTE_COUNT,
                                         stringCostumers.length);
                LOG.error(errorMsg);
                throw new ApplicationException(errorMsg);
            }

            id          = customerDetailsArray[CustomerDetails.CUSTOMER_ID.getIndex()];
            phoneNumber = customerDetailsArray[CustomerDetails.PHONE_NUMBER.getIndex()].replace("'","''");
            firstName   = customerDetailsArray[CustomerDetails.FIRST_NAME.getIndex()].replace("'","''");
            lastName    = customerDetailsArray[CustomerDetails.LAST_NAME.getIndex()].replace("'","''");
            streetName  = customerDetailsArray[CustomerDetails.STREET.getIndex()].replace("'","''");
            city        = customerDetailsArray[CustomerDetails.CITY.getIndex()].replace("'","''");
            postalCode  = customerDetailsArray[CustomerDetails.POSTAL_CODE.getIndex()].replace("'","''");
            email       = customerDetailsArray[CustomerDetails.EMAIL.getIndex()].replace("'","''");
            joinDate    = Common.getLocalDateFromString(customerDetailsArray[CustomerDetails.JOIN_DATE.getIndex()], FORMATTER_DATE);

            if(!Validator.validEmail(email))
            {
                throw new ApplicationException("Invalid Email:");
            }

            customer = new Customer.Builder(id, phoneNumber)
                    .firstName(firstName)
                    .lastName(lastName)
                    .streetName(streetName)
                    .city(city)
                    .postalCode(postalCode)
                    .email(email)
                    .joinDate(joinDate)
                    .build();

            customers.add(customer);
        }
    }
}
