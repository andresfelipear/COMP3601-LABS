package ca.bcit.comp3601.A01394332.lab03.data;

import ca.bcit.comp3601.A01394332.lab03.data.util.Validator;
import ca.bcit.comp3601.A01394332.lab03.logging.log4j.LoggingConfiguration;
import org.apache.logging.log4j.Logger;


import java.time.LocalDate;

/**
 * Customer
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class Customer
{
    private static final String DATE_FORMAT;
    private static final Logger LOG;

    private final String id;
    private final String firstName;
    private final String lastName;

    private String    streetName;
    private String    city;
    private String    postalCode;
    private String    email;
    private LocalDate joinDate;
    private String    phoneNumber;

    static
    {
        DATE_FORMAT = "MMM dd yyyy";
        LOG         = LoggingConfiguration.getLOG();
    }

    /**
     * Builder class for constructing Customer instances.
     */
    public static class Builder
    {
        private final String id;
        private final String phoneNumber;

        private String    firstName;
        private String    lastName;
        private String    streetName;
        private String    city;
        private String    postalCode;
        private String    email;
        private LocalDate joinDate;

        /**
         * Constructs a Builder for a Customer with the required fields.
         *
         * @param id The unique identifier for the Customer.
         * @param phoneNumber The Customer's phone number.
         */
        public Builder(final String id,
                       final String phoneNumber)
        {
            this.id = id;
            this.phoneNumber = phoneNumber;
        }

        /**
         * Sets the first name for the Customer.
         *
         * @param firstName The Customer's first name.
         * @return The current Builder instance.
         */
        public Builder firstName(final String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the last name for the Customer.
         *
         * @param lastName The Customer's last name.
         * @return The current Builder instance.
         */
        public Builder lastName(final String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the street name for the Customer's address.
         *
         * @param streetName The street name.
         * @return The current Builder instance.
         */
        public Builder streetName(final String streetName)
        {
            this.streetName = streetName;
            return this;
        }

        /**
         * Sets the city for the Customer's address.
         *
         * @param city The city name.
         * @return The current Builder instance.
         */
        public Builder city(final String city)
        {
            this.city = city;
            return this;
        }

        /**
         * Sets the postal code for the Customer's address.
         *
         * @param postalCode The postal code.
         * @return The current Builder instance.
         */
        public Builder postalCode(final String postalCode)
        {
            this.postalCode = postalCode;
            return this;
        }

        /**
         * Sets the email address for the Customer.
         *
         * @param email The Customer's email address.
         * @return The current Builder instance.
         */
        public Builder email(final String email)
        {
            this.email = email;
            return this;
        }

        /**
         * Sets the join date for the Customer.
         *
         * @param joinDate The date the Customer joined.
         * @return The current Builder instance.
         */
        public Builder joinDate(final LocalDate joinDate)
        {
            this.joinDate = joinDate;
            return this;
        }

        /**
         * Builds and returns a new Customer instance based on the current state of the Builder.
         *
         * @return A new Customer instance.
         */
        public Customer build()
        {
            LOG.info("Building a Customer instance with ID: {}", id);
            return new Customer(this);
        }
    }

    /**
     * Private constructor used by the Builder to create a Customer.
     *
     * @param builder The Builder instance with the necessary values to create a Customer.
     */
    private Customer(final Builder builder)
    {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        streetName = builder.streetName;
        city = builder.city;
        postalCode = builder.postalCode;
        email = builder.email;
        joinDate = builder.joinDate;
        phoneNumber = builder.phoneNumber;

        LOG.info("Customer created: {}", this);
    }


    /**
     * Gets the Customer's first name.
     *
     * @return The Customer's first name.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Gets the Customer's last name.
     *
     * @return The Customer's last name.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Gets the Customer's street name.
     *
     * @return The Customer's street name.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * Gets the Customer's city.
     *
     * @return The Customer's city.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Gets the Customer's postal code.
     *
     * @return The Customer's postal code.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Gets the Customer's email address.
     *
     * @return The Customer's email address.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Gets the date the Customer joined.
     *
     * @return The join date of the Customer.
     */
    public LocalDate getJoinDate()
    {
        return joinDate;
    }

    /**
     * Gets the Customer's phone number.
     *
     * @return The Customer's phone number.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sets the Customer's street name.
     *
     * @param streetName The new street name for the Customer.
     */
    public void setStreetName(final String streetName)
    {
        LOG.debug("Setting street name for Customer ID {}: {}", id, streetName);
        this.streetName = streetName;
    }

    /**
     * Sets the Customer's city.
     *
     * @param city The new city for the Customer.
     */
    public void setCity(final String city)
    {
        LOG.debug("Setting city for Customer ID {}: {}", id, city);
        this.city = city;
    }

    /**
     * Sets the Customer's postal code.
     *
     * @param postalCode The new postal code for the Customer.
     */
    public void setPostalCode(final String postalCode)
    {
        LOG.debug("Setting postal code for Customer ID {}: {}", id, postalCode);
        this.postalCode = postalCode;
    }

    /**
     * Sets the Customer's email address.
     *
     * @param email The new email address for the Customer.
     */
    public void setEmail(final String email)
    {
        LOG.debug("Setting email for Customer ID {}: {}", id, email);
        this.email = email;
    }

    /**
     * Sets the Customer's join date.
     *
     * @param joinDate The new join date for the Customer.
     */
    public void setJoinDate(final LocalDate joinDate)
    {
        LOG.debug("Setting join date for Customer ID {}: {}", id, joinDate);
        this.joinDate = joinDate;
    }

    /**
     * Sets the Customer's phone number.
     *
     * @param phoneNumber The new phone number for the Customer.
     */
    public void setPhoneNumber(final String phoneNumber)
    {
        LOG.debug("Setting phone number for Customer ID {}: {}", id, phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the Customer with the formatted details.
     *
     * @return A formatted string with the Customer's details.
     */
    @Override
    public String toString()
    {
        return id + ". " + String.format("%4s", id).replace(' ', '0') +
                String.format("  %-10s %-13s %-25s %-13s %-12s %-15s %-25s %-12s" ,
                              firstName,
                              lastName,
                              streetName,
                              city,
                              postalCode,
                              phoneNumber,
                              email,
                              Validator.getDateFormatted(joinDate, DATE_FORMAT));
    }
}
