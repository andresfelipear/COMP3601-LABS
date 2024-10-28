package ca.bcit.comp3601.A01394332.lab03.database.dao;

import ca.bcit.comp3601.A01394332.lab03.database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao
{

	protected final Database database;
	protected final String tableName;

	private static final Logger LOG = LogManager.getLogger();

	protected Dao(Database database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}

	/**
	 * Abstract method to create a table in the database.
	 * This method should be implemented by derived classes to define the structure of the table.
	 *
	 * @throws SQLException If a database access error occurs or the creation fails.
	 */
	public abstract void create() throws SQLException;

	/**
	 * Executes a SQL statement to create a table in the database.
	 * This method accepts a SQL statement to create a table, logs the SQL statement for debugging,
	 * and then executes it using the database connection.
	 *
	 * @param sql The SQL query that defines the table creation.
	 * @throws SQLException If a database access error occurs or the creation fails.
	 */
	protected void create(String sql) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Executes a SQL statement to add a new record to the database.
	 * This method accepts a SQL insert query, logs the SQL statement for debugging,
	 * and executes the query to insert the new record.
	 *
	 * @param sql The SQL query that inserts a new record into the table.
	 * @throws SQLException If a database access error occurs or the insertion fails.
	 */
	protected void add(String sql) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Drops the table from the database.
	 * This method constructs and executes a SQL statement to drop the table
	 * associated with this Dao instance. The SQL statement is logged for debugging purposes.
	 *
	 * @throws SQLException If a database access error occurs or the drop operation fails.
	 */
	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = "drop table " + tableName;
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Closes the provided SQL statement.
	 * Ensures that the statement is closed after executing a database operation
	 * to free up database resources. If closing the statement fails,
	 * the SQLException is caught and printed.
	 *
	 * @param statement The SQL statement to be closed.
	 */
	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
