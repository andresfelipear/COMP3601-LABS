package ca.bcit.comp3601.A01394332.lab03.database.dao;


import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.data.CustomerDetails;
import ca.bcit.comp3601.A01394332.lab03.database.Database;
import ca.bcit.comp3601.A01394332.lab03.database.DbConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerDao
 *
 * @author Andres Arevalo
 * @version 1.0
 */
public class CustomerDao extends Dao
{
    public static final String TABLE_NAME;

    private static final Logger LOG;

    static
    {
        TABLE_NAME = DbConstants.CUSTOMER_TABLE_NAME;
        LOG        = LogManager.getLogger();
    }

    public CustomerDao(final Database database)
    {
        super(database, TABLE_NAME);
    }

    /**
     * Creates the customer table in the database if it doesn't already exist.
     * The table is created with the following columns:
     * - CUSTOMER_ID: The unique identifier for each customer.
     * - FIRST_NAME: The customer's first name.
     * - LAST_NAME: The customer's last name.
     * - STREET: The customer's street address.
     * - CITY: The city in which the customer resides.
     * - POSTAL_CODE: The customer's postal code.
     * - PHONE_NUMBER: The customer's phone number.
     * - EMAIL: The customer's email address.
     * - JOIN_DATE: The date the customer joined, formatted as yyyyMMdd.
     * This method logs the SQL statement for debugging purposes.
     *
     * @throws SQLException If a database access error occurs or the table creation fails.
     */
    @Override
    public void create() throws SQLException
    {
        String sql = String.format(
                "create table %s(" // 1
                        + "%s VARCHAR(9), " // 2
                        + "%s VARCHAR(20), " // 3
                        + "%s VARCHAR(20), " // 4
                        + "%s VARCHAR(40), " // 5
                        + "%s VARCHAR(25), " // 6
                        + "%s VARCHAR(10), " // 7
                        + "%s VARCHAR(15), " // 8
                        + "%s VARCHAR(50), " // 9
                        + "%s DATE, " // 10
                        + "primary key (%s) )",// 11
                tableName,// 1
                CustomerDetails.CUSTOMER_ID.getName(),// 2
                CustomerDetails.FIRST_NAME.getName(),// 3
                CustomerDetails.LAST_NAME.getName(),// 4
                CustomerDetails.STREET.getName(),// 5
                CustomerDetails.CITY.getName(),// 6
                CustomerDetails.POSTAL_CODE.getName(),// 7
                CustomerDetails.PHONE_NUMBER.getName(),// 8
                CustomerDetails.EMAIL.getName(),// 9
                CustomerDetails.JOIN_DATE.getName(),// 10
                CustomerDetails.CUSTOMER_ID.getName()); // 11
        LOG.debug(sql);
        super.create(sql);
    }

    /**
     * Adds a new customer record to the customer table in the database.
     * The customer details are inserted into the table as follows:
     * - CUSTOMER_ID
     * - FIRST_NAME
     * - LAST_NAME
     * - STREET
     * - CITY
     * - POSTAL_CODE
     * - PHONE_NUMBER
     * - EMAIL
     * - JOIN_DATE
     * This method logs the SQL statement for debugging purposes.
     *
     * @param customer The customer object containing the details to be inserted.
     * @throws SQLException If a database access error occurs or the insertion fails.
     */
    public void add(final Customer customer) throws SQLException
    {
        Statement statement;
        statement = null;

        try
        {
            Connection connection = database.getConnection();
            statement = connection.createStatement();
            String sql = String.format(
                    "insert into %s values(" // 1
                            + "'%s', " // 2
                            + "'%s', " // 3
                            + "'%s', " // 4
                            + "'%s', " // 5
                            + "'%s', " // 6
                            + "'%s', " // 7
                            + "'%s', " // 8
                            + "'%s', " // 9
                            + "'%s') ", // 10
                    tableName,// 1
                    customer.getId(),// 2
                    customer.getFirstName(),// 3
                    customer.getLastName(),// 4
                    customer.getStreetName(),// 5
                    customer.getCity(),// 6
                    customer.getPostalCode(),// 7
                    customer.getPhoneNumber(),// 8
                    customer.getEmail(),// 9
                    customer.getJoinDate());// 10
            statement.executeUpdate(sql);
        }
        finally
        {
            close(statement);
        }
    }

    /**
     * Updates the details of an existing customer in the database.
     * The customer record is updated based on the customer's ID, and all other fields
     * (first name, last name, street, city, postal code, phone number, email, and join date)
     * are modified to match the provided customer object.
     * This method logs the SQL statement for debugging purposes.
     *
     * @param customer The customer object containing the updated details.
     * @throws SQLException If a database access error occurs or the update fails.
     */
    public void update(final Customer customer) throws SQLException {
        Connection connection;
        Statement statement = null;
        try
        {
            connection = database.getConnection();
            statement = connection.createStatement();
            // Execute a statement
            String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'",
                                       tableName, //
                                       CustomerDetails.CUSTOMER_ID.getName(), customer.getId(), //
                                       CustomerDetails.FIRST_NAME.getName(), customer.getFirstName(), //
                                       CustomerDetails.LAST_NAME.getName(), customer.getLastName(), //
                                       CustomerDetails.STREET.getName(), customer.getStreetName(), //
                                       CustomerDetails.CITY.getName(), customer.getCity(), //
                                       CustomerDetails.POSTAL_CODE.getName(), customer.getPostalCode(), //
                                       CustomerDetails.PHONE_NUMBER.getName(), customer.getPhoneNumber(), //
                                       CustomerDetails.EMAIL.getName(), customer.getEmail(), //
                                       CustomerDetails.JOIN_DATE.getName(), customer.getJoinDate(), //
                                       CustomerDetails.CUSTOMER_ID.getName(), customer.getId());
            LOG.debug(sql);
            int rowcount = statement.executeUpdate(sql);
            System.out.printf("Updated %d rows%n", rowcount);
        } finally
        {
            close(statement);
        }
    }

    /**
     * Deletes a customer record from the customer table in the database.
     * The customer is identified by their unique customer ID, and the corresponding record
     * is removed from the table.
     * This method logs the SQL statement for debugging purposes.
     *
     * @param customer The customer object whose record should be deleted.
     * @throws SQLException If a database access error occurs or the deletion fails.
     */
    public void delete(final Customer customer) throws SQLException {
        Connection connection;
        Statement statement = null;
        try
        {
            connection = database.getConnection();
            statement = connection.createStatement();
            // Execute a statement
            String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, CustomerDetails.CUSTOMER_ID.getName(), customer.getId());
            LOG.debug(sql);
            int rowcount = statement.executeUpdate(sql);
            System.out.printf("Deleted %d rows%n", rowcount);
        } finally
        {
            close(statement);
        }
    }

    /**
     * Retrieves a list of all customer IDs from the customer table in the database.
     * This method queries the database for all customer IDs and returns them as a list.
     * It is useful for fetching the list of all customers' unique identifiers.
     * This method logs the SQL statement for debugging purposes.
     *
     * @return A list of customer IDs (as strings) from the database.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public List<String> getIds() throws SQLException
    {
        List<String> customersIds = new ArrayList<>();
        Connection connection;
        Statement statement = null;
        ResultSet resultSet;

        try
        {
            connection = database.getConnection();
            statement = connection.createStatement();

            // Execute a statement
            String sql = String.format("SELECT %s from %s", CustomerDetails.CUSTOMER_ID.getName(), tableName);
            LOG.debug(sql);
            resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                customersIds.add(resultSet.getString(CustomerDetails.CUSTOMER_ID.getName()));
            }
        } finally
        {
            close(statement);
        }
        return customersIds;
    }

    /**
     * Retrieves a specific customer's details from the database based on their customer ID.
     * The method queries the database for the customer record with the given ID and constructs
     * a Customer object containing their details (ID, first name, last name, street, city, postal code,
     * phone number, email, and join date).
     * If more than one record is found for the given ID, an exception is thrown.
     * This method logs the SQL statement for debugging purposes.
     *
     * @param id The unique customer ID for which the details are to be retrieved.
     * @return The Customer object representing the customer's details.
     */
    public Customer getCustomer(final String id)
    {
        Customer   customer  = null;
        Connection connection;
        Statement  statement = null;
        ResultSet resultSet;

        try
        {
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = String.format("SELECT * from %s WHERE %s='%s'",
                                       tableName,
                                       CustomerDetails.CUSTOMER_ID.getName(),
                                       id);
            resultSet = statement.executeQuery(sql);

            ArrayList<Customer> customers = getCustomersFromResultSet(resultSet);
            if(customers.isEmpty())
            {
                return null;
            }
            customer = customers.get(0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            close(statement);
        }
        return customer;
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return a list of all Customer objects from the database
     * @throws SQLException if a database access error occurs
     */
    public ArrayList<Customer> getCustomers() throws SQLException
    {
        ArrayList<Customer> customers;
        Connection connection;
        Statement  statement = null;
        ResultSet resultSet;

        try
        {
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = String.format("SELECT * from %s",
                                       tableName);
            resultSet = statement.executeQuery(sql);
            customers = getCustomersFromResultSet(resultSet);
        }
        finally
        {
            close(statement);
        }

        return customers;
    }

    /**
     * Converts a ResultSet into a list of Customer objects.
     *
     * @param rs the ResultSet containing customer data
     * @return a list of Customer objects
     * @throws SQLException if a database access error occurs
     */
    public ArrayList<Customer> getCustomersFromResultSet(final ResultSet rs) throws SQLException
    {
        final ArrayList<Customer> customers;

        customers = new ArrayList<>();

        while(rs.next())
        {
            final Customer  customer;
            final String    customerId;
            final String    firstName;
            final String    lastName;
            final String    streetName;
            final String    city;
            final String    postalCode;
            final String    email;
            final String    phoneNumber;
            final LocalDate         joinDate;

            customerId  = rs.getString(CustomerDetails.CUSTOMER_ID.getName());
            phoneNumber = rs.getString(CustomerDetails.PHONE_NUMBER.getName());
            firstName   = rs.getString(CustomerDetails.FIRST_NAME.getName());
            lastName    = rs.getString(CustomerDetails.LAST_NAME.getName());
            streetName  = rs.getString(CustomerDetails.STREET.getName());
            city        = rs.getString(CustomerDetails.CITY.getName());
            postalCode  = rs.getString(CustomerDetails.POSTAL_CODE.getName());
            email       = rs.getString(CustomerDetails.EMAIL.getName());
            joinDate    = LocalDate.parse(rs.getString(CustomerDetails.JOIN_DATE.getName()));

            customer = new Customer.Builder(customerId, phoneNumber)
                    .firstName(firstName)
                    .lastName(lastName)
                    .streetName(streetName)
                    .city(city)
                    .postalCode(postalCode)
                    .email(email)
                    .joinDate(joinDate)
                    .build();

            customers.add(customer);
        }

        return customers;
    }
}
