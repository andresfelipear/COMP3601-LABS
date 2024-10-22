package ca.bcit.comp3601.A01394332.lab03.data.util;

import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerReader;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Common
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Common
{
    /**
     * Reads the content of a file and returns it as a single string, excluding the header line.
     * Appends each line (excluding the header) to a StringBuilder and adds a split character.
     *
     * @param fileName the name of the file to be read.
     * @return the content of the file as a string, with each line concatenated and split by a specific character.
     */
    public static String readAndGetFileAsString(String fileName)
    {
        final StringBuilder       content;
        content = new StringBuilder();

        try
        {
            String         line;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while((line = br.readLine()) != null)
            {
                if(!isHeader(line))
                {
                    content.append(line).append(CustomerReader.SPLIT_CHAR_LINE);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error reading customers.csv");
        }

        return content.toString();
    }

    /**
     * Checks if the provided line matches the header line in the customer data file.
     * Compares the line with known customer detail headers to determine if it's a header.
     *
     * @param line the line to be checked.
     * @return true if the line is a header; false otherwise.
     */
    private static boolean isHeader(final String line)
    {
        for(CustomerDetails detail : CustomerDetails.values())
        {
            if(line.contains(detail.toString()))
            {
                return true;
            }
        }
        return false;
    }
}
