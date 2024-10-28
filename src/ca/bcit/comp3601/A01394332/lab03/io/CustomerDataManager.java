package ca.bcit.comp3601.A01394332.lab03.io;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.util.Common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * CustomerDataManager
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerDataManager
{
    private final String fileName;

    public CustomerDataManager(final String fileName)
    {
        this.fileName = fileName;
    }

    public void writeCustomerToFile(final Customer customer)
    {
        final StringBuilder       content;
        content = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName));)
        {
            String         line;
            while((line = br.readLine()) != null)
            {
                if(line.contains(customer.getId()))
                {
                    String customerString = Common.getCustomerAsStringToWriteTextFile(customer, "|");
                    content.append(customerString).append("\n");
                }
                else
                {
                    content.append(line).append("\n");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error reading customers.dat");
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            bw.write(content.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error writing customers.dat");
        }
    }
}
