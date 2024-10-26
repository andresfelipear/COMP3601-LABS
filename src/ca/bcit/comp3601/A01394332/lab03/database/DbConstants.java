package ca.bcit.comp3601.A01394332.lab03.database;


public interface DbConstants
{
	String DB_PROPERTIES_FILENAME = "db.properties";
	String DB_DRIVER_KEY = "db.driver";
	String DB_URL_KEY = "db.url";
	String DB_USER_KEY = "db.user";
	String DB_PASSWORD_KEY = "db.password";
	String TABLE_ROOT = "A00123456_";
	String CUSTOMER_TABLE_NAME = TABLE_ROOT + "Customer";
	String STUDENT_TABLE_NAME = TABLE_ROOT + "Student";
	String COURSE_TABLE_NAME = TABLE_ROOT + "Course";
	String ENROLLMENT_TABLE_NAME = TABLE_ROOT + "Enrollment";
}
