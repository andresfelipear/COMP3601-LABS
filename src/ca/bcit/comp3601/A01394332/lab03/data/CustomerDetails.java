package ca.bcit.comp3601.A01394332.lab03.data;

/**
 * The CustomerDetails enum represents the various details or attributes associated with a customer,
 * such as their ID, name, address, and contact information.
 * Each enum value holds an integer that represents its index position and a string description.
 * This enum can be used to identify specific customer data fields in a structured manner.
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public enum CustomerDetails
{
    ID(0, "ID"), FIRST_NAME(1, "First Name"), LAST_NAME(2, "last Name"),
    STREET_NAME(3, "Street"), CITY(4, "City"), POSTAL_CODE(5, "Postal Code"),
    PHONE_NUMBER(6, "Phone"), EMAIL(7, "Email"), JOIN_DATE(8, "Join Date");

    private final String description;
    private final int    value;

    /**
     * Retrieves the integer index associated with the customer detail.
     *
     * @return the index value of the detail.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Retrieves the description of the customer detail.
     *
     * @return the description of the detail.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Constructor for CustomerDetails enum that initializes the index and description.
     *
     * @param value       The index position of the customer detail.
     * @param description The description of the customer detail.
     */
    CustomerDetails(final int value, final String description)
    {
        this.value = value;
        this.description = description;
    }
}
