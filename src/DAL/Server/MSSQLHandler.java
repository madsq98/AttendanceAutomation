package DAL.Server;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MSSQLHandler {
    private static SQLServerDataSource dataSource;

    private static Connection connection;

    public static void connect(String hostname, String username, String password, String database) throws Exception {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(hostname);
        dataSource.setDatabaseName(database);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        connection = dataSource.getConnection();
    }

    public static Connection getConnection() {
        return connection;
    }
}
