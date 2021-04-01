package DAL.Server;

import java.sql.Connection;

public interface IDatabaseConnection {
    public void connect(String hostname, String username, String password, String database);

    public Connection getConnection();
}
