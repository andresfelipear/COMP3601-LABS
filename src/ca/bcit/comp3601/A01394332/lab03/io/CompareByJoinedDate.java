package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;

import java.util.Comparator;

/**
 * CompareByJoinedDate is a comparator implementation that
 * compares two Customer instances based on their join dates.
 * It is used to sort or order Customer objects by the date they joined.
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CompareByJoinedDate implements Comparator<Customer>
{
    /**
     * Compares two Customer objects based on their join dates.
     *
     * @param customer1 The first Customer object to compare.
     * @param customer2 The second Customer object to compare.
     * @return A negative integer, zero, or a positive integer as
     *         the first Customer's join date is before, equal to,
     *         or after the second Customer's join date, respectively.
     */
    @Override
    public int compare(final Customer customer1,
                       final Customer customer2)
    {
        return customer1.getJoinDate().compareTo(customer2.getJoinDate());
    }
}
