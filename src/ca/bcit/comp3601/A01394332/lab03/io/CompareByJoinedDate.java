package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;

import java.util.Comparator;

/**
 * CompareByJoinedDate
 *
 * @author user
 * @version 1.0
 */
public class CompareByJoinedDate implements Comparator<Customer>
{
    @Override
    public int compare(final Customer customer1,
                       final Customer customer2)
    {
        return customer1.getJoinDate().compareTo(customer2.getJoinDate());
    }
}
