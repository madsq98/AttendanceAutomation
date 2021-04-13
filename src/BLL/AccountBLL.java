package BLL;

import BE.Account;
import DAL.AccountDAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBLL {
    private AccountDAL dbAccess;

    private List<Account> allAccounts;

    public AccountBLL() {
        dbAccess = new AccountDAL();
        allAccounts =
    }

    public Account checkLogin(String username, String password) throws SQLException {
        if(!username.isEmpty() && !password.isEmpty()) {
            Account check = dbAccess.getAccountByUsername(username);

            if(check == null) return null;

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

    public void updateAccount(Account a) throws SQLException {
        dbAccess.updateAccount(a);
    }

    public Account getAccountFromId(int id) throws SQLException {
        return dbAccess.getAccountById(id);
    }
}
