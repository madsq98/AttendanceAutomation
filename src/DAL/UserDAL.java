package DAL.Server;

import BE.Account;

import java.sql.Connection;

public class AccountDAL {
    private Connection conn;

    public AccountDAL() {
        conn = MSSQLHandler.getConnection();
    }

    public Account getAccountLogin(String username, String password) {
        String query = "SELECT ";
    }
}
