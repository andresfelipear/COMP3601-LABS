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
    CUSTOMER_ID(0, "customerId", "VARCHAR", 9 ),
    FIRST_NAME(1, "firstName", "VARCHAR", 20 ),
    LAST_NAME(2, "lastName", "VARCHAR", 20 ),
    STREET(3, "street", "VARCHAR", 40 ),
    CITY(4, "city", "VARCHAR", 15 ),
    POSTAL_CODE(5, "postalCode", "VARCHAR", 10 ),
    PHONE_NUMBER(6, "phoneNumber", "VARCHAR", 10 ),
    EMAIL(7, "email", "VARCHAR", 30 ),
    JOIN_DATE(8, "dateJoined", "DATE", -1);

    private final String name;
    private final String type;
    private final int    length;
    private final int    index;


    /**
     * Retrieves the integer index associated with the customer detail.
     *
     * @return the index value of the detail.
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Retrieves the description of the customer detail.
     *
     * @return the description of the detail.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Constructor for CustomerDetails enum that initializes the index and name.
     *
     * @param index       The index position of the customer detail.
     * @param name The name of the customer detail.
     */
    CustomerDetails(final int index, final String name, final String type, final int length)
    {
        this.index = index;
        this.name = name;
        this.type = type;
        this.length = length;
    }
}
