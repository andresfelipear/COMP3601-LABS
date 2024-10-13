package ca.bcit.comp3601.A01394332.lab03.data.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Validator
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Validator
{

    // Regular expression to validate email addresses.
    private static final String REGEX_VALID_EMAIL;

    // Minimum number of characters allowed for the username part of an email.
    private static final int MIN_CHARS_USERNAME;
    // Minimum number of characters allowed for the domain part of an email.
    private static final int MIN_CHARS_DOMAIN;
    // Maximum number of characters allowed for the domain part of an email.
    private static final int MAX_CHARS_DOMAIN;
    // Minimum number of characters allowed for the top-level domain (TLD) of an email.
    private static final int MIN_CHARS_TOP_DOMAIN;
    // Maximum number of characters allowed for the top-level domain (TLD) of an email.
    private static final int MAX_CHARS_TOP_DOMAIN;

    // Static block to initialize the constants and regex.
    static
    {
        MIN_CHARS_USERNAME   = 2;
        MIN_CHARS_DOMAIN     = 2;
        MAX_CHARS_DOMAIN     = 10;
        MIN_CHARS_TOP_DOMAIN = 2;
        MAX_CHARS_TOP_DOMAIN = 3;

        // Construct the regular expression to validate an email address.
        REGEX_VALID_EMAIL = String.format(
                "^[\\w~!$&'*+/=?^`{|}\\.\\-]{%d,}@[\\w\\-]{%d,%d}\\.[a-zA-Z]{%d,%d}$",
                MIN_CHARS_USERNAME,
                MIN_CHARS_DOMAIN,
                MAX_CHARS_DOMAIN,
                MIN_CHARS_TOP_DOMAIN,
                MAX_CHARS_TOP_DOMAIN
                                                );

    }

    /**
     * Validates whether the provided email matches the defined regular expression.
     *
     * @param email The email address to be validated.
     * @return true if the email is valid according to the regular expression; false otherwise.
     */
    public static boolean validEmail(final String email)
    {
        return email.matches(REGEX_VALID_EMAIL);
    }

    /**
     * Formats the given LocalDate into a string based on the provided format pattern.
     *
     * @param date The LocalDate to be formatted.
     * @param format The format pattern to be applied.
     * @return A string representation of the date in the specified format.
     */
    public static String getDateFormatted(final LocalDate date, final String format)
    {
        final DateTimeFormatter formatter;
        formatter = DateTimeFormatter.ofPattern(format);

        return date.format(formatter);
    }
}
