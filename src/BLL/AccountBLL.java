package BLL;

import BE.Account;
import DAL.Server.AccountDAL;

import java.sql.SQLException;

public class AccountBLL {
    private AccountDAL dbAccess;

    public AccountBLL() {
        dbAccess = new AccountDAL();
    }

    public Account checkLogin(String username, String password) throws SQLException {
        if(!username.isEmpty() && !password.isEmpty()) {
            Account check = dbAccess.getAccountByUsername(username);

            return (check.getPassword().equals(password)) ? check : null;
        } else
            return null;
    }

    public boolean newAccount(Account a) throws SQLException {
        int newId = dbAccess.saveNewAccount(a);

        if(newId == -1)
            return false;

        a.setId(newId);

        return true;
    }
}
