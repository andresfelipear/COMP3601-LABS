package ca.bcit.comp3601.A01394332.lab03.database;

import ca.bcit.comp3601.A01394332.lab03.data.Customer;
import ca.bcit.comp3601.A01394332.lab03.database.dao.CustomerDao;
import ca.bcit.comp3601.A01394332.lab03.io.CustomerReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * CustomerDaoTester
 *
 * @author user
 * @version 1.0
 */
public class CustomerDaoTester
{
    private final Properties dbProperties;
    private final ArrayList<Customer> customers;
    private static Database  database;
    private Connection       connection;
    private CustomerDao      customerDao;
    private List<String>     ids;

    private static final Logger LOG = LogManager.getLogger(CustomerDaoTester.class);

    /**
     * Initializes the CustomerDaoTester with a properties file for database
     * configuration and a list of customers to be tested.
     *
     * @param dbPropertiesFile the database configuration file
     * @param customers a list of Customer objects to be inserted into the database
     * @throws IOException if an error occurs during file reading
     */
    public CustomerDaoTester(final File dbPropertiesFile, final ArrayList<Customer> customers) throws IOException
    {
        this.customers = customers;
        dbProperties = new Properties();
        dbProperties.load(new FileInputStream(dbPropertiesFile));
        database = new Database(dbProperties);
    }

    /**
     * Runs the testing process, including connecting to the database, creating
     * tables, inserting customers, and printing customer information.
     *
     * @throws ClassNotFoundException if the database driver class is not found
     * @throws SQLException if a database access error occurs
     */
    public void run() throws ClassNotFoundException, SQLException
    {
        connect();
        try
        {
            if(database.tableExists(CustomerDao.TABLE_NAME))
            {
                dropTables();
            }
            createTables();
            insertCustomers();
            customerDao.getCustomers();

            // get ids and print information
            getAndPrintCustomersIds();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally
        {
            database.shutdown();
        }
    }

    /**
     * Retrieves and prints customer IDs from the database if the table exists.
     *
     * @throws SQLException if a database access error occurs
     */
    public void getAndPrintCustomersIds() throws SQLException
    {
        if(database.tableExists(CustomerDao.TABLE_NAME))
        {
            ids = customerDao.getIds();
            CustomerReport.printIds(ids);
        }
        else
        {
            System.out.printf("The given table %s does not exist.\n", CustomerDao.TABLE_NAME);
        }
    }

    /**
     * Retrieves customer IDs from the database. If the IDs have already been retrieved,
     * it returns the existing list.
     *
     * @return a list of customer IDs as strings
     * @throws SQLException if a database access error occurs
     */
    public List<String> getIds() throws SQLException
    {
        if(ids == null)
        {
            ids = customerDao.getIds();
        }
        return ids;
    }

    /**
     * Prints detailed information of each customer based on their ID.
     * If a customer cannot be found, logs an error.
     */
    private void printCustomerDetails()
    {
        for(final String id : ids)
        {
            final Customer customer;

            try
            {
                customer = customerDao.getCustomer(id);
                System.out.println(id);
                System.out.printf("SELECT * FROM %s WHERE id = %s\n", CustomerDao.TABLE_NAME, id);
                System.out.println(customer.getStringDetails());
            }
            catch(Exception e)
            {
                System.out.println("Customer with id " + id + " not found");
                LOG.error(e.getMessage());
            }
        }
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return an ArrayList of all Customer objects in the database
     * @throws Exception if a database access error occurs
     */
    public ArrayList<Customer> getCustomers() throws Exception
    {
        return customerDao.getCustomers();
    }

    /**
     * Establishes a connection to the database and initializes the CustomerDao object.
     *
     * @throws SQLException if a database access error occurs
     */
    private void connect() throws SQLException
    {
        connection = database.getConnection();
        customerDao = new CustomerDao(database);
    }

    /**
     * Drops the customer table from the database if it exists.
     *
     * @throws SQLException if a database access error occurs
     */
    public void dropTables() throws SQLException
    {
        customerDao.drop();
    }

    /**
     * Creates the customer table in the database.
     *
     * @throws SQLException if a database access error occurs
     */
    private void createTables() throws SQLException
    {
        customerDao.create();
    }

    /**
     * Updates an existing customer record in the database.
     *
     * @param customer the Customer object containing updated data
     * @throws SQLException if a database access error occurs
     */
    public void update(final Customer customer) throws SQLException
    {
        try
        {
            customerDao.update(customer);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            LOG.error(e);
        }
    }

    /**
     * Deletes a customer record from the database.
     *
     * @param customer the Customer object containing data to delete
     */
    public void delete(final Customer customer)
    {
        try
        {
            customerDao.delete(customer);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            LOG.error(e);
        }
    }

    /**
     * Inserts each customer in the provided list into the database.
     *
     * @throws SQLException if a database access error occurs
     */
    public void insertCustomers() throws SQLException
    {
        for(final Customer customer : customers)
        {
            customerDao.add(customer);
        }
    }
}
