package sql;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceSingleton {

    // Fields to store database connection properties
    private static final String PWD = "password";
    private static final String DB = "ecom";
    private static final String USER = "postgres";
    private static final String SERVER = "localhost";

    // Singleton instance of the DataSource object
    private static DataSource instance;

    // Returns the singleton instance of the DataSource object
    // Singleton instance is created once and reused throughout the lifetime of the application
    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance(); // create a new data source instance if it doesn't exist
        }
        return instance;
    }

    // Creates a new instance of the DataSource object with the specified properties
    private static DataSource createInstance() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[] { SERVER });
        dataSource.setUser(USER);
        dataSource.setDatabaseName(DB);
        dataSource.setPassword(PWD);
        return dataSource;
    }

}
