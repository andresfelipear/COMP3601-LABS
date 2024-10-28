package ca.bcit.comp3601.A01394332.lab03.data.util;

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

    // Static block to initialize the constants and regex.
    static
    {
        REGEX_VALID_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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


}
