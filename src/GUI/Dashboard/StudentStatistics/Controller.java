package GUI.Dashboard.StudentStatistics;

import BE.Account;
import BLL.AccountBLL;
import BLL.SchemaBLL;
import GUI.Dashboard.Interfaces.ISubPage;

import java.util.List;

public class Controller implements ISubPage {
    private Account currentAccount;
    private AccountBLL accountBLL;
    private SchemaBLL schemaBLL;

    @Override
    public void setCurrentAccount(Account a) {
        currentAccount = a;
    }

    @Override
    public void setAccountBLL(AccountBLL accountBLL) {
        this.accountBLL = accountBLL;
    }

    @Override
    public void setSchemaBLL(SchemaBLL schemaBLL) {
        this.schemaBLL = schemaBLL;
    }
}