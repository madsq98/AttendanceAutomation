package DAL.Server;

import java.sql.Connection;

public interface IDatabaseConnection {
    public static void connect(String hostname, String username, String password, String database) {

    }

    public static Connection getConnection() {
        return null;
    }
}
