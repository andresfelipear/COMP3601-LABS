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

    public CustomerDaoTester(final File dbPropertiesFile, final ArrayList<Customer> customers) throws IOException
    {
        this.customers = customers;
        dbProperties = new Properties();
        dbProperties.load(new FileInputStream(dbPropertiesFile));
        database = new Database(dbProperties);
    }

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

            // get ids and print information
            ids = customerDao.getIds();
            CustomerReport.printIds(ids);

            // print information of each customer
            printCustomerDetails();
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

    private void connect() throws SQLException
    {
        connection = database.getConnection();
        customerDao = new CustomerDao(database);
    }

    private void dropTables() throws SQLException
    {
        customerDao.drop();
    }

    private void createTables() throws SQLException
    {
        customerDao.create();
    }

    private void update(final Customer customer) throws SQLException
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

    private void delete(final Customer customer)
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

    private void insertCustomers() throws SQLException
    {
        for(final Customer customer : customers)
        {
            customerDao.add(customer);
        }
    }
}
