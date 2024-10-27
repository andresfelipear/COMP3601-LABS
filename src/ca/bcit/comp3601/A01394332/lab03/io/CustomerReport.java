package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomerReport
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerReport
{
    private static final String HEADER_SEPARATOR;
    private static final String LINE_SEPARATOR;

    static
    {
        HEADER_SEPARATOR = "--------------------------------------------------------------------------------------------------------------------------------------------------";
        LINE_SEPARATOR   = "\n";
    }

    /**
     * Prints a formatted customer report to the console, including the duration of the report generation.
     *
     * @param customers an ArrayList of Customer objects to be included in the report.
     */
    public static void printCustomerReport(final ArrayList<Customer> customers)
    {


        System.out.println("Customer Report");
        System.out.println(HEADER_SEPARATOR);
        System.out.printf(Customer.CUSTOMER_FORMAT,
                          "ID",
                          CustomerDetails.FIRST_NAME,
                          CustomerDetails.LAST_NAME,
                          CustomerDetails.STREET,
                          CustomerDetails.CITY,
                          CustomerDetails.POSTAL_CODE,
                          CustomerDetails.PHONE_NUMBER,
                          CustomerDetails.EMAIL,
                          CustomerDetails.JOIN_DATE);
        System.out.println(HEADER_SEPARATOR);

        customers.sort(new CompareByJoinedDate());
        customers.forEach(System.out::println);
    }

    public static String getCustomerReportSorted(final ArrayList<Customer> customers, final boolean sortByJoinedDate)
    {
        final StringBuilder customerReport = new StringBuilder();
        customerReport.append(String.format(Customer.CUSTOMER_FORMAT,
                                            "ID",
                                            CustomerDetails.FIRST_NAME,
                                            CustomerDetails.LAST_NAME,
                                            CustomerDetails.STREET,
                                            CustomerDetails.CITY,
                                            CustomerDetails.POSTAL_CODE,
                                            CustomerDetails.PHONE_NUMBER,
                                            CustomerDetails.EMAIL,
                                            CustomerDetails.JOIN_DATE));
        customerReport.append(LINE_SEPARATOR);
        customerReport.append(HEADER_SEPARATOR);
        customerReport.append(LINE_SEPARATOR);

        if(sortByJoinedDate)
        {
            customers.sort(new CompareByJoinedDate());
        }

        customers.forEach( customer -> customerReport.append(customer.toString()).append(LINE_SEPARATOR));

        System.out.println(customerReport);
        return customerReport.toString();
    }

    /**
     * Prints the number of customer IDs loaded from the database and the list of customer IDs.
     *
     * The method uses `System.out.printf` to print the count of IDs and then prints the actual list of IDs
     * by converting the provided list to a string using `toString()`.
     *
     * @param ids A List of customer ID strings to be printed.
     */
    public static void printIds(List<String> ids)
    {
        System.out.printf("Loaded %d customer IDs from the database\n", ids.size());
        System.out.println("Customer IDs:" + ids.toString());
    }

    public static String getCustomerHeader()
    {
        return String.format(Customer.CUSTOMER_FORMAT,
                             "ID",
                             CustomerDetails.FIRST_NAME,
                             CustomerDetails.LAST_NAME,
                             CustomerDetails.STREET,
                             CustomerDetails.CITY,
                             CustomerDetails.POSTAL_CODE,
                             CustomerDetails.PHONE_NUMBER,
                             CustomerDetails.EMAIL,
                             CustomerDetails.JOIN_DATE);
    }
}
