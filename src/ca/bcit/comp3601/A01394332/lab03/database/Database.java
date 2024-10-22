package ca.bcit.comp3601.A01394332.lab03.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Database
 *
 * @author user
 * @version 1.0
 */
public class Database
{
    public static final String DB_DRIVER_KEY = "db.driver";
    public static final String DB_URL_KEY = "db.url";
    public static final String DB_USER_KEY = "db.user";
    public static final String DB_PASSWORD_KEY = "db.password";

    private static Logger LOG = LogManager.getLogger();

    private static Connection connection;
    private final Properties properties;

    /**
     * Constructor that initializes the Database class with a properties object.
     * The properties object contains the database configuration details (driver, URL, user, password).
     *
     * @param properties The Properties object containing the database configuration.
     * @throws FileNotFoundException if the properties file cannot be found.
     * @throws IOException           if there is an issue loading the properties file.
     */
    public Database(Properties properties) throws FileNotFoundException, IOException
    {
        LOG.debug("Loading database properties from db.properties");
        this.properties = properties;
    }

    /**
     * Retrieves the current database connection. If no connection exists, it attempts to create one by calling the connect() method.
     *
     * @return The Connection object for the database.
     * @throws SQLException if a database access error occurs or if the connection cannot be established.
     */
    public Connection getConnection() throws SQLException
    {
        if (connection != null) {
            return connection;
        }
        try {
            connect();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }

        return connection;
    }

    /**
     * Connects to the database using the properties loaded during initialization.
     * Loads the JDBC driver and establishes a connection to the database.
     *
     * @throws ClassNotFoundException if the database driver class cannot be found.
     * @throws SQLException           if a database access error occurs.
     */
    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty(DB_DRIVER_KEY));
        System.out.println("Driver loaded");
        connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY),
                                                 properties.getProperty(DB_PASSWORD_KEY));
        System.out.println("Database connected");
    }

    /**
     * Shuts down the database connection by closing it and setting the connection object to null.
     */
    public void shutdown() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if a given table exists in the database.
     *
     * @param tableName The name of the table to check.
     * @return true if the table exists in the database; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public static boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet        resultSet        = null;
        String           rsTableName      = null;

        try {
            resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
            while (resultSet.next()) {
                rsTableName = resultSet.getString("TABLE_NAME");
                if (rsTableName.equalsIgnoreCase(tableName)) {
                    return true;
                }
            }
        } finally {
            resultSet.close();
        }

        return false;
    }
}
