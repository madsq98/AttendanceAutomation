package GUI.Dashboard.Interfaces;

import BE.Account;
import BLL.AccountBLL;

import java.util.List;

public interface ISubPage {
    public void setCurrentAccount(Account a);

    public void setAccounts(List<Account> a);

    public void setAccountBLL(AccountBLL bll);
}
