package DAL.Server;

import java.sql.Connection;

public class UserDAL {
    private Connection conn;

    public UserDAL() {
        conn = MSSQLHandler.getConnection();
    }
}
