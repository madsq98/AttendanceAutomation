package DAL.Server;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MSSQLHandler implements IDatabaseConnection {
    private static SQLServerDataSource dataSource;

    private static Connection connection;

    @Override
    public static void connect(String hostname, String username, String password, String database) throws SQLException {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(hostname);
        dataSource.setDatabaseName(database);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        connection = dataSource.getConnection();
    }

    @Override
    public static Connection getConnection() {
        return connection;
    }
}
