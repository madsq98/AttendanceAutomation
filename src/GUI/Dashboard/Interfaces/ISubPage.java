package GUI.Dashboard.Interfaces;

import BE.Account;
import BLL.AccountBLL;
import BLL.AttendanceBLL;
import BLL.SchemaBLL;

import java.util.List;

public interface ISubPage {
    public void setCurrentAccount(Account a);

    public void setAccountBLL(AccountBLL accountBLL);

    public void setSchemaBLL(SchemaBLL schemaBLL);

    public void setAttendanceBLL(AttendanceBLL attendanceBLL);

    public void load();
}
