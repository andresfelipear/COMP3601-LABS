package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * CustomerReport
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerReport
{
    /**
     * Prints a formatted customer report to the console.
     *
     * @param customers an ArrayList of Customer objects to be included in the report.
     */
    public static void printCustomerReport(final ArrayList<Customer> customers)
    {
        final LocalDateTime timestampStart;
        final LocalDateTime timestampEnd;
        final Duration      duration;

        timestampStart = LocalDateTime.now();
        System.out.println(timestampStart);

        System.out.println("Customer Report");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("#. %-4s  %-10s %-13s %-25s %-13s %-12s %-15s %-25s %-12s",
                          CustomerDetails.ID,
                          CustomerDetails.FIRST_NAME,
                          CustomerDetails.LAST_NAME,
                          CustomerDetails.STREET_NAME,
                          CustomerDetails.CITY,
                          CustomerDetails.POSTAL_CODE,
                          CustomerDetails.PHONE_NUMBER,
                          CustomerDetails.EMAIL,
                          CustomerDetails.JOIN_DATE);
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------");
        customers.sort(new CompareByJoinedDate());
        customers.forEach(System.out::println);

        timestampEnd = LocalDateTime.now();
        System.out.println(timestampEnd);

        duration = Duration.between(timestampStart, timestampEnd);
        System.out.println("Duration: " + duration.toMillis() + " ms");
    }
}
